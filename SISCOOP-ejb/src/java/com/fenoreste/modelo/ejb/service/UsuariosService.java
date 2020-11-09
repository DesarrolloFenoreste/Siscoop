/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.UsuariosDTO;
import com.fenoreste.modelo.ejb.facade.UsuariosFacade;
import com.fenoreste.modelo.ejb.interfaceService.UsuariosServiceLocal;
import com.fenoreste.modelo.entidad.Usuarios;
import java.lang.reflect.InvocationTargetException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author prometeo
 */
@Stateless
public class UsuariosService implements UsuariosServiceLocal {

    @EJB
    private UsuariosFacade usuariosFacade;

    EntityManager entity;

    @Override
    public int actualizaPingreso(Integer idusuario, String pingreso) {
        Usuarios usuarios = usuarioPorId(idusuario);
        usuarios.setPingreso(pingreso);
        return usuariosFacade.actualiza(usuarios);
    }

    @Override
    public UsuariosDTO buscaUsuario(int idusuario) {
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        Usuarios usuarios = usuarioPorId(idusuario);
        if (usuarios != null) {
            usuariosDTO = fromEntity2DTO(usuarios);
        }
        return usuariosDTO;
    }

    private Usuarios usuarioPorId(int idusuario) {
        entity = usuariosFacade.getEntityManager();
        //EntityManagerFactory emf=usuariosFacade.getEntityManager();
        //entity=emf.createEntityManager();
        Usuarios usuarios;
        try {
            String consulta = " SELECT u.* "
                    + "         FROM usuarios u "
                    + "         WHERE u.idusuario = ? ";
            Query query = entity.createNativeQuery(consulta, Usuarios.class);
            query.setParameter(1, idusuario);
            usuarios = (Usuarios) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en usuarioPorId de UsuariosService: " + e.getMessage());
            usuarios = null;
        }
        entity.close();
        return usuarios;
    }

    private UsuariosDTO fromEntity2DTO(Usuarios usu) {
        UsuariosDTO dto = new UsuariosDTO();
        try {
            BeanUtils.copyProperties(dto, usu);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }

}
