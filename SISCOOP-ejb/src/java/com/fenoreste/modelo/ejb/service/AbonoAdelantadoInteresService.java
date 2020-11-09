/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.ejb.facade.AbonoAdelantadoInteresFacade;
import com.fenoreste.modelo.ejb.interfaceService.AbonoAdelantadoInteresServiceLocal;
import com.fenoreste.modelo.entidad.AbonoAdelantadoInteres;
import java.math.BigDecimal;
import java.util.Date;
//import com.fenoreste.modelo.entidad.AbonoAdelantadoInteresPK;
//import java.lang.reflect.InvocationTargetException;
//import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author prometeo
 */
@Stateless
public class AbonoAdelantadoInteresService implements AbonoAdelantadoInteresServiceLocal {

    @EJB
    private AbonoAdelantadoInteresFacade abonoAdelantadoInteresFacade;

    EntityManager entity;

    @Override
    public boolean insertaAbonoAdelantoInteres(AbonoAdelantadoInteres abonint) {
        /*
        entity = abonoAdelantadoInteresFacade.getEntityManager();
        int r = 0;
        try {
            String consulta = "insert into abono_adelantado_interes(idorigenp_p, idproducto_p, idauxiliar_p, idorigenp_a, idproducto_a, idauxiliar_a, fecha_mov, fecha, interes, iva_interes, interes_ap, iva_interes_ap, aplicado, estatus_ap)values(?,?,?,?,?,?,?,?,?,?,0.0,0.0,false,0)";
            Query query = entity.createNativeQuery(consulta);
            query.setParameter(1, abonint.getAbonoAdelantadoInteresPK().getIdorigenpP());
            query.setParameter(2, abonint.getAbonoAdelantadoInteresPK().getIdproductoP());
            query.setParameter(3, abonint.getAbonoAdelantadoInteresPK().getIdauxiliarP());
            query.setParameter(4, abonint.getAbonoAdelantadoInteresPK().getIdorigenpA());
            query.setParameter(5, abonint.getAbonoAdelantadoInteresPK().getIdproductoA());
            query.setParameter(6, abonint.getAbonoAdelantadoInteresPK().getIdauxiliarA());
            query.setParameter(7, abonint.getAbonoAdelantadoInteresPK().getFechaMov());
            query.setParameter(8, abonint.getAbonoAdelantadoInteresPK().getFecha());
            query.setParameter(9, abonint.getInteres());
            query.setParameter(10, abonint.getIvaInteres());
            r = query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en insertaAbonoAdelantoInteres de AbonoAdelantadoInteresService: " + e.getMessage());
        }
        entity.close();
        if (r == 1) {
            return true;
        }
        return false;
         */
        int r = abonoAdelantadoInteresFacade.inserta(abonint);
        if (r == 1) {
            return true;
        }
        return false;
    }

}
