/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.BancaMovilUsuariosDTO;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface BancaMovilUsuariosServiceLocal {
    
    BancaMovilUsuariosDTO buscaAliasBancaMovil(String aliasUsuario);
    
    BancaMovilUsuariosDTO buscaAliasBancaMovil(int idorigen, int idgrupo, int idsocio);
    
}
