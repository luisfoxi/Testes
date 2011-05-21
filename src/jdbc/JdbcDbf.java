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
public class JdbcDbf {
     public static void dbf(){
        //The driver that will be used to access the default Data Bank
        String driver = "com.hxtt.sql.dbf.DBFDriver";
        /*
         * These are the default information to be connected to the default Data Bank, that it is
         * a DBF Data Bank
         */
        String url = "jdbc:DBF:/D:/Clientes/GBertho/Retaguarda/data";
        String user = "";
        String password = "";

        //Object Connection that will be used as a connection with the DBF Data Bank and this object
        Connection dbConnection = null;

        /*
        * Constructor Method that starts a connection with the Data Bank
        * @param String url of the Data Bank
        * @param String user that has access to the Data Bank
        * @param String password of that user to get access to the Data Bank
        */
        try{
            Class.forName( driver );
            dbConnection = DriverManager.getConnection( url, user, password );
        }
        catch( ClassNotFoundException e ){
            System.err.println( "An error ocurred while was trying to load the driver "+
            e.getMessage() );
        }
        catch( SQLException e ){
            System.err.println( "An error ocurred while was trying to connect to the Data Bank "+
            e.getMessage() );
        }

        String query = "SELECT id AS idvenda FROM Vendas WHERE DTOS(data)>='20100501' AND DTOS(data)<='20100531' AND cupomfisca<>0 AND DTOS(cancelamen)<'00000000'";
            PreparedStatement ps= null;
        try {
            ps = dbConnection.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDbf.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDbf.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
            System.out.println(rs.getRow());
        } catch (SQLException ex) {
            Logger.getLogger(JdbcDbf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

