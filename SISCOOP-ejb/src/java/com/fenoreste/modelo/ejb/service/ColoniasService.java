/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.ColoniasDTO;
import com.fenoreste.modelo.ejb.facade.ColoniasFacade;
import com.fenoreste.modelo.ejb.interfaceService.ColoniasServiceLocal;
import com.fenoreste.modelo.entidad.Colonias;
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
public class ColoniasService implements ColoniasServiceLocal {

    @EJB
    private ColoniasFacade coloniasFacade;

    EntityManager entity;

    @Override
    public ColoniasDTO buscarColonia(Integer idColonia) {
        entity = coloniasFacade.getEntityManager();
        //EntityManagerFactory emf=coloniasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        ColoniasDTO coloniasDTO = new ColoniasDTO();
        try {
            String consulta = " SELECT c.* "
                    + "         FROM colonias c"
                    + "         WHERE c.idcolonia = ? ";
            Query query = entity.createNativeQuery(consulta, Colonias.class);
            query.setParameter(1, idColonia);
            Colonias colonias = (Colonias) query.getSingleResult();
            if (colonias != null) {
                coloniasDTO = fromEntity2DTO(colonias);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarColonia de ColoniasService: " + e.getMessage());
        }
        entity.close();
        return coloniasDTO;
    }

    // --- PASA DE ENTIDAD A DTO ---
    private ColoniasDTO fromEntity2DTO(Colonias col) {
        ColoniasDTO dto = new ColoniasDTO();
        try {
            BeanUtils.copyProperties(dto, col);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de ColoniasService");
        }
        return dto;
    }
}
