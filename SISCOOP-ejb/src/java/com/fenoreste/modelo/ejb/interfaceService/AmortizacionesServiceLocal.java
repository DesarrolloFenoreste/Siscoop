/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.AmortizacionesDTO;
import com.fenoreste.modelo.entidad.AmortizacionesPK;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface AmortizacionesServiceLocal {

    AmortizacionesDTO buscaAmortizacionesProxPago(AmortizacionesPK opa);

    List<AmortizacionesDTO> amortizacionesSorteoBuenos(AmortizacionesPK opa, Date vence);

}
