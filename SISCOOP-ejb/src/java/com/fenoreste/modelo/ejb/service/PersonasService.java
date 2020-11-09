/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.PersonasDTO;
import com.fenoreste.modelo.ejb.facade.PersonasFacade;
import com.fenoreste.modelo.ejb.interfaceService.PersonasServiceLocal;
import com.fenoreste.modelo.entidad.Personas;
import com.fenoreste.modelo.entidad.PersonasPK;
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
public class PersonasService implements PersonasServiceLocal {

    @EJB
    private PersonasFacade personasFacade;

    EntityManager entity;

    // Obtiene todos los datos de personas
    @Override
    public PersonasDTO buscarPersona(PersonasPK ogs) {
        entity = personasFacade.getEntityManager();
        //EntityManagerFactory emf=personasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        
        PersonasDTO personasDTO = new PersonasDTO();
        
        try {
            String consulta = " SELECT p.* "
                    + "         FROM personas p"
                    + "         WHERE p.idorigen = ? "
                    + "           AND p.idgrupo = ? "
                    + "           AND p.idsocio = ? ";
            Query query = entity.createNativeQuery(consulta, Personas.class);
            query.setParameter(1, ogs.getIdorigen());
            query.setParameter(2, ogs.getIdgrupo());
            query.setParameter(3, ogs.getIdsocio());
            Personas persona = (Personas) query.getSingleResult();
            if (persona != null) {
                personasDTO = fromEntity2DTO(persona);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarPersona de PersonasService: " + e.getMessage());
        }
        entity.close();
        return personasDTO;
    }

    // Obtiene los datos codificados en utf8 de personas
    @Override
    public PersonasDTO caracteresUTF8(PersonasPK ogs) {
        entity = personasFacade.getEntityManager();
        //EntityManagerFactory emf=personasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        PersonasDTO personasDTO = new PersonasDTO();
        try {
            String consulta = " SELECT p.idorigen, p.idgrupo, p.idsocio, "
                    + "                convert_from(convert(p.appaterno::bytea,'ISO_8859_1','UTF8'), 'UTF8') AS appaterno, "
                    + "                convert_from(convert(p.apmaterno::bytea,'ISO_8859_1','UTF8'), 'UTF8') AS apmaterno, "
                    + "                convert_from(convert(p.nombre::bytea,'ISO_8859_1','UTF8'), 'UTF8') AS nombre "
                    + "         FROM personas p"
                    + "         WHERE p.idorigen = ? "
                    + "           AND p.idgrupo = ? "
                    + "           AND p.idsocio = ? ";
            Query query = entity.createNativeQuery(consulta, Personas.class);
            query.setParameter(1, ogs.getIdorigen());
            query.setParameter(2, ogs.getIdgrupo());
            query.setParameter(3, ogs.getIdsocio());
            Personas persona = (Personas) query.getSingleResult();
            if (persona != null) {
                personasDTO = fromEntity2DTO(persona);
            }
        } catch (Exception e) {
            System.out.println("Error en caracteresUTF8 de PersonasService: " + e.getMessage());
        }
        entity.close();
        return personasDTO;
    }

    private PersonasDTO fromEntity2DTO(Personas persona) {
        PersonasDTO dto = new PersonasDTO();
        try {
            BeanUtils.copyProperties(dto, persona);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de PersonasService");
        }
        return dto;
    }

}
