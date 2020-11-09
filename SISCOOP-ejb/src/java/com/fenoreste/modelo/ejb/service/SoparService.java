/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.SoparDTO;
import com.fenoreste.modelo.ejb.facade.SoparFacade;
import com.fenoreste.modelo.ejb.interfaceService.SoparServiceLocal;
import com.fenoreste.modelo.entidad.Sopar;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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
public class SoparService implements SoparServiceLocal {

    @EJB
    private SoparFacade soparFacade;

    EntityManager entity;

    @Override
    public List<SoparDTO> buscaSopar(int idusuario, String tipo) {
        entity = soparFacade.getEntityManager();
        //EntityManagerFactory emf=soparFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<SoparDTO> listSoparDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT s.* "
                    + "         FROM sopar s "
                    + "         WHERE s.idusuario = ? "
                    + "           AND s.tipo = ? ";
            Query query = entity.createNativeQuery(consulta, Sopar.class);
            query.setParameter(1, idusuario);
            query.setParameter(2, tipo);
            List sopars = query.getResultList();
            if (!sopars.isEmpty()) {
                sopars.stream().forEach((sopar) -> {
                    listSoparDTO.add(fromEntity2DTO((Sopar) sopar));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaSopar de SoparService: " + e.getMessage());
        }
        entity.close();
        return listSoparDTO;
    }

    @Override
    public List<SoparDTO> buscaSoparTipo(String tipo) {
        entity = soparFacade.getEntityManager();
        //EntityManagerFactory emf=soparFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<SoparDTO> listSoparDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT s.* "
                    + "         FROM sopar s "
                    + "         WHERE s.tipo = ? ";
            Query query = entity.createNativeQuery(consulta, Sopar.class);
            query.setParameter(1, tipo);
            List sopars = query.getResultList();
            if (!sopars.isEmpty()) {
                sopars.stream().forEach((sopar) -> {
                    listSoparDTO.add(fromEntity2DTO((Sopar) sopar));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaSopar de SoparService: " + e.getMessage());
        }
        entity.close();
        return listSoparDTO;
    }
    
    @Override
    public List<SoparDTO> buscaSopar(String tipo) {
        entity = soparFacade.getEntityManager();
        //EntityManagerFactory emf=soparFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<SoparDTO> listSoparDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT s.idorigen, s.idgrupo, s.idsocio, s.tipo "
                    + "         FROM sopar s "
                    + "         WHERE s.tipo = ? "
                    + "         GROUP BY s.idorigen, s.idgrupo, s.idsocio, s.tipo ";
            Query query = entity.createNativeQuery(consulta, Sopar.class);
            query.setParameter(1, tipo);
            List sopars = query.getResultList();
            if (!sopars.isEmpty()) {
                sopars.stream().forEach((sopar) -> {
                    listSoparDTO.add(fromEntity2DTO((Sopar) sopar));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaSopar de SoparService: " + e.getMessage());
        }
        entity.close();
        return listSoparDTO;
    }
    
    private SoparDTO fromEntity2DTO(Sopar sop) {
        SoparDTO sdto = new SoparDTO();
        try {
            BeanUtils.copyProperties(sdto, sop);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de SoparService. " + e.getMessage());
        }
        return sdto;
    }

}
