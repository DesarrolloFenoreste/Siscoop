/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.SPEI.AbonospeiDTO;
import java.math.BigDecimal;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface AbonoSPEIServiceLocal {

    int insertaAbonoSPEI(String Clave, String FechaOperacion, String InstitucionOrdenante, String InstitucionBeneficiaria, String ClaveRastreo, String Monto, String NombreOrdenante, String TipoCuentaOrdenante, String CuentaOrdenante, String RfcCurpOrdenante, String NombreBeneficiario, String TipoCuentaBeneficiario, String CuentaBeneficiario, String RfcCurpBeneficiario, String ConceptoPago, String ReferenciaNumerica, String Empresa, String IdTeller, String SessionId);

    int actualizaAbonoSPEI(String Clave, String ResponseCode, boolean Aplicado);

    AbonospeiDTO buscaMovimientoSPEI(String Clave);

    BigDecimal montoDiarioSPEI(String Clave);
    
}
