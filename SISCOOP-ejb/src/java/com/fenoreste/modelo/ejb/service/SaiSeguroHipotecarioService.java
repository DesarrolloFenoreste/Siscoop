/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.SaiSeguroHipotecarioDTO;
import com.fenoreste.modelo.ejb.facade.SaiSeguroHipotecarioFacade;
import com.fenoreste.modelo.ejb.interfaceService.SaiSeguroHipotecarioServiceLocal;
import com.fenoreste.modelo.entidad.SaiSeguroHipotecario;
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
public class SaiSeguroHipotecarioService implements SaiSeguroHipotecarioServiceLocal {

    @EJB
    private SaiSeguroHipotecarioFacade saiSeguroHipotecarioFacade;

    EntityManager entity;

    @Override
    public List<SaiSeguroHipotecarioDTO> buscarPagoSeguroHipotecario(int idorigenp, int idproducto, int idauxiliar, Date fecha) {
        List<SaiSeguroHipotecarioDTO> list = new ArrayList<>(0);
        try {
            List saiSeguroHipotecario = buscaSaiSeguro(idorigenp, idproducto, idauxiliar, fecha);
            if (!saiSeguroHipotecario.isEmpty()) {
                saiSeguroHipotecario.stream().forEach((saiSeguroHipotecarioe) -> {
                    list.add(fromEntity2DTO((SaiSeguroHipotecario) saiSeguroHipotecarioe));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscarPagoSeguroHipotecario de SaiSeguroHipotecarioService: " + e.getMessage());
        }
        return list;
    }

    // BUSCA AL SOCIO CON LA ENTIDAD
    private List<SaiSeguroHipotecario> buscaSaiSeguro(int idorigenp, int idproducto, int idauxiliar, Date fecha) {
        entity = saiSeguroHipotecarioFacade.getEntityManager();
        //EntityManagerFactory emf=saiSeguroHipotecarioFacade.getEntityManager();
        //entity=emf.createEntityManager();
        String consulta = " SELECT * FROM sai_prestamos_hipotecarios_calcula_seguro_a_pagar(?, ?, ?, ?) ";
        Query query = entity.createNativeQuery(consulta, SaiSeguroHipotecario.class);
        query.setParameter(1, idorigenp);
        query.setParameter(2, idproducto);
        query.setParameter(3, idauxiliar);
        query.setParameter(4, fecha);
        List<SaiSeguroHipotecario> saiSeguro = query.getResultList();
        entity.close();
        return saiSeguro;
    }

    private SaiSeguroHipotecarioDTO fromEntity2DTO(SaiSeguroHipotecario hip) {
        SaiSeguroHipotecarioDTO dto = new SaiSeguroHipotecarioDTO();
        try {
            BeanUtils.copyProperties(dto, hip);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de SaiSeguroHipotecarioService");
        }
        return dto;
    }

}
