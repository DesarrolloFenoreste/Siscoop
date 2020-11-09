/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.EstadosDTO;
import com.fenoreste.modelo.ejb.facade.EstadosFacade;
import com.fenoreste.modelo.ejb.interfaceService.EstadosServiceLocal;
import com.fenoreste.modelo.entidad.Estados;
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
public class EstadosService implements EstadosServiceLocal {

    @EJB
    private EstadosFacade estadosFacade;

    EntityManager entity;

    @Override
    public EstadosDTO buscarEstado(Integer idEstado) {
        entity = estadosFacade.getEntityManager();
        //EntityManagerFactory emf=estadosFacade.getEntityManager();
        //entity=emf.createEntityManager();
        EstadosDTO coloniasDTO = new EstadosDTO();
        try {
            String consulta = " SELECT e.* "
                    + "         FROM estados e"
                    + "         WHERE e.idestado = ? ";
            Query query = entity.createNativeQuery(consulta, Estados.class);
            query.setParameter(1, idEstado);
            Estados estados = (Estados) query.getSingleResult();
            if (estados != null) {
                coloniasDTO = fromEntity2DTO(estados);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarEstado de EstadosService: " + e.getMessage());
        }
        entity.close();
        return coloniasDTO;
    }

    private EstadosDTO fromEntity2DTO(Estados estados) {
        EstadosDTO dto = new EstadosDTO();
        try {
            BeanUtils.copyProperties(dto, estados);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de EstadosService");
        }
        return dto;
    }

}
