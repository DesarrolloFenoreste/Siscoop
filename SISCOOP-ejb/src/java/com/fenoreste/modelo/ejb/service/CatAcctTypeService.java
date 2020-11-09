/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.siscoop.CatAcctTypeDTO;
import com.fenoreste.modelo.ejb.facade.CatAcctTypeFacade;
import com.fenoreste.modelo.ejb.interfaceService.CatAcctTypeServiceLocal;
import com.fenoreste.modelo.entidad.Cataccttype;
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
public class CatAcctTypeService implements CatAcctTypeServiceLocal {

    @EJB
    private CatAcctTypeFacade catAcctTypeFacade;

    EntityManager entity;

    @Override
    public List<CatAcctTypeDTO> tipoDeCuentaSiscoop() {
        entity = catAcctTypeFacade.getEntityManager();
        //EntityManagerFactory emf=catAcctTypeFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        List<CatAcctTypeDTO> listCatAcctTypeDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT c.* "
                    + "         FROM cataccttype c";
            Query query = entity.createNativeQuery(consulta, Cataccttype.class);
            List amortizaciones = query.getResultList();
            if (!amortizaciones.isEmpty()) {
                amortizaciones.stream().forEach((amortizacion) -> {
                    listCatAcctTypeDTO.add(fromEntity2DTO((Cataccttype) amortizacion));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en tipoDeCuentaSiscoop de CatAcctTypeService: " + e.getMessage());
        }
        entity.close();
        return listCatAcctTypeDTO;
    }

    private CatAcctTypeDTO fromEntity2DTO(Cataccttype aux) {
        CatAcctTypeDTO dto = new CatAcctTypeDTO();
        try {
            BeanUtils.copyProperties(dto, aux);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO, CatAcctTypeService");
        }
        return dto;
    }

}
