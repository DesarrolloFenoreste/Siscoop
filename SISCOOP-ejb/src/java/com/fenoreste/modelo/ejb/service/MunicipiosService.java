/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.MunicipiosDTO;
import com.fenoreste.modelo.ejb.facade.MunicipiosFacade;
import com.fenoreste.modelo.ejb.interfaceService.MunicipiosServiceLocal;
import com.fenoreste.modelo.entidad.Municipios;
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
public class MunicipiosService implements MunicipiosServiceLocal {

    @EJB
    private MunicipiosFacade municipiosFacade;

    EntityManager entity;

    @Override
    public MunicipiosDTO buscarMunicipio(Integer idMunicipio) {
        entity = municipiosFacade.getEntityManager();
        //EntityManagerFactory emf=municipiosFacade.getEntityManager();
        //entity=emf.createEntityManager();
        MunicipiosDTO municipiosDTO = new MunicipiosDTO();
        try {
            String consulta = " SELECT m.* "
                    + "         FROM municipios m"
                    + "         WHERE m.idmunicipio = ? ";
            Query query = entity.createNativeQuery(consulta, Municipios.class);
            query.setParameter(1, idMunicipio);
            Municipios municipios = (Municipios) query.getSingleResult();
            if (municipios != null) {
                municipiosDTO = fromEntity2DTO(municipios);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarColonia de ColoniasService: " + e.getMessage());
        }
        entity.close();
        return municipiosDTO;
    }

    // --- PASA DE ENTIDAD A DTO ---
    private MunicipiosDTO fromEntity2DTO(Municipios municipios) {
        MunicipiosDTO dto = new MunicipiosDTO();
        try {
            BeanUtils.copyProperties(dto, municipios);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de ColoniasService");
        }
        return dto;
    }

}
