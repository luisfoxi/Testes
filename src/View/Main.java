/*
 *  To change this template, choose Tools | Templates
 * and open the template in the editor.
 
 */

package View;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*import Controller.SimpleDate;
import JYaml.JyamlTeste;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.View;
import jdbc.Campos;
import jdbc.JdbcOdbc;
*/

/**
 *
 * @author Luis Fernando
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String curDir = System.getProperty("user.dir");
        System.out.println(curDir);


/*        Principal p = new Principal();
        p.setVisible(true);
        //p.main();

        

        System.setOut(new TextAreaPrintStream(p.jTextArea1));
        System.setErr(new TextAreaPrintStream(p.jTextArea1));
        System.out.println("teste");
        System.out.println(10);
        try {
            try {
                System.console().wait(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("divisao por zero: " + (30 / 0));
        } finally {
            System.out.print("x");
            System.setOut(new TextAreaPrintStream(p.jTextArea1));
            JOptionPane.showMessageDialog(null, "o programa será encerrado!", "atencao", JOptionPane.WARNING_MESSAGE);
        }
        System.exit(0);
*/



        //        Campos.teste();

//        JyamlTeste.teste();


//        JdbcOdbc.testeOdbc();
/*        //SimpleDate
        SimpleDate t= new SimpleDate(new java.util.Date());
//        System.out.println(t.toString("yyyy-MM-dd"));
        System.out.println(t.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(t);
*/
  /*      t= new SimpleDate("2010-09-17 13:20:33");
        System.out.println(t.toString("yyyy-MM-dd hh:mm:ss"));
        java.util.Date y= new java.util.Date();
        Format formata= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s= formata.format(y);

        java.sql.Date d= new java.sql.Date(y.getTime());

        System.out.println(s);

        DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dh = null;
        try {
            dh = df.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(dh);

        java.sql.Date sqldate= new java.sql.Date(dh.getTime());

        System.out.println(new java.util.Date(sqldate.getTime()));
*/



                    //VariosJdbc.varios();
            //JdbcDbf.dbf();

    }

}
