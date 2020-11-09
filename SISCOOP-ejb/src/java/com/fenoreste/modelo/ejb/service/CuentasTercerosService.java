/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.siscoop.CuentasTercerosDTO;
import com.fenoreste.modelo.ejb.facade.CuentasTercerosFacade;
import com.fenoreste.modelo.ejb.interfaceService.CuentasTercerosServiceLocal;
import com.fenoreste.modelo.entidad.CatalogoCuentasTerceros;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
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
public class CuentasTercerosService implements CuentasTercerosServiceLocal {

    @EJB
    private CuentasTercerosFacade cuentasTercerosFacade;

    EntityManager entity;

    @Override
    public List<CuentasTercerosDTO> buscaCuentasTercerosPorOGS(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK) {
        entity = cuentasTercerosFacade.getEntityManager();
        //EntityManagerFactory emf=cuentasTercerosFacade.getEntityManager();
        //entity=emf.createEntityManager();
        List<CuentasTercerosDTO> listCuentasTercerosDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT c.* "
                    + "         FROM catalogo_cuentas_terceros c"
                    + "         WHERE c.idorigen = ? "
                    + "           AND c.idgrupo = ? "
                    + "           AND c.idsocio = ? "
                    + "           AND c.estatus = ? ";
            Query query = entity.createNativeQuery(consulta, CatalogoCuentasTerceros.class);
            query.setParameter(1, catalogoCuentasTercerosPK.getIdorigen());
            query.setParameter(2, catalogoCuentasTercerosPK.getIdgrupo());
            query.setParameter(3, catalogoCuentasTercerosPK.getIdsocio());
            query.setParameter(4, true);
            List amortizaciones = query.getResultList();
            if (!amortizaciones.isEmpty()) {
                amortizaciones.stream().forEach((amortizacion) -> {
                    listCuentasTercerosDTO.add(fromEntity2DTO((CatalogoCuentasTerceros) amortizacion));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaCuentasTercerosPorOGS de CuentasTercerosService: " + e.getMessage());
        }
        entity.close();
        return listCuentasTercerosDTO;
    }

    private CuentasTercerosDTO fromEntity2DTO(CatalogoCuentasTerceros catalogoCuentasTerceros) {
        CuentasTercerosDTO dto = new CuentasTercerosDTO();
        try {
            BeanUtils.copyProperties(dto, catalogoCuentasTerceros);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de CuentasTercerosService");
        }
        return dto;
    }

}
