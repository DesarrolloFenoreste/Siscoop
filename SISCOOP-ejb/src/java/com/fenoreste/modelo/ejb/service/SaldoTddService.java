/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.tdd.SaldoTddDTO;
import com.fenoreste.modelo.ejb.facade.SaldoTddFacade;
import com.fenoreste.modelo.ejb.interfaceService.SaldoTddServiceLocal;
import com.fenoreste.modelo.entidad.SaldoTdd;
import com.fenoreste.modelo.entidad.SaldoTddPK;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
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
public class SaldoTddService implements SaldoTddServiceLocal {

    @EJB
    private SaldoTddFacade saldoTddFacade;

    EntityManager entity;

    /* -------------------------------------------------------------------------
     * RETORNAN AL CLIENTE
     ------------------------------------------------------------------------ */
    @Override
    public int insertaSaldoTdd(SaldoTddPK opa, BigDecimal saldo, Date fecha) {
        SaldoTdd saldoTdd = new SaldoTdd();
        saldoTdd.setSaldoTddPK(opa);
        saldoTdd.setSaldo(saldo);
        saldoTdd.setFecha(fecha);
        int n = saldoTddFacade.inserta(saldoTdd);
        return n;
    }

    @Override
    public int actualizaSaldoTdd(SaldoTddPK opa, BigDecimal saldo, Date fecha) {
        SaldoTdd saldoTdd = busca(opa);
        saldoTdd.setSaldoTddPK(opa);
        saldoTdd.setSaldo(saldo);
        saldoTdd.setFecha(fecha);
        int n = saldoTddFacade.actualiza(saldoTdd);
        return n;
    }

    @Override
    public SaldoTddDTO buscaSaldoTDD(SaldoTddPK opa) {
        SaldoTddDTO saldoTddDTO = new SaldoTddDTO();
        try {
            SaldoTdd saldoTdd = busca(opa);
            if (saldoTdd != null) {
                saldoTddDTO = fromEntity2DTO(saldoTdd);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
        }
        return saldoTddDTO;
    }

    public SaldoTdd busca(SaldoTddPK opa) {
        entity = saldoTddFacade.getEntityManager();
        //EntityManagerFactory emf=saldoTddFacade.getEntityManager();
        //entity=emf.createEntityManager();
        SaldoTdd saldoTdd;
        try {
            String consulta = " SELECT s.* "
                    + "         FROM saldo_tdd s "
                    + "         WHERE s.idorigenp = ? "
                    + "           AND s.idproducto = ? "
                    + "           AND s.idauxiliar = ? ";
            Query query = entity.createNativeQuery(consulta, SaldoTdd.class);
            query.setParameter(1, opa.getIdorigenp());
            query.setParameter(2, opa.getIdproducto());
            query.setParameter(3, opa.getIdauxiliar());
            saldoTdd = (SaldoTdd) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
            saldoTdd = null;
        }
        entity.close();
        return saldoTdd;

    }

    private SaldoTddDTO fromEntity2DTO(SaldoTdd opa) {
        SaldoTddDTO dto = new SaldoTddDTO();
        try {
            BeanUtils.copyProperties(dto, opa);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de SaldoTddService");
        }
        return dto;
    }

}
