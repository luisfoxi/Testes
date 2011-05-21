/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando
 */
public class Campos {

    public static void teste(){
        try {
            Class.forName("org.postgresql.Driver");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Campos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tabelaDestino= "cliente", tabelaOrigem= "Clientes";
        String campo;
        String tipo;
        String queryOrigem;
        PreparedStatement psOrigem;
        int id;
        String mensagem;
        String campoDestino;
        try {
            Connection conOrigem =  DriverManager.getConnection("jdbc:odbc:DistribuicaoPE");
            Connection conDestino = DriverManager.getConnection("jdbc:postgresql://localhost/ERP_GAPAN", "postgres", "123");
            String queryDestino = "SELECT * FROM "+tabelaDestino+";";
            PreparedStatement psDestino= conDestino.prepareStatement(queryDestino);
            psDestino.setFetchSize(3);
            ResultSet rsDestino= psDestino.executeQuery();
            ResultSetMetaData mdDestino= rsDestino.getMetaData();

            while(rsDestino.next()){
                id= rsDestino.getInt("id_"+tabelaDestino);
                System.out.println(id);
                queryOrigem = "SELECT C.id AS id_clientes, R.id AS id_rota, C.cod,"
                            + "PADR(C.nome,60) AS nome, PADR(C.apelido,60) AS apelido,"
                            + "C.tipo AS situacao, IIF(EMPTY(C.deletado),1,0) AS fg_ativo,"
                            + "C.incluido AS created_at, C.incluidopor AS created_by,"
                            + "C.incluidoem AS created_on, SPACE(60) AS created_user_db,"
                            + "C.alterado AS modified_at,"
                            + "C.alteradopor AS modified_by, C.alteradoem AS modified_on,"
                            + "SPACE(60) AS modified_user_db"
                            + " FROM "
                            + tabelaOrigem + " C LEFT JOIN Rotas R ON C.rota=R.cod WHERE C.id=? AND NOT DELETED()";
                psOrigem= conOrigem.prepareStatement(queryOrigem);
                psOrigem.setInt(1, id);
                ResultSet rsOrigem= psOrigem.executeQuery();
                if(!rsOrigem.next()){
                    System.out.println("id" + id + " não econtrado na origem na tabela " + tabelaOrigem);
                    System.exit(0);
                }

                mensagem= "";
                for(int i=1;i<=mdDestino.getColumnCount(); i++){
                    campo= mdDestino.getColumnName(i);
                    System.out.println(" "+campo);
                    tipo= mdDestino.getColumnClassName(i);
                    if(tipo.contains("int")){
                        int valorDestino= rsDestino.getInt(campo);
                        int valorOrigem= rsOrigem.getInt(campo);
                        if(valorDestino != valorOrigem)
                            mensagem+= "Divergencia no campo " + campo
                                + ": Destino="+valorDestino
                                + "   | Origem="+valorOrigem+"\n";
                    }

                    if( (tipo.contains("Data") || tipo.contains("Timestamp")) ) {
                        Timestamp valorDestino= rsDestino.getTimestamp(campo);
                        Timestamp valorOrigem= rsOrigem.getTimestamp(campo);
                        if(!valorDestino.equals(valorOrigem))
                            mensagem+= "Divergencia no campo " + campo
                                + ": Destino="+valorDestino
                                + "   | Origem="+valorOrigem+"\n";
                    }
                     
                    if(tipo.contains("String") && !campo.contains("user_db")){
                        String valorDestino= rsDestino.getString(campo);
                        String valorOrigem= rsOrigem.getString(campo);
                        System.out.println(valorDestino);
                        System.out.println(" " + valorOrigem);
                        if(!(valorDestino==null && valorOrigem.trim().isEmpty()) && !valorDestino.trim().equals(valorOrigem.trim()))
                            mensagem+= "Divergencia no campo " + campo
                                + ": Destino="+valorDestino.trim()
                                + "   | Origem="+valorOrigem.trim()+"\n";
                    }
                }
                if(!mensagem.isEmpty()){
                    System.out.println("Tabela " + tabelaDestino + "   id=" + id + "\n" + mensagem);
                    System.exit(0);
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Campos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
