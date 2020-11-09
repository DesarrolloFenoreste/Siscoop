/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.BancaMovilUsuariosDTO;
import com.fenoreste.modelo.ejb.facade.BancaMovilUsuariosFacade;
import com.fenoreste.modelo.ejb.interfaceService.BancaMovilUsuariosServiceLocal;
import com.fenoreste.modelo.entidad.BancaMovilUsuarios;
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
public class BancaMovilUsuariosService implements BancaMovilUsuariosServiceLocal {

    @EJB
    private BancaMovilUsuariosFacade bancaMovilUsuariosFacade;

    EntityManager entity;
    
    @Override
    public BancaMovilUsuariosDTO buscaAliasBancaMovil(String aliasUsuario) {
        BancaMovilUsuariosDTO bancaMovilUsuariosDTO = new BancaMovilUsuariosDTO();
        try {
            BancaMovilUsuarios bancaMovilUsuarios = buscaOGSPorAlias(aliasUsuario);
            if (bancaMovilUsuarios != null) {
                bancaMovilUsuariosDTO = fromEntity2DTO(bancaMovilUsuarios);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaAliasBancaMovil de BancaMovilUsuariosService: " + e.getMessage());
        }
        return bancaMovilUsuariosDTO;
    }

    @Override
    public BancaMovilUsuariosDTO buscaAliasBancaMovil(int idorigen, int idgrupo, int idsocio) {
        BancaMovilUsuariosDTO bancaMovilUsuariosDTO = new BancaMovilUsuariosDTO();
        try {
            BancaMovilUsuarios bancaMovilUsuarios = buscaUsuarioPorOGS(idorigen, idgrupo, idsocio);
            if (bancaMovilUsuarios != null) {
                bancaMovilUsuariosDTO = fromEntity2DTO(bancaMovilUsuarios);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaAliasBancaMovil de BancaMovilUsuariosService: " + e.getMessage());
        }
        return bancaMovilUsuariosDTO;
    }
    
    
    private BancaMovilUsuarios buscaOGSPorAlias(String aliasUsuario) {
        entity = bancaMovilUsuariosFacade.getEntityManager();
        //EntityManagerFactory emf=bancaMovilUsuariosFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        BancaMovilUsuarios bancaMovilUsuarios = new BancaMovilUsuarios();
        try {
            String consulta = " SELECT b.* "
                    + "         FROM banca_movil_usuarios b "
                    + "         WHERE b.alias_usuario = ? "
                    + "           AND b.estatus = true ";
            Query query = entity.createNativeQuery(consulta, BancaMovilUsuarios.class);
            query.setParameter(1, aliasUsuario);
            bancaMovilUsuarios = (BancaMovilUsuarios) query.getSingleResult();
        } catch (Exception e) {
            //System.out.println("Error en buscaUsuario de BancaMovilUsuariosService: " + e.getMessage());
        }
        entity.close();
        return bancaMovilUsuarios;
    }
    
    private BancaMovilUsuarios buscaUsuarioPorOGS(int idorigen, int idgrupo, int idsocio) {
        entity = bancaMovilUsuariosFacade.getEntityManager();
        //EntityManagerFactory emf=bancaMovilUsuariosFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        BancaMovilUsuarios bancaMovilUsuarios = new BancaMovilUsuarios();
        try {
            String consulta = " SELECT b.* "
                    + "         FROM banca_movil_usuarios b "
                    + "         WHERE b.idorigen = ? "
                    + "           AND b.idgrupo = ? "
                    + "           AND b.idsocio = ? "
                    + "           AND b.estatus = true ";
            Query query = entity.createNativeQuery(consulta, BancaMovilUsuarios.class);
            query.setParameter(1, idorigen);
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsocio);
            bancaMovilUsuarios = (BancaMovilUsuarios) query.getSingleResult();
        } catch (Exception e) {
            //System.out.println("Error en buscaUsuario de BancaMovilUsuariosService: " + e.getMessage());
        }
        entity.close();
        return bancaMovilUsuarios;
    }
    
    private BancaMovilUsuariosDTO fromEntity2DTO(BancaMovilUsuarios bancaMovilUsuarios) {
        BancaMovilUsuariosDTO dto = new BancaMovilUsuariosDTO();
        try {
            BeanUtils.copyProperties(dto, bancaMovilUsuarios);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }
    
}
