/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.siscoop.CuentasTercerosDTO;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface CuentasTercerosServiceLocal {

    List<CuentasTercerosDTO> buscaCuentasTercerosPorOGS (CatalogoCuentasTercerosPK catalogoCuentasTercerosPK);

}
