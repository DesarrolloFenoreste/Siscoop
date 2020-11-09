/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.SPEI.WsSiscoopClabeInterbancariaDTO;
import com.fenoreste.modelo.ejb.facade.WsSiscoopClabeInterbancariaFacade;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopClabeInterbancariaServiceLocal;
import com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancaria;
import java.lang.reflect.InvocationTargetException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author gerardo
 */
@Stateless
public class WsSiscoopClabeInterbancariaService implements WsSiscoopClabeInterbancariaServiceLocal{

    @EJB
    private WsSiscoopClabeInterbancariaFacade wsSiscoopClabeInterbancariaFacade;

    EntityManager entity;

    @Override
    public WsSiscoopClabeInterbancariaDTO buscaClabeInterbancaria(String clabe) {
        entity = wsSiscoopClabeInterbancariaFacade.getEntityManager();
        //EntityManagerFactory emf=wsSiscoopClabeInterbancariaFacade.getEntityManager();
        //entity=emf.createEntityManager();
        WsSiscoopClabeInterbancariaDTO wsSiscoopFoliosTarjetasDTO = new WsSiscoopClabeInterbancariaDTO();
        try {
            String consulta = " SELECT w.* "
                    + "         FROM ws_siscoop_clabe_interbancaria w "
                    + "         WHERE w.clabe = ? ";
            Query query = entity.createNativeQuery(consulta, WsSiscoopClabeInterbancaria.class);
            query.setParameter(1, clabe);
            WsSiscoopClabeInterbancaria wsSiscoopFoliosTarjetas = (WsSiscoopClabeInterbancaria) query.getSingleResult();
            if (wsSiscoopFoliosTarjetas != null) {
                wsSiscoopFoliosTarjetasDTO = fromEntity2DTO(wsSiscoopFoliosTarjetas);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaClabeInterbancaria de WsSiscoopClabeInterbancariaService de la clabe " + clabe + ". " + e.getMessage());
        }
        entity.close();
        return wsSiscoopFoliosTarjetasDTO;
    }

    @Override
    public WsSiscoopClabeInterbancariaDTO buscaClabeInterbancaria(int idorigenp, int idproducto, int idauxiliar) {
        entity = wsSiscoopClabeInterbancariaFacade.getEntityManager();
        //EntityManagerFactory emf=wsSiscoopClabeInterbancariaFacade.getEntityManager();
        //entity=emf.createEntityManager();
        WsSiscoopClabeInterbancariaDTO wsSiscoopFoliosTarjetasDTO = new WsSiscoopClabeInterbancariaDTO();
        try {
            String consulta = " SELECT w.* "
                    + "         FROM ws_siscoop_clabe_interbancaria w "
                    + "         WHERE w.idorigenp = ? "
                    + "           AND w.idproducto = ? "
                    + "           AND w.idauxiliar = ? ";
            Query query = entity.createNativeQuery(consulta, WsSiscoopClabeInterbancaria.class);
            query.setParameter(1, idorigenp);
            query.setParameter(2, idproducto);
            query.setParameter(3, idauxiliar);
            WsSiscoopClabeInterbancaria wsSiscoopFoliosTarjetas = (WsSiscoopClabeInterbancaria) query.getSingleResult();
            if (wsSiscoopFoliosTarjetas != null) {
                wsSiscoopFoliosTarjetasDTO = fromEntity2DTO(wsSiscoopFoliosTarjetas);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaClabeInterbancaria de WsSiscoopClabeInterbancariaService: " + e.getMessage());
        }
        entity.close();
        return wsSiscoopFoliosTarjetasDTO;
    }

    private WsSiscoopClabeInterbancariaDTO fromEntity2DTO(WsSiscoopClabeInterbancaria clabe) {
        WsSiscoopClabeInterbancariaDTO dto = new WsSiscoopClabeInterbancariaDTO();
        try {
            BeanUtils.copyProperties(dto, clabe);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de WsSiscoopFoliosTarjetasService");
        }
        return dto;
    }

    
}
