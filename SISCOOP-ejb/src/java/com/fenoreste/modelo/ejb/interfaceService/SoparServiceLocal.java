/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.SoparDTO;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface SoparServiceLocal {

    List<SoparDTO> buscaSopar(int idusuario, String tipo);
    
    List<SoparDTO> buscaSoparTipo(String tipo);
    
    List<SoparDTO> buscaSopar(String tipo);

}
