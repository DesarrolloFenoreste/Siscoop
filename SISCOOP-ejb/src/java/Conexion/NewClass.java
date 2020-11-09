/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author wilmer
 */
public class NewClass {
    
    public static EntityManager getEntityManager(){  
    Properties properties = new Properties();
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + "localhost" + ":5432/" + "mitras310520");
            properties.put("javax.persistence.jdbc.user", "desarrollo");
            properties.put("javax.persistence.jdbc.password", "desarrollo");
            properties.put("javax.persistence.schema-generation.database.action", "create");
     
              
              
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SISCOOP-ejbPU",properties);
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
    
    public static void main(String[] args) {
        EntityManager manager = NewClass.getEntityManager();
        Query query=manager.createNativeQuery("select nombre from personas limit 1");
        String nom=(String)query.getSingleResult();
        System.out.println("Nombre:"+nom);
        DatoClienteDTO dto=(DatoClienteDTO) query.getSingleResult();
        
        System.out.println("Idc:"+dto.getName());
        
        
    
}
}
