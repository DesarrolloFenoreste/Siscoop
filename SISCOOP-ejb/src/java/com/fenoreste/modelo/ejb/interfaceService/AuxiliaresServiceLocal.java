/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.AuxiliaresDTO;
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface AuxiliaresServiceLocal {

    AuxiliaresDTO buscarAuxiliar(AuxiliaresPK auxiliar);

    List<AuxiliaresDTO> buscaAuxiliaresRango(int[] range);

    List<AuxiliaresDTO> buscaAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio);

    boolean existeAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio, int idproducto);

    AuxiliaresDTO buscaAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio, int idproducto);
    
}
