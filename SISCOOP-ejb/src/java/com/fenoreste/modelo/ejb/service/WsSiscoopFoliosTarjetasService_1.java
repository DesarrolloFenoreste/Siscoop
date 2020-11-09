/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.tdd.WsSiscoopFoliosTarjetasDTO;
import com.fenoreste.modelo.ejb.facade.WsSiscoopFoliosTarjetasFacade;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopFoliosTarjetasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopFoliosTarjetasServiceLocal_1;
import com.fenoreste.modelo.entidad.WsSiscoopFoliosTarjetas;
import java.lang.reflect.InvocationTargetException;
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
public class WsSiscoopFoliosTarjetasService_1 {

    @EJB
    private WsSiscoopFoliosTarjetasFacade wsSiscoopFoliosTarjetasFacade;

    EntityManager entity;
    // el metodo buscaTarjetaTDD(String idtarjeta) lo utiliza SPEI  -- observacion de fredy
   
    public List<Object[]> buscaTarjetaTDD(String idtarjeta) {
        System.out.println("Llego a buscar tarjeta por cuentas");
        entity = wsSiscoopFoliosTarjetasFacade.getEntityManager();
         //EntityManagerFactory emf=wsSiscoopFoliosTarjetasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<Object[]>lista=null;
        try {
            System.out.println("Llegando a consulta principal");
            String consulta = " SELECT w.* "
                    + "         FROM ws_siscoop_folios_tarjetas w "
                    + "         INNER JOIN ws_siscoop_tarjetas td using(idtarjeta)"
                    + "         WHERE w.idtarjeta = ? "
                    + "          AND td.fecha_vencimiento > (select distinct fechatrabajo from origenes limit 1) ";
            Query query = entity.createNativeQuery(consulta);
            query.setParameter(1, idtarjeta);
            System.out.println("Consulta:"+consulta);
            lista=query.getResultList();
           
            System.out.println("Todavia");
           
        } catch (Exception e) {
            System.out.println("Error en buscaTarjetaTDD de WsSiscoopFoliosTarjetasService: " + e.getMessage());
        }
        entity.close();
        return lista;
    }
// el medoto buscaTarjetaTDD(int idorigenp, int idproducto, int idauxiliar) lo utiliza Banca movil  -- observacion de fredy
   
    public WsSiscoopFoliosTarjetasDTO buscaTarjetaTDD(int idorigenp, int idproducto, int idauxiliar) {
        entity = wsSiscoopFoliosTarjetasFacade.getEntityManager();
        //EntityManagerFactory emf=wsSiscoopFoliosTarjetasFacade.getEntityManager();
        //entity=emf.createEntityManager();
        WsSiscoopFoliosTarjetasDTO wsSiscoopFoliosTarjetasDTO = new WsSiscoopFoliosTarjetasDTO();
        try {
            String consulta = " SELECT w.* "
                    + "         FROM ws_siscoop_folios_tarjetas w "
                    + "         INNER JOIN ws_siscoop_tarjetas td using(idtarjeta)"
                    + "         WHERE w.idorigenp = ? "
                    + "           AND w.idproducto = ? "
                    + "           AND w.idauxiliar = ? "
                    + "          AND td.fecha_vencimiento > (select distinct fechatrabajo from origenes limit 1) ";
            Query query = entity.createNativeQuery(consulta, WsSiscoopFoliosTarjetas.class);
            query.setParameter(1, idorigenp);
            query.setParameter(2, idproducto);
            query.setParameter(3, idauxiliar);
            WsSiscoopFoliosTarjetas wsSiscoopFoliosTarjetas = (WsSiscoopFoliosTarjetas) query.getSingleResult();
            if (wsSiscoopFoliosTarjetas != null) {
                System.out.println("wsSiscoopFoliosTarjetas" + wsSiscoopFoliosTarjetas.getWsSiscoopFoliosTarjetasPK().getIdtarjeta());
                wsSiscoopFoliosTarjetasDTO = fromEntity2DTO(wsSiscoopFoliosTarjetas);
                System.out.println("wsSiscoopFoliosTarjetasDTO" + wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdtarjeta());
            }
        } catch (Exception e) {
            System.out.println("Error en buscaTarjetaTDD de WsSiscoopFoliosTarjetasService: " + e.getMessage());
        }
        entity.close();
        return wsSiscoopFoliosTarjetasDTO;
    }

    private WsSiscoopFoliosTarjetasDTO fromEntity2DTO(WsSiscoopFoliosTarjetas tdd) {
        WsSiscoopFoliosTarjetasDTO dto = new WsSiscoopFoliosTarjetasDTO();
        try {
            BeanUtils.copyProperties(dto, tdd);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de WsSiscoopFoliosTarjetasService");
        }
        return dto;
    }

}
