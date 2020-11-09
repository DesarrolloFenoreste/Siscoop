/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.ValidaMovimientoCajeroReceptorDTO;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface ValidaMovimientoCajeroReceptorServiceLocal {

    int insertaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String DateTime, String Amount1, String Amount2);

    ValidaMovimientoCajeroReceptorDTO buscaMovimientoCajeroReceptor(String idTeller, String idAcct, Integer idOperation);

    int actualizaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String DateTime, String Amount1, String Amount2, boolean Estatus);
    
}
