/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gerardo
 */
public class JpaUtil {

    private final String PU = "SISCOOP-ejbPU";
  private static String usuario;
    private static String pass;
    public static int tipoDispositivo;

    /*------------------------------------------------------------------------------
--- BANCA MOVIL ---
DROP USER sisbmovil;
CREATE USER sisbmovil createdb;
ALTER USER sisbmovil WITH PASSWORD 'sbm26n19!?';

--- SPEI ---
DROP USER sisspei;
CREATE USER sisspei createdb;
ALTER USER sisspei WITH PASSWORD 'ssi26n19?!';

--- CAJERO RECEPTOR ---
DROP USER siscajero;
CREATE USER siscajero createdb;
ALTER USER siscajero WITH PASSWORD 'scr26n19!!';
------------------------------------------------------------------------------*/
    public void cambiarDispositivo(int tipoDispositivo) {
        JpaUtil.tipoDispositivo = tipoDispositivo;
        switch (tipoDispositivo) {
            // CAJERO RECEPTOR
            case 0:
               usuario = "saicoop";
                pass = "slufpana?";
                break;
            // BANCA MOVIL
            case 1:
                usuario ="saicoop";// "sisbmovil";
                pass ="slufpana?";//sbm26n19";
                break;
            // SPEI
            case 2:
                usuario = "saicoop";
                pass = "slufpana?";
                break;
        }

        
 
         
    }
   
    public EntityManager createEntityManager(String ip, String bd) {
       
        System.out.println("IP.-" + ip + ", Base:"+ bd);
        System.out.println("Usuario:"+usuario+", Pass:"+pass);
        try {
            Properties properties = new Properties();
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + ip + ":5432/" + bd);
            properties.put("javax.persistence.jdbc.user",  /*"saicoop"*/usuario);
            properties.put("javax.persistence.jdbc.password",/*"slufpana?"*/pass);
            EntityManagerFactory emf=Persistence.createEntityManagerFactory(PU,properties);           
            return emf.createEntityManager();
        } catch (Throwable e) {
            System.err.println("Error en createEntityManager de JpaUtil tipo " + e.getMessage());
            return null;
        }
    }

}

/*

--- BANCA MOVIL ---
DROP USER sisbmovil;
CREATE USER sisbmovil createuser createdb;
ALTER USER sisbmovil WITH PASSWORD 'sbm27j15!?';


--- SPEI ---
DROP USER sisspei;
CREATE USER sisspei createuser createdb;
ALTER USER sisspei WITH PASSWORD 'ssi27j15?!';


--- CAJERO RECEPTOR ---
DROP USER siscajero;
CREATE USER siscajero createuser createdb;
ALTER USER siscajero WITH PASSWORD 'scr27j15!!';

 */
