/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.webservice;

import com.syc.ws.endpoint.siscoop.BalanceQueryResponseDto;
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
public interface WsTDD {

    // --------------- Consulta el saldo de la tarjeta TDD ---------------------
    @WebMethod(operationName = "wsSaldoTDDReq", action = "wsSaldoTDDReq")
    @RequestWrapper(localName = "wsSaldoTDDReq")
    @ResponseWrapper(localName = "wsSaldoTDDRes")
    @WebResult(name = "wsSaldoTDDRes")
    BalanceQueryResponseDto wsSaldoTDD(@WebParam(name = "Cuenta") String cuenta,
            @WebParam(name = "Idusuario") String idusuario);

    // --------------- Realiza un retiro a la tarjeta TDD ----------------------
    @WebMethod(operationName = "wsRetiroTDDReq", action = "wsRetiroTDDReq")
    @RequestWrapper(localName = "wsRetiroTDDReq")
    @ResponseWrapper(localName = "wsRetiroTDDRes")
    @WebResult(name = "wsRetiroTDDRes")
    boolean wsRetiroTDD(@WebParam(name = "Cuenta") String cuenta,
            @WebParam(name = "Monto") String monto,
            @WebParam(name = "Idusuario") String idusuario);
    
    // --------------- Realiza un deposito a la tarjeta TDD --------------------
    @WebMethod(operationName = "wsDepositoTDDReq", action = "wsDepositoTDDReq")
    @RequestWrapper(localName = "wsDepositoTDDReq")
    @ResponseWrapper(localName = "wsDepositoTDDRes")
    @WebResult(name = "wsDepositoTDDRes")
    boolean wsDepositoTDD(@WebParam(name = "Cuenta") String cuenta,
            @WebParam(name = "Monto") String monto,
            @WebParam(name = "Idusuario") String idusuario);
    
    // --------------- Asigna una cuenta a la tarjeta TDD ----------------------
    @WebMethod(operationName = "wsAsignarCuentaTDDReq", action = "wsAsignarCuentaTDDReq")
    @RequestWrapper(localName = "wsAsignarCuentaTDDReq")
    @ResponseWrapper(localName = "wsAsignarCuentaTDDRes")
    @WebResult(name = "wsAsignarCuentaTDDRes")
    boolean wsAsignarCuentaTDD(@WebParam(name = "Pan") String pan,
            @WebParam(name = "Cuenta") String cuenta,
            @WebParam(name = "NombreLargo") String nombreLargo,
            @WebParam(name = "NombreCorto") String nombreCorto,
            @WebParam(name = "Direccion") String direccion,
            @WebParam(name = "Colonia") String colonia,
            @WebParam(name = "EntidadFederativa") String entidadFederativa,
            @WebParam(name = "CodigoPostal") String codigoPostal,
            @WebParam(name = "Rfc") String rfc,
            @WebParam(name = "Telefono") String telefono,
            @WebParam(name = "Telefono2") String telefono2,
            @WebParam(name = "Sucursal") String sucursal,
            @WebParam(name = "Producto") String producto);
}