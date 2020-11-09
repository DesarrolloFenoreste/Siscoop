/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import com.fenoreste.modelo.entidad.WsSiscoopFoliosTarjetas;
import com.fenoreste.modelo.entidad.WsSiscoopFoliosTarjetasPK;
import com.fenoreste.modelo.entidad.ws_siscoop_foliosT;
import com.fenoreste.modelo.entidad.ws_siscoop_foliosTPK;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import serviciosExternos.SiscoopTDD;

/**
 *
 * @author wilmer
 */
public class tddPrueba {
    
     public static void main(String[] args) {
         JpaUtil jp=new JpaUtil();
       EntityManager em=jp.createEntityManager("localhost","san_nicolas310720");
       
       Query query=em.createNativeQuery("select * from ws_siscoop_folios_tarjetas where idtarjeta='5062470100000032'");
       List<Object[]>lista=query.getResultList();
       
     }
       
       
         
             
       
    }
    

