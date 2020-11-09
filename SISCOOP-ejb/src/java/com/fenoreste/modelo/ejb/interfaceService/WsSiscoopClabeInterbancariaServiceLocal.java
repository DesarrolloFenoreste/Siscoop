/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.SPEI.WsSiscoopClabeInterbancariaDTO;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface WsSiscoopClabeInterbancariaServiceLocal {

    WsSiscoopClabeInterbancariaDTO buscaClabeInterbancaria(String cuenta);

    WsSiscoopClabeInterbancariaDTO buscaClabeInterbancaria(int idorigenp, int idproducto, int idauxiliar);

}
