/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando
 */
public class VariosJdbc {
    public static void varios(){
        Connection c1= null, c2=null;
        try {
            Class.forName("org.postgresql.Driver");
            Class.forName("com.hxtt.sql.dbf.DBFDriver");
            try {
                c1 = DriverManager.getConnection("jdbc:DBF:/D:/Clientes/GBertho/Retaguarda/data");
            } catch (SQLException ex) {
                Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                c2 = DriverManager.getConnection("jdbc:postgresql://localhost/ERP","postgres","123");
            } catch (SQLException ex) {
                Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

    // ****** QUERY 1
        String query1 = "SELECT id AS idvenda FROM Vendas WHERE DTOS(data)>='20100501' AND DTOS(data)<='20100531' AND cupomfisca<>0 AND DTOS(cancelamen)<'00000000'";
        PreparedStatement ps1= null;
        try {
            ps1 = c1.prepareStatement(query1);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDbf.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs1 = null;
        try {
            rs1 = ps1.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs1.next();
        } catch (SQLException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(rs1.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

    // ****** QUERY 2
        String query2 = "SELECT descricao FROM Cor;";
        PreparedStatement ps2= null;
        try {
            ps2 = c2.prepareStatement(query2);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDbf.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs2 = null;
        try {
            rs2 = ps2.executeQuery();
            rs2.next();
            System.out.println(rs2.getString("descricao"));
        } catch (SQLException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}
