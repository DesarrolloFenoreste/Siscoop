/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.ejb.facade.TablasFacade;
import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.entidad.Tablas;
import com.fenoreste.modelo.entidad.TablasPK;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author gerardo
 */
@Stateless
public class TablasService implements TablasServiceLocal {

    @EJB
    private TablasFacade tablasFacade;

    EntityManager entity;

    @Override
    public TablasDTO buscaTabla(TablasPK tablaspk) {
        entity = tablasFacade.getEntityManager();
       // EntityManagerFactory emf=tablasFacade.getEntityManager();
        System.out.println("Buscando tabla...");
       // entity=emf.createEntityManager();
        
        System.out.println("IdTabla:"+tablaspk.getIdtabla()+" , IdElemento:"+tablaspk.getIdelemento());
        TablasDTO tablasDTO = new TablasDTO();
        try {
            String consulta = " SELECT t.* "
                    + "         FROM tablas t "
                    + "         WHERE t.idtabla = ? "
                    + "           AND t.idelemento = ? ";
            Query query = entity.createNativeQuery(consulta, Tablas.class);
            query.setParameter(1, tablaspk.getIdtabla());
            query.setParameter(2, tablaspk.getIdelemento());
            Tablas tablas = (Tablas) query.getSingleResult();
            
            if (tablas != null) {
                System.out.println("Conviertiendo tabla....");
                tablasDTO = fromEntity2DTO(tablas);
                System.out.println("Tabla Convertida:"+tablasDTO);
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar la tabla en (Tablas Services): " + e.getMessage());
        }
        entity.close();
        return tablasDTO;
    }

    @Override
    public TablasDTO buscaValorUDIS() {
        entity = tablasFacade.getEntityManager();
       // EntityManagerFactory emf=tablasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        TablasDTO tablasDTO = new TablasDTO();
        try {
            String consulta = " SELECT t.* "
                    + "         FROM tablas t "
                    + "         WHERE t.idtabla = 'valor_udi' "
                    + "         ORDER BY idelemento "
                    + "         DESC limit 1 ";
            Query query = entity.createNativeQuery(consulta, Tablas.class);
            Tablas tablas = (Tablas) query.getSingleResult();
            if (tablas != null) {
                tablasDTO = fromEntity2DTO(tablas);
            }
        } catch (Exception e) {
            System.out.println("Error en buscar UDIS de buscaValorUDIS: " + e.getMessage());
        }
        entity.close();
        return tablasDTO;
    }
   
     
   
    
    @Override
    public TablasDTO buscaTablaPuntomania() {
        entity = tablasFacade.getEntityManager();
        TablasDTO tablasDTO = new TablasDTO();
        try {
            String consulta = " SELECT t.* "
                    + "         FROM tablas t "
                    + "         WHERE t.idtabla = 'param' "
                    + "           AND t.idelemento = 'programa_puntos' "
                    + "           AND dato1 IS NOT NULL "
                    + "           AND dato1 != '' "
                    + "           AND dato1 = '1' "
                    + "           AND dato3 IS NOT NULL "
                    + "           AND dato3 != ''  "
                    + "           AND dato4 IS NOT NULL "
                    + "           AND dato4 != ''  "
                    + "           AND date((select fechatrabajo from origenes limit 1)) between date(dato3) and date(dato4) "
                    + "           limit 1 ";
            Query query = entity.createNativeQuery(consulta, Tablas.class);
            Tablas tablas = (Tablas) query.getSingleResult();
            if (tablas != null) {
                tablasDTO = fromEntity2DTO(tablas);
            }
        } catch (Exception e) {
            //System.out.println("Error en buscaTabla de TablasService: " + e.getMessage());
        }
        entity.close();
        return tablasDTO;
        
        
    }
    
    TablasDTO fromEntity2DTO(Tablas pro) {
        TablasDTO dto = new TablasDTO();
        try {
            BeanUtils.copyProperties(dto, pro);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de TablasService");
        }
        return dto;
    }
    
     public boolean validarTablaPuntomania(){
        boolean bandera=false;
        entity=AbstractFacade.getEntityManager();
        
         String consulta = " SELECT count(*) "
                    + "         FROM tablas t "
                    + "         WHERE t.idtabla = 'param' "
                    + "           AND t.idelemento = 'programa_puntos' "
                    + "           AND dato1 IS NOT NULL "
                    + "           AND dato1 != '' "
                    + "           AND dato1 = '1' "
                    + "           AND dato3 IS NOT NULL "
                    + "           AND dato3 != ''  "
                    + "           AND dato4 IS NOT NULL "
                    + "           AND dato4 != ''  "
                    + "           AND date((select fechatrabajo from origenes limit 1)) between date(dato3) and date(dato4) "
                    + "           limit 1 ";
         
        Query query=entity.createNativeQuery(consulta);
        int count=(int)query.getSingleResult();
        if(count>0){
            bandera=true;
        }else{
            bandera=false;
        }
        return bandera;
    }
    
}
