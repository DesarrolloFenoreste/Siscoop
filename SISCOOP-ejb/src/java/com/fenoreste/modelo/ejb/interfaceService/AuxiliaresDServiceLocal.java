/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.AuxiliaresdDTO;
import com.fenoreste.modelo.entidad.AuxiliaresDPK;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface AuxiliaresDServiceLocal {

    AuxiliaresdDTO buscarAuxiliarD(AuxiliaresDPK auxiliar, int idtipo);

    List<AuxiliaresdDTO> buscaListaDeAuxiliaresD(int idorigenp, int idproducto, int idauxiliar, int maxResult);

    BigDecimal saldoAuxiliaresD(int cargoabono, int idorigenp, int idproducto, int idauxiliar, int meses);
    
}
