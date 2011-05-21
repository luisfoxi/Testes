/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Desenvolvimento01
 */
public class TextAreaPrintStream extends PrintStream{
      private JTextArea textArea;

      public TextAreaPrintStream(JTextArea area) {
         super(System.out, true);
         textArea = area;
         textArea.setCaretPosition(textArea.getText().length());
      }

      public void print(boolean b) {
         print("" + b);
      }

      public void print(char c) {
         print("" + c);
      }

      public void print(char[] s) {
         print("" + s);
      }

      public void print(double d) {
         print("" + d);
      }

      public void print(float f) {
         print("" + f);
      }

      public void print(int i) {
         print("" + i);
      }

      public void print(long l) {
         print("" + l);
      }

      public void print(Object obj) {
         print("" + obj);
      }

      public void print(String s) {
//         if(s.toLowerCase().contains("Exception in thread".toLowerCase()))
  //           JOptionPane.showMessageDialog(textArea, s, "atencao", JOptionPane.ERROR_MESSAGE);
         textArea.append("" + s);
         textArea.setCaretPosition(textArea.getText().length());
      }

      public void println(boolean x) {
         println("" + x + "\n");
      }

      public void println(char x) {
         println("" + x + "\n");
      }

      public void println(char[] x) {
         println("" + x + "\n");
      }

      public void println(double x) {
         println("" + x + "\n");
      }

      public void println(float x) {
         println("" + x + "\n");
      }

      public void println(int x) {
         println("" + x + "\n");
      }

      public void println(long x) {
         println("" + x);
      }

      public void println(Object x) {
         println("" + x);
      }

      public void println(String x) {
         print("" + x + "\n");

      }

}
