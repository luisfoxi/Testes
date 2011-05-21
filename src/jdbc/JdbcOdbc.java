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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando
 */
public class JdbcOdbc {
    public JdbcOdbc() {

    }
    public static void testeOdbc() {
        String url = "sun.jdbc.odbc.JdbcOdbcDriver";
        try {
            Class.forName(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcOdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection c1 = null;
        try {
            c1 = DriverManager.getConnection("jdbc:odbc:DistribuicaoPE");
        } catch (SQLException ex) {
            Logger.getLogger(JdbcOdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query1 = "SELECT incluido FROM Usuarios";
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
            Timestamp dt= rs1.getTimestamp("incluido");
            System.out.println(dt);
            DateFormat formata = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(formata.format(dt));
  //          Timestamp t= new java.util.Date().getTime();
    //        System.out.println(t);

        } catch (SQLException ex) {
            Logger.getLogger(VariosJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
}
