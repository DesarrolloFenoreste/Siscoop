/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.OrigenesDTO;
import com.fenoreste.modelo.ejb.facade.OrigenesFacade;
import com.fenoreste.modelo.ejb.interfaceService.OrigenesServiceLocal;
import com.fenoreste.modelo.entidad.Origenes;
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
public class OrigenesService implements OrigenesServiceLocal {

    @EJB
    private OrigenesFacade origenesFacade;

    EntityManager entity;

    @Override
    public OrigenesDTO buscarOrigen(Integer idorigen) {
        entity = origenesFacade.getEntityManager();
        //EntityManagerFactory emf=origenesFacade.getEntityManager();
        //entity=emf.createEntityManager();
        OrigenesDTO origenesDTO = new OrigenesDTO();
        try {
            String consulta = " SELECT o.* "
                    + "         FROM origenes o"
                    + "         WHERE o.idorigen = ? ";
            Query query = entity.createNativeQuery(consulta, Origenes.class);
            query.setParameter(1, idorigen);
            Origenes origenes = (Origenes) query.getSingleResult();
            if (origenes != null) {
                origenesDTO = fromEntity2DTO(origenes);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
        }
        entity.close();
        return origenesDTO;
    }

    private OrigenesDTO fromEntity2DTO(Origenes ori) {
        OrigenesDTO dto = new OrigenesDTO();
        try {
            BeanUtils.copyProperties(dto, ori);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de OrigenesService");
        }
        return dto;
    }

}
