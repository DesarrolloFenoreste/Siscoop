/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author wilmer
 */
public class ConexionTest {
      
      
      public static void main(String[] args) {
          JpaUtil jp=new JpaUtil();
          AbstractFacade fc=new AbstractFacade(Entity.class) {};
          System.out.println("d:"+fc.getEntityManager());
          EntityManager em=fc.getEntityManager();//.createEntityManager("localhost","mitras310520");
          Query query=em.createNativeQuery("select nombre from personas limit 1");
          String nombre=(String)query.getSingleResult();
          System.out.println("Nombre:"+nombre);
    }
      
      
}
