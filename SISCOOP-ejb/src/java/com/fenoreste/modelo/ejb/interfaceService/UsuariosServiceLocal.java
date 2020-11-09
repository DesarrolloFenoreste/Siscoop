/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.UsuariosDTO;
import javax.ejb.LocalBean;

/**
 *
 * @author prometeo
 */
@LocalBean
public interface UsuariosServiceLocal {

    UsuariosDTO buscaUsuario(int idusuario);

    public int actualizaPingreso(Integer idusuario, String pingreso);

}
