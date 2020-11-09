/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.SaiComisionCobranzaDTO;
import com.fenoreste.modelo.ejb.facade.SaiComisionCobranzaFacade;
import com.fenoreste.modelo.ejb.interfaceService.SaiComisionCobranzaServiceLocal;
import com.fenoreste.modelo.entidad.SaiComisionCobranza;
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
public class SaiComisionCobranzaService implements SaiComisionCobranzaServiceLocal {

    @EJB
    private SaiComisionCobranzaFacade saiComisionCobranzaFacade;

    EntityManager entity;

    @Override
    public SaiComisionCobranzaDTO buscaComisionCobranza(int idorigenp, int idproducto, int idauxiliar) {
        SaiComisionCobranzaDTO salida = new SaiComisionCobranzaDTO();
        try {
            SaiComisionCobranza saiComisionCobranza = buscaComision(idorigenp, idproducto, idauxiliar);
            if (saiComisionCobranza != null) {
                salida = fromEntity2DTO(saiComisionCobranza);
            }
        } catch (Exception e) {
            //System.out.println("Error en buscaComisionCobranza de SaiComisionCobranzaService: " + e.getMessage());
        }
        return salida;
    }

    // BUSCA LA COMISION DEL OPA
    private SaiComisionCobranza buscaComision(int idorigenp, int idproducto, int idauxiliar) {
        entity = saiComisionCobranzaFacade.getEntityManager();
        //EntityManagerFactory emf=saiComisionCobranzaFacade.getEntityManager();
        //entity=emf.createEntityManager();
        String consulta = "SELECT * FROM sai_calculos_de_comision_cobranza(?, ?, ?)";
        Query query = entity.createNativeQuery(consulta); // SaiComisionCobranza.class
        query.setParameter(1, idorigenp);
        query.setParameter(2, idproducto);
        query.setParameter(3, idauxiliar);
        SaiComisionCobranza comision = (SaiComisionCobranza) query.getSingleResult();
        entity.close();
        return comision;
    }

    private SaiComisionCobranzaDTO fromEntity2DTO(SaiComisionCobranza scc) {
        SaiComisionCobranzaDTO dto = new SaiComisionCobranzaDTO();
        try {
            BeanUtils.copyProperties(dto, scc);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de SaiComisionCobranzaService");
        }
        return dto;
    }

}
