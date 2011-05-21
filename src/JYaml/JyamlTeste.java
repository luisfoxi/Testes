/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JYaml;

import Controller.SimpleDate;
import Controller.UtilidadesGerais;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Fernando
 */
public class JyamlTeste {
    public JyamlTeste(){
    }

    public static void teste() {

            SimpleDate nova= new SimpleDate(new java.util.Date());

            System.out.println(UtilidadesGerais.hostname());
            System.out.println(UtilidadesGerais.getChave("UltimaReplica.yml","Replicacao","Usuario"));
            System.out.println(UtilidadesGerais.getChave("UltimaReplica.yml","Replicacao","Empresa"));
            System.out.println("-----------");

            UtilidadesGerais.setChave("UltimaReplica.yml", "Replicacao", "Usuario", nova.toString("yyyy-MM-dd HH:mm:ss"));
            UtilidadesGerais.setChave("UltimaReplica.yml", "Novas", "Cliente", nova.toString("yyyy-MM-dd HH:mm:ss"));

            System.out.println(UtilidadesGerais.getChave("UltimaReplica.yml","Replicacao","Usuario"));
            System.out.println(UtilidadesGerais.getChave("UltimaReplica.yml","Replicacao","Empresa"));
            System.out.println(UtilidadesGerais.getChave("UltimaReplica.yml","Novas","Cliente"));


    }

}
