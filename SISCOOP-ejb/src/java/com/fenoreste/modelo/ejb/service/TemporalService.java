/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.TemporalDTO;
import com.fenoreste.modelo.ejb.facade.TemporalFacade;
import com.fenoreste.modelo.ejb.interfaceService.TemporalServiceLocal;
import com.fenoreste.modelo.entidad.Temporal;
import com.fenoreste.modelo.entidad.TemporalPK;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;

/**
 *
 * @author prometeo
 */
@Stateless
public class TemporalService implements TemporalServiceLocal {

    @EJB
    private TemporalFacade temporalFacade;

    EntityManager entity;
    BigDecimal bigd = new BigDecimal(0.00);

    /* -------------------------------------------------------------------------
     * RETORNAN AL CLIENTE
     ------------------------------------------------------------------------ */
    // INSERTA EL TEMPORAL
    @Override
    public int insertaTemporal(String sesion, int idorigenp, int idproducto, int idauxiliar, int idusuario, int idorigen, int idgrupo, int idsocio, BigDecimal acapital, BigDecimal Im, BigDecimal ImCalc, BigDecimal Aiva, BigDecimal Io, BigDecimal IoCalc, BigDecimal IvaIm, BigDecimal IvaImCalc, String Idcuenta, BigDecimal IvaIo, BigDecimal IvaIoCalc, BigDecimal efectivo,String referencia, int diasVencidos, BigDecimal montoVencido, boolean esEntrada, int mov, String saiaux) {
        Temporal temporal = new Temporal();
        TemporalPK temporalpk = new TemporalPK();
        temporalpk.setIdauxiliar(idauxiliar);
        temporalpk.setIdorigenp(idorigenp);
        temporalpk.setIdproducto(idproducto);
        temporalpk.setSesion(sesion);
        temporal.setTemporalPK(temporalpk);
        temporal.setIdusuario(idusuario);
        temporal.setIdorigen(idorigen);
        temporal.setIdgrupo(idgrupo);
        temporal.setIdsocio(idsocio);
        temporal.setEsentrada(esEntrada);
        temporal.setAcapital(acapital);
        temporal.setMov(mov);
        temporal.setIoPag(Io);
        temporal.setIoCal(IoCalc);
        temporal.setImPag(Im);
        temporal.setImCal(ImCalc);
        temporal.setAiva(Aiva);
        temporal.setSaldodiacum(bigd);
        temporal.setAbonifio(bigd);
        temporal.setIdcuenta(Idcuenta);
        temporal.setAplicado(false);
        temporal.setIvaioPag(IvaIo);
        temporal.setIvaioCal(IvaIoCalc);
        temporal.setIvaimPag(IvaIm);
        temporal.setIvaimCal(IvaImCalc);
        temporal.setEfectivo(efectivo);
        temporal.setReferencia(referencia);
        temporal.setCpnpPag(bigd);
        temporal.setCpnpCal(bigd);
        temporal.setDiasvencidos(diasVencidos);
        temporal.setMontovencido(montoVencido);
        temporal.setIdorigena(0);
        temporal.setHuellaValida(false);
        temporal.setSaiAux(saiaux);
        return temporalFacade.inserta(temporal);
    }

    // ACTUALIZA EL REGISTRO DE HUELLA DIGITAL
    public void actualizaTemporalHuella(int idusuario, int idorigenp, int idproducto, int idauxiliar, int mov) {
        Temporal temporal = temporal(idusuario, idorigenp, idproducto, idauxiliar, mov);
        temporal.setHuellaValida(Boolean.TRUE);
        temporalFacade.actualiza(temporal);
    }

    // ACTUALIZA EL REGISTRO REFERENCIA
    @Override
    public void actualizaTemporalReferencia(String sesion, int idorigenp, int idproducto, int idauxiliar, int mov, String referencia) {
        System.out.println("Entro en temporal referencia");
        System.out.println("session:"+sesion+", idorigenp:"+idorigenp+", idproducto:"+idproducto+", idauxiliar:"+idauxiliar+", mov:"+mov+", referencia:"+referencia);
        System.out.println("Temporal:"+ temporal(sesion, idorigenp, idproducto, idauxiliar, mov));
        Temporal temporal = temporal(sesion, idorigenp, idproducto, idauxiliar, mov);
        System.out.println("paso para continuar:"+temporal);
        temporal.setReferencia(referencia);
        System.out.println("Llego hasta actualiza temporal referencias..");
        temporalFacade.actualiza(temporal);
    }

    // ELMINA EL TEMPORAL POR LA SESION Y EL OPA
    @Override
    public int eliminaTemporal(String sesion, int idorigenp, int idproducto, int idauxiliar) {
        Temporal temporal = temporal(sesion, idorigenp, idproducto, idauxiliar);
        return temporalFacade.elimina(temporal);
    }

    @Override
    public List<TemporalDTO> ListTemporal(String sesion) {
        entity = temporalFacade.getEntityManager();
        //EntityManagerFactory emf=temporalFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<TemporalDTO> listTemporalDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT t.* "
                    + "         FROM temporal t "
                    + "         WHERE t.sesion = ? ";
            Query query = entity.createNativeQuery(consulta, Temporal.class);
            query.setParameter(1, sesion);
            List temporals = query.getResultList();
            if (!temporals.isEmpty()) {
                temporals.stream().forEach((temporal) -> {
                    listTemporalDTO.add(fromEntity2DTO((Temporal) temporal));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en ListTemporal de TemporalService: " + e.getMessage());
        }
        entity.close();
        return listTemporalDTO;
    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    private Temporal temporal(String sesion, int idorigenp, int idproducto, int idauxiliar) {
        entity = temporalFacade.getEntityManager();
        //EntityManagerFactory emf=temporalFacade.getEntityManager();
        //entity=emf.createEntityManager();
        System.out.println("niiiuiyiyiyuiyu");
        Temporal temporal;
        System.out.println("entroooooooooooooooooo");
        try {
            String consulta = " SELECT t.* "
                    + "         FROM temporal t "
                    + "         WHERE t.sesion = ? "
                    + "           AND t.idorigenp = ? "
                    + "           AND t.idproducto = ? "
                    + "           AND t.idauxiliar = ? ";
            
            Query query = entity.createNativeQuery(consulta, Temporal.class);
            query.setParameter(1, sesion);
            query.setParameter(2, idorigenp);
            query.setParameter(3, idproducto);
            query.setParameter(4, idauxiliar);
            System.out.println("paso paso y paso");
            temporal = (Temporal) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
            temporal = null;
        }
        entity.close();
        return temporal;
    
    }

    private Temporal temporal(String sesion, int idorigenp, int idproducto, int idauxiliar, int mov) {
        System.out.println(" aqui ps");
        entity = temporalFacade.getEntityManager();
        //EntityManagerFactory emf=temporalFacade.getEntityManager();
        //entity=emf.createEntityManager();
        Temporal temporal;
        
        System.out.println(" entrando a temporal : session="+sesion+", idorigenp="+idorigenp+",idproducto="+idproducto+", idauxiliar="+ idauxiliar+",mov="+mov);
        try {
            String consulta = " SELECT t.* "
                    + "         FROM temporal t "
                    + "         WHERE t.sesion = ? "
                    + "           AND t.idorigenp = ? "
                    + "           AND t.idproducto = ? "
                    + "           AND t.idauxiliar = ? "
                    + "           AND t.mov = ? ";
            
            
            // SELECT t.* FROM temporal t WHERE t.sesion = ?    AND t.idorigenp = ?     AND t.idproducto = ?            AND t.idauxiliar = ?          AND t.mov = ? ;
                    
                    
            Query query = entity.createNativeQuery(consulta, Temporal.class);
            query.setParameter(1, sesion);
            query.setParameter(2, idorigenp);
            query.setParameter(3, idproducto);
            query.setParameter(4, idauxiliar);
            query.setParameter(5, mov);
            
            temporal = (Temporal) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
            temporal = null;
        }
        entity.close();
        return temporal;
    }

    private Temporal temporal(int idusuario, int idorigenp, int idproducto, int idauxiliar, int mov) {
        entity = temporalFacade.getEntityManager();
        //EntityManagerFactory emf=temporalFacade.getEntityManager();
        //entity=emf.createEntityManager();
        Temporal temporal;
        try {
            String consulta = " SELECT t.* "
                    + "         FROM temporal t "
                    + "         WHERE t.idusuario = ? "
                    + "           AND t.idorigenp = ? "
                    + "           AND t.idproducto = ? "
                    + "           AND t.idauxiliar = ? "
                    + "           AND t.mov = ? ";
            Query query = entity.createNativeQuery(consulta, Temporal.class);
            query.setParameter(1, idusuario);
            query.setParameter(2, idorigenp);
            query.setParameter(3, idproducto);
            query.setParameter(4, idauxiliar);
            query.setParameter(5, mov);
            temporal = (Temporal) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en buscarOrigen de OrigenesService: " + e.getMessage());
            temporal = null;
        }
        entity.close();
        return temporal;
    }

    // PASA DE ENTDAD A DTO
    private TemporalDTO fromEntity2DTO(Temporal tmp) {
        TemporalDTO temporalDTO = new TemporalDTO();
        try {
            BeanUtils.copyProperties(temporalDTO, tmp);
        } catch (IllegalAccessException | InvocationTargetException | ConversionException e) {
            System.out.println("Error en fromEntity2DTO de HuellasDpFacade tipo: " + e.getMessage());
        }
        return temporalDTO;
    }

}
