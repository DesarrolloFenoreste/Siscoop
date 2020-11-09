/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.AmortizacionesDTO;
import com.fenoreste.modelo.ejb.facade.AmortizacionesFacade;
import com.fenoreste.modelo.ejb.interfaceService.AmortizacionesServiceLocal;
import com.fenoreste.modelo.entidad.Amortizaciones;
import com.fenoreste.modelo.entidad.AmortizacionesPK;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
public class AmortizacionesService implements AmortizacionesServiceLocal {

    @EJB
    private AmortizacionesFacade amortizacionesFacade;

    EntityManager entity;

    // Obtiene la fecha del proximo pago de amortizaciones
    @Override
    public AmortizacionesDTO buscaAmortizacionesProxPago(AmortizacionesPK opa) {
        //entity = amortizacionesFacade.getEntityManager();
        //EntityManagerFactory emf=amortizacionesFacade.getEntityManager();        
        entity =amortizacionesFacade.getEntityManager();
        AmortizacionesDTO amortizacionesDTO = new AmortizacionesDTO();
        try {
            String consulta = " SELECT a.* "
                    + "         FROM amortizaciones a"
                    + "         WHERE a.idorigenp = ? "
                    + "           AND a.idproducto = ? "
                    + "           AND a.idauxiliar = ? "
                    + "           AND a.todopag = ? ";
            Query query = entity.createNativeQuery(consulta, Amortizaciones.class);
            query.setParameter(1, opa.getIdorigenp());
            query.setParameter(2, opa.getIdproducto());
            query.setParameter(3, opa.getIdauxiliar());
            query.setParameter(4, false);
            query.setFirstResult(0);
            query.setMaxResults(1);
            Amortizaciones amortizaciones = (Amortizaciones) query.getSingleResult();
            if (amortizaciones != null) {
                amortizacionesDTO = fromEntity2DTO(amortizaciones);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarPersona de PersonasService: " + e.getMessage());
        }
        entity.close();
        return amortizacionesDTO;
    }

    // Obtiene lso datos de amortizaciones
    @Override
    public List<AmortizacionesDTO> amortizacionesSorteoBuenos(AmortizacionesPK opa, Date vence) {
        // EntityManagerFactory emf=amortizacionesFacade.getEntityManager();        
        entity =amortizacionesFacade.getEntityManager();
        List<AmortizacionesDTO> listAmortizacionesDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT a.* "
                    + "         FROM amortizaciones a"
                    + "         WHERE a.idorigenp = ? "
                    + "           AND a.idproducto = ? "
                    + "           AND a.idauxiliar = ? "
                    + "           AND a.vence = ? "
                    + "           AND a.todopag = ? "
                    + "           AND a.atiempo = ? ";
            Query query = entity.createNativeQuery(consulta, Amortizaciones.class);
            query.setParameter(1, opa.getIdorigenp());
            query.setParameter(2, opa.getIdproducto());
            query.setParameter(3, opa.getIdauxiliar());
            query.setParameter(4, vence);
            query.setParameter(5, true);
            query.setParameter(6, true);
            List amortizaciones = query.getResultList();
            if (!amortizaciones.isEmpty()) {
                amortizaciones.stream().forEach((amortizacion) -> {
                    listAmortizacionesDTO.add(fromEntity2DTO((Amortizaciones) amortizacion));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscarPersona de PersonasService: " + e.getMessage());
        }
        entity.close();
        return listAmortizacionesDTO;
    }

    // CONVIERTE DE ENTIDAD A DTO
    private AmortizacionesDTO fromEntity2DTO(Amortizaciones aortizaciones) {
        AmortizacionesDTO dto = new AmortizacionesDTO();
        try {
            BeanUtils.copyProperties(dto, aortizaciones);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }

}
