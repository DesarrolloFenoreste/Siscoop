/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.ValidaMovimientoCajeroReceptorDTO;
import com.fenoreste.modelo.ejb.facade.ValidaMovimientoCajeroReceptorFacade;
import com.fenoreste.modelo.ejb.interfaceService.ValidaMovimientoCajeroReceptorServiceLocal;
import com.fenoreste.modelo.entidad.ValidaMovimientoCajeroReceptor;
import com.fenoreste.modelo.entidad.ValidaMovimientoCajeroReceptorPK;
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
public class ValidaMovimientoCajeroReceptorService implements ValidaMovimientoCajeroReceptorServiceLocal {

    @EJB
    private ValidaMovimientoCajeroReceptorFacade validaMovimientoCajeroReceptorFacade;

    EntityManager entity;

    /* -------------------------------------------------------------------------
     * RETORNAN AL CLIENTE
     ------------------------------------------------------------------------ */
    @Override
    public int insertaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String DateTime, String Amount1, String Amount2) {
        ValidaMovimientoCajeroReceptor validaMovimientoCajeroReceptor = new ValidaMovimientoCajeroReceptor();
        ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorpk = new ValidaMovimientoCajeroReceptorPK();
        validaMovimientoCajeroReceptorpk.setIdteller(IdTeller);
        validaMovimientoCajeroReceptorpk.setIdacct(IdAcct);
        validaMovimientoCajeroReceptorpk.setIdoperation(idOperation);
        validaMovimientoCajeroReceptor.setValidaMovimientoCajeroReceptorPK(validaMovimientoCajeroReceptorpk);
        validaMovimientoCajeroReceptor.setTxtype(TxType);
        validaMovimientoCajeroReceptor.setDatetime(DateTime);
        validaMovimientoCajeroReceptor.setAmount1(Amount1);
        validaMovimientoCajeroReceptor.setAmount2(Amount2);
        int n = validaMovimientoCajeroReceptorFacade.inserta(validaMovimientoCajeroReceptor);
        return n;
    }

    @Override
    public int actualizaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String DateTime, String Amount1, String Amount2, boolean Estatus) {
        ValidaMovimientoCajeroReceptor validaMovimientoCajeroReceptor = new ValidaMovimientoCajeroReceptor();
        ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorpk = new ValidaMovimientoCajeroReceptorPK();
        validaMovimientoCajeroReceptorpk.setIdteller(IdTeller);
        validaMovimientoCajeroReceptorpk.setIdacct(IdAcct);
        validaMovimientoCajeroReceptorpk.setIdoperation(idOperation);
        validaMovimientoCajeroReceptor.setValidaMovimientoCajeroReceptorPK(validaMovimientoCajeroReceptorpk);
        validaMovimientoCajeroReceptor.setTxtype(TxType);
        validaMovimientoCajeroReceptor.setDatetime(DateTime);
        validaMovimientoCajeroReceptor.setAmount1(Amount1);
        validaMovimientoCajeroReceptor.setAmount2(Amount2);
        validaMovimientoCajeroReceptor.setEstatus(Estatus);
        int n = validaMovimientoCajeroReceptorFacade.actualiza(validaMovimientoCajeroReceptor);
        return n;
    }

    @Override
    public ValidaMovimientoCajeroReceptorDTO buscaMovimientoCajeroReceptor(String idTeller, String idAcct, Integer idOperation) {
        entity = validaMovimientoCajeroReceptorFacade.getEntityManager();
        //EntityManagerFactory emf=validaMovimientoCajeroReceptorFacade.getEntityManager();
        //entity=emf.createEntityManager();
        ValidaMovimientoCajeroReceptorDTO validaMovimientoCajeroReceptorDTO = new ValidaMovimientoCajeroReceptorDTO();
        try {
            String consulta = " SELECT v.* "
                    + "         FROM valida_movimiento_cajero_receptor v "
                    + "         WHERE v.idteller = ? "
                    + "           AND v.idacct = ? "
                    + "           AND v.idoperation = ? ";
            Query query = entity.createNativeQuery(consulta, ValidaMovimientoCajeroReceptor.class);
            query.setParameter(1, idTeller);
            query.setParameter(2, idAcct);
            query.setParameter(3, idOperation);
            ValidaMovimientoCajeroReceptor vlidaMovimientoCajeroReceptor = (ValidaMovimientoCajeroReceptor) query.getSingleResult();
            if (vlidaMovimientoCajeroReceptor != null) {
                validaMovimientoCajeroReceptorDTO = fromEntity2DTO(vlidaMovimientoCajeroReceptor);
            }
        } catch (Exception e) {
            //System.out.println("Error en buscaMovimientoCajeroReceptor de ValidaMovimientoCajeroReceptorService: " + e.getMessage());
        }
        entity.close();
        return validaMovimientoCajeroReceptorDTO;
    }

    private ValidaMovimientoCajeroReceptorDTO fromEntity2DTO(ValidaMovimientoCajeroReceptor vmcr) {
        ValidaMovimientoCajeroReceptorDTO dto = new ValidaMovimientoCajeroReceptorDTO();
        try {
            BeanUtils.copyProperties(dto, vmcr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }

}
