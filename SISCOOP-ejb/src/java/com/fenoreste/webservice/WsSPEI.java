/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.webservice;

import com.fenoreste.modelo.dto.SPEI.ResponseAbonoSPEIDTO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author gerardo
 */
@WebService()
public interface WsSPEI {

    // --------------- Transferencia a Cuenta de otros bancos ------------------
    @WebMethod(operationName = "wsAbonoSPEIReq", action = "wsAbonoSPEIReq")
    @RequestWrapper(localName = "wsAbonoSPEIReq")
    @ResponseWrapper(localName = "wsAbonoSPEIRes")
    @WebResult(name = "wsAbonoSPEIRes")
    ResponseAbonoSPEIDTO wsAbonoSPEIReq(@WebParam(name = "Clave") String Clave,
            @WebParam(name = "FechaOperacion") String FechaOperacion,
            @WebParam(name = "InstitucionOrdenante") String InstitucionOrdenante,
            @WebParam(name = "InstitucionBeneficiaria") String InstitucionBeneficiaria,
            @WebParam(name = "ClaveRastreo") String ClaveRastreo,
            @WebParam(name = "Monto") String Monto,
            @WebParam(name = "NombreOrdenante") String NombreOrdenante,
            @WebParam(name = "TipoCuentaOrdenante") String TipoCuentaOrdenante,
            @WebParam(name = "CuentaOrdenante") String CuentaOrdenante,
            @WebParam(name = "RfcCurpOrdenante") String RfcCurpOrdenante,
            @WebParam(name = "NombreBeneficiario") String NombreBeneficiario,
            @WebParam(name = "TipoCuentaBeneficiario") String TipoCuentaBeneficiario,
            @WebParam(name = "CuentaBeneficiario") String CuentaBeneficiario,
            @WebParam(name = "RfcCurpBeneficiario") String RfcCurpBeneficiario,
            @WebParam(name = "ConceptoPago") String ConceptoPago,
            @WebParam(name = "ReferenciaNumerica") String ReferenciaNumerica,
            @WebParam(name = "Empresa") String Empresa,
            @WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

}
