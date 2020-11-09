/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.SPEI.AbonospeiDTO;
import com.fenoreste.modelo.ejb.facade.AbonoSPEIFacade;
import com.fenoreste.modelo.ejb.interfaceService.AbonoSPEIServiceLocal;
import com.fenoreste.modelo.ejb.util.SaiFunciones;
import com.fenoreste.modelo.entidad.SPEI.AbonoSPEI;
import java.math.BigDecimal;
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
public class AbonoSPEIService implements AbonoSPEIServiceLocal {

    @EJB
    private SaiFunciones saiFunciones;

    @EJB
    private AbonoSPEIFacade abonoSPEIFacade;

    EntityManager entity;

    @Override
    public int insertaAbonoSPEI(String Clave, String FechaOperacion, String InstitucionOrdenante, String InstitucionBeneficiaria, String ClaveRastreo, String Monto, String NombreOrdenante, String TipoCuentaOrdenante, String CuentaOrdenante, String RfcCurpOrdenante, String NombreBeneficiario, String TipoCuentaBeneficiario, String CuentaBeneficiario, String RfcCurpBeneficiario, String ConceptoPago, String ReferenciaNumerica, String Empresa, String IdTeller, String SessionId) {
        AbonoSPEI abonoSPEI = new AbonoSPEI();
        abonoSPEI.setClave(Clave);
        abonoSPEI.setFechaoperacion(FechaOperacion);
        abonoSPEI.setInstitucionordenante(InstitucionOrdenante);
        abonoSPEI.setInstitucionbeneficiaria(InstitucionBeneficiaria);
        abonoSPEI.setClaverastreo(ClaveRastreo);
        abonoSPEI.setMonto(new BigDecimal(Monto));
        abonoSPEI.setNombreordenante(NombreOrdenante);
        abonoSPEI.setTipocuentaordenante(TipoCuentaOrdenante);
        abonoSPEI.setCuentaordenante(CuentaOrdenante);
        abonoSPEI.setRfccurpordenante(RfcCurpOrdenante);
        abonoSPEI.setNombrebeneficiario(NombreBeneficiario);
        abonoSPEI.setTipocuentabeneficiario(TipoCuentaBeneficiario);
        abonoSPEI.setCuentabeneficiario(CuentaBeneficiario);
        abonoSPEI.setRfccurpbeneficiario(RfcCurpBeneficiario);
        abonoSPEI.setConceptopago(ConceptoPago);
        abonoSPEI.setReferencianumerica(ReferenciaNumerica);
        abonoSPEI.setEmpresa(Empresa);
        abonoSPEI.setIdteller(IdTeller);
        abonoSPEI.setSessionid(SessionId);
        abonoSPEI.setAplicado(false);
        abonoSPEI.setResponsecode("050");
        abonoSPEI.setFecha(saiFunciones.saiFechaDB("24"));
        int n = abonoSPEIFacade.inserta(abonoSPEI);
        return n;
    }

    @Override
    public int actualizaAbonoSPEI(String Clave, String ResponseCode, boolean Aplicado) {
        AbonoSPEI abonoSPEI = busca(Clave);
        abonoSPEI.setResponsecode(ResponseCode);
        abonoSPEI.setAplicado(Aplicado);
        abonoSPEI.setFechaRespuesta(saiFunciones.saiFechaDB("24"));
        int n = abonoSPEIFacade.actualiza(abonoSPEI);
        return n;
    }

    @Override
    public AbonospeiDTO buscaMovimientoSPEI(String Clave) {
        AbonospeiDTO abonospeiDTO = new AbonospeiDTO();
        try {
            AbonoSPEI abonoSPEI = busca(Clave);
            if (abonoSPEI != null) {
                abonospeiDTO = fromEntity2DTO(abonoSPEI);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaMovimientoSPEI de AbonoSPEIService: " + e.getMessage());
        }
        return abonospeiDTO;
    }

    private AbonoSPEI busca(String Clave) {
        //EntityManagerFactory emf=abonoSPEIFacade.getEntityManager();        
        entity =abonoSPEIFacade.getEntityManager();        
        AbonoSPEI abonoSPEI = new AbonoSPEI();
        try {
            String consulta = " SELECT s.* "
                    + "         FROM abonospei s "
                    + "         WHERE s.clave = ? ";
            Query query = entity.createNativeQuery(consulta, AbonoSPEI.class);
            query.setParameter(1, Clave);
            abonoSPEI = (AbonoSPEI) query.getSingleResult();
        } catch (Exception e) {
            //System.out.println("Error en busca de AbonoSPEIService: " + e.getMessage());
        }
        entity.close();
        return abonoSPEI;
    }

    @Override
    public BigDecimal montoDiarioSPEI(String Clave) {
        //EntityManagerFactory emf=abonoSPEIFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        entity = abonoSPEIFacade.getEntityManager();
        BigDecimal montoDiarioSPEI = new BigDecimal(0);
        try {
            String consulta = "SELECT COALESCE(SUM(monto), 0.00) AS montoDiario "
                    + "         FROM abonospei "
                    + "         WHERE cuentabeneficiario = ? "
                    + "           AND aplicado = true "
                    + "           AND responsecode = '000' "
                    + "           AND DATE(fecha) = (SELECT DATE(fechatrabajo) FROM origenes LIMIT 1);";
            Query query = entity.createNativeQuery(consulta);
            query.setParameter(1, Clave);
            montoDiarioSPEI = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en montoDiarioSPEI de AbonoSPEIService: " + e.getMessage());
        }
        entity.close();
        return montoDiarioSPEI;
    }

    private AbonospeiDTO fromEntity2DTO(AbonoSPEI abonoSPEI) {
        AbonospeiDTO dto = new AbonospeiDTO();
        try {
            BeanUtils.copyProperties(dto, abonoSPEI);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }

}
