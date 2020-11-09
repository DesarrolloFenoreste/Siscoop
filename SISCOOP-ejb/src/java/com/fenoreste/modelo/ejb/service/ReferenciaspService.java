/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.ReferenciaspDTO;
import com.fenoreste.modelo.ejb.facade.ReferenciaspFacade;
import com.fenoreste.modelo.ejb.interfaceService.ReferenciaspServiceLocal;
import com.fenoreste.modelo.entidad.Referenciasp;
import com.fenoreste.modelo.entidad.ReferenciaspPK;
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
public class ReferenciaspService implements ReferenciaspServiceLocal {

    @EJB
    private ReferenciaspFacade referenciaspFacade;

    EntityManager entity;

    @Override
    public List<ReferenciaspDTO> buscaReferenciaspPorOPAyTRef(ReferenciaspPK rpk) {
        entity = referenciaspFacade.getEntityManager();
        //EntityManagerFactory emf=referenciaspFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<ReferenciaspDTO> listReferenciaspDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT r.* "
                    + "         FROM referenciasp r "
                    + "         WHERE r.idorigenp = ? "
                    + "           AND r.idproducto = ? "
                    + "           AND r.idauxiliar = ? "
                    + "           AND r.tiporeferencia = ? ";
            Query query = entity.createNativeQuery(consulta, Referenciasp.class);
            query.setParameter(1, rpk.getIdorigenp());
            query.setParameter(2, rpk.getIdproducto());
            query.setParameter(3, rpk.getIdauxiliar());
            query.setParameter(4, rpk.getTiporeferencia());
            List referenciasp = query.getResultList();
            if (!referenciasp.isEmpty()) {
                referenciasp.stream().forEach((referencias) -> {
                    listReferenciaspDTO.add(fromEntity2DTO((Referenciasp) referencias));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaReferenciaspPorOPAyTRef de ReferenciaspService: " + e.getMessage());
        }
        entity.close();
        return listReferenciaspDTO;
    }

    private ReferenciaspDTO fromEntity2DTO(Referenciasp rp) {
        ReferenciaspDTO dto = new ReferenciaspDTO();
        try {
            BeanUtils.copyProperties(dto, rp);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de ReferenciaspService");
        }
        return dto;
    }

}
