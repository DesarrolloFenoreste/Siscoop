/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.webservice;

import com.fenoreste.modelo.dto.siscoop.ResponseTransferenciaACuentaDTO;
import com.fenoreste.modelo.dto.siscoop.ProductoDTO;
import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import com.fenoreste.modelo.dto.siscoop.CuentaUsuarioDTO;
import com.fenoreste.modelo.dto.siscoop.CuentasTercerosDTO;
import com.fenoreste.modelo.dto.siscoop.MovimientoCuentaDTO;
import com.fenoreste.modelo.dto.siscoop.ProductosTrasODDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseDepositoACuentaDTO;
import com.fenoreste.modelo.dto.siscoop.SaldoAuxiliarDTO;
import com.fenoreste.modelo.dto.siscoop.saiAuxiliar.SaiAuxiliarAhorroDTO;
import com.fenoreste.modelo.dto.siscoop.saiAuxiliar.SaiAuxiliarPrestamoDTO;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author gerardo
 */
@WebService()
public interface WsBancaMovil {

    // --------------- Buscar cuenta -------------------------------------------
    @WebMethod(operationName = "wsGetAccountReq", action = "wsGetAccountReq")
    @RequestWrapper(localName = "wsGetAccountReq")
    @ResponseWrapper(localName = "wsGetAccountRes")
    @WebResult(name = "wsGetAccountRes")
    ProductoDTO wsGetAccountReq(@XmlElement(required = true) @WebParam(name = "IdAcct") String IdAcct,
            @WebParam(name = "AcctType") String AcctType,
            @WebParam(name = "AcctStatus") String AcctStatus,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- Datos cliente -------------------------------------------
    @WebMethod(operationName = "wsGetClientDataReq", action = "wsGetClientDataReq")
    @RequestWrapper(localName = "wsGetClientDataReq")
    @ResponseWrapper(localName = "wsGetClientDataRes")
    @WebResult(name = "wsGetClientDataRes")
    DatoClienteDTO wsGetClientDataReq(@XmlElement(required = true) @WebParam(name = "IdClient") String IdClient,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- Lista cuentas cliente -----------------------------------
    @WebMethod(operationName = "wsGetAccountsListReq", action = "wsGetAccountsListReq")
    @RequestWrapper(localName = "wsGetAccountsListReq")
    @ResponseWrapper(localName = "wsGetAccountsListRes")
    @WebResult(name = "wsGetAccountsListRes")
    List<CuentaUsuarioDTO> wsGetAccountsListReq(@WebParam(name = "IdClient") String IdClient,
            @WebParam(name = "AcctType") String AcctType,
            @WebParam(name = "IdChannel") String IdChannel,
            @WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- busca movimientos de cuanta -----------------------------
    @WebMethod(operationName = "wsGetAccountTxsReq", action = "wsGetAccountTxsReq")
    @RequestWrapper(localName = "wsGetAccountTxsReq")
    @ResponseWrapper(localName = "wsGetAccountTxsRes")
    @WebResult(name = "wsGetAccountTxsRes")
    List<MovimientoCuentaDTO> wsGetAccountTxsReq(@WebParam(name = "IdAcct") String IdAcct,
            @WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- DepositoCuenta ------------------------------------------
    @WebMethod(operationName = "wsDepositAccountReq", action = "wsDepositAccountReq")
    @RequestWrapper(localName = "wsDepositAccountReq")
    @ResponseWrapper(localName = "wsDepositAccountRes")
    @WebResult(name = "wsDepositAccountRes")
    ResponseDepositoACuentaDTO wsDepositAccountReq(@WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "IdAcct") String IdAcct,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "IdOperation") Integer IdOperation,
            @WebParam(name = "Date") String Date,
            @WebParam(name = "Amount1") String Amount1,
            @WebParam(name = "Amount2") String Amount2,
            @WebParam(name = "Reference") String Reference,
            @WebParam(name = "Concept") String Concept,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- Transferencia a Cuenta de otros bancos ------------------
    @WebMethod(operationName = "wsTransferReq", action = "wsTransferReq")
    @RequestWrapper(localName = "wsTransferReq")
    @ResponseWrapper(localName = "wsTransferRes")
    @WebResult(name = "wsTransferRes")
    ResponseTransferenciaACuentaDTO wsTransferReq(@WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "IdAcctO") String IdAcctO,
            @WebParam(name = "IdAcctD") String IdAcctD,
            @WebParam(name = "Date") Date Date,
            @WebParam(name = "IdOperation") String IdOperation,
            @WebParam(name = "Amount1") String Amount1,
            @WebParam(name = "Amount2") String Amount2,
            @WebParam(name = "Reference") String Reference,
            @WebParam(name = "Concept") String Concept,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- Transferencias a cuenta propia --------------------------
    @WebMethod(operationName = "wsTransferOwnReq", action = "wsTransferOwnReq")
    @RequestWrapper(localName = "wsTransferOwnReq")
    @ResponseWrapper(localName = "wsTransferOwnRes")
    @WebResult(name = "wsTransferOwnRes")
    ResponseTransferenciaACuentaDTO wsTransferOwnReq(@WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "IdAcctO") String IdAcctO,
            @WebParam(name = "IdAcctD") String IdAcctD,
            @WebParam(name = "DateOper") Date DateOper,
            @WebParam(name = "DateProg") Date DateProg,
            @WebParam(name = "IdOperation") String IdOperation,
            @WebParam(name = "Amount") String Amount,
            @WebParam(name = "Fee") String Fee,
            @WebParam(name = "Reference") String Reference,
            @WebParam(name = "Concept") String Concept,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // --------------- Transferencias a cuenta de tercero ----------------------
    @WebMethod(operationName = "wsTransferThirdReq", action = "wsTransferThirdReq")
    @RequestWrapper(localName = "wsTransferThirdReq")
    @ResponseWrapper(localName = "wsTransferThirdRes")
    @WebResult(name = "wsTransferThirdRes")
    ResponseTransferenciaACuentaDTO wsTransferThirdReq(@WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "TxType") String TxType,
            @WebParam(name = "IdAcctO") String IdAcctO,
            @WebParam(name = "IdAcctD") String IdAcctD,
            @WebParam(name = "DateOper") Date DateOper,
            @WebParam(name = "DateProg") Date DateProg,
            @WebParam(name = "IdOperation") String IdOperation,
            @WebParam(name = "Amount") String Amount,
            @WebParam(name = "Fee") String Fee,
            @WebParam(name = "Reference") String Reference,
            @WebParam(name = "Concept") String Concept,
            @WebParam(name = "SessionId") String SessionId,
            @WebParam(name = "User") String User,
            @WebParam(name = "Password") String Password);

    // -------------------------------------------------------------------------
    // --------------- FUNCIONES VARIOS ----------------------------------------
    // -------------------------------------------------------------------------
    // Este metodo retorna un dto con las cuentas de origen y destino que se pueden utilizar
    @WebMethod(operationName = "wsGetAccountTrasReq", action = "wsGetAccountTrasReq")
    @RequestWrapper(localName = "wsGetAccountTrasReq")
    @ResponseWrapper(localName = "wsGetAccountTrasRes")
    @WebResult(name = "wsGetAccountTrasRes")
    ProductosTrasODDTO wsGetAccountTrasReq(@WebParam(name = "IdClient") String IdClient,
            @WebParam(name = "IdTeller") String IdTeller,
            @WebParam(name = "tipoTransferencia") String tipoTransferencia);

    // Este metodo te retorna un dto cargado con las cantidades en las que se distribuye el monto a pagar de un prestamo
    @WebMethod(operationName = "wsGetSaiAuxiliarPresReq", action = "wsGetSaiAuxiliarPresReq")
    @RequestWrapper(localName = "wsGetSaiAuxiliarPresReq")
    @ResponseWrapper(localName = "wsGetSaiAuxiliarPresRes")
    @WebResult(name = "wsGetSaiAuxiliarPresRes")
    SaiAuxiliarPrestamoDTO wsGetSaiAuxiliarPresReq(@WebParam(name = "idorigenp") int idorigenp,
            @WebParam(name = "idproducto") int idproducto,
            @WebParam(name = "idauxiliar") int idauxiliar);

    // Este metodo te retorna un dto cargado con las cantidades de un en las que se distribuye un monto de ahorro
    @WebMethod(operationName = "wsGetSaiAuxiliarAhoReq", action = "wsGetSaiAuxiliarAhoReq")
    @RequestWrapper(localName = "wsGetSaiAuxiliarAhoReq")
    @ResponseWrapper(localName = "wsGetSaiAuxiliarAhoRes")
    @WebResult(name = "wsGetSaiAuxiliarAhoRes")
    SaiAuxiliarAhorroDTO wsGetSaiAuxiliarAhoReq(@WebParam(name = "idorigenp") int idorigenp,
            @WebParam(name = "idproducto") int idproducto,
            @WebParam(name = "idauxiliar") int idauxiliar);

    // Este metodo te retorna un dto cargado con tu saldo y tu saldo disponible
    @WebMethod(operationName = "wsGetSaldoAuxiliarDTOReq", action = "wsGetSaldoAuxiliarDTOReq")
    @RequestWrapper(localName = "wsGetSaldoAuxiliarDTOReq")
    @ResponseWrapper(localName = "wsGetSaldoAuxiliarDTORes")
    @WebResult(name = "wsGetSaldoAuxiliarDTORes")
    SaldoAuxiliarDTO wsGetSaldoAuxiliarDTOReq(@WebParam(name = "idorigenp") int idorigenp,
            @WebParam(name = "idproducto") int idproducto,
            @WebParam(name = "idauxiliar") int idauxiliar,
            @WebParam(name = "idTeller") String idTeller);

    // Este metodo te retorna una lista de todas las cuentas de terceros a los que les puedes transferir
    @WebMethod(operationName = "wsGetAccountThirdByOGSReq", action = "wsGetAccountThirdByOGSReq")
    @RequestWrapper(localName = "wsGetAccountThirdByOGSReq")
    @ResponseWrapper(localName = "wsGetAccountThirdByOGSRes")
    @WebResult(name = "wsGetAccountThirdByOGSRes")
    List<CuentasTercerosDTO> wsGetAccountThirdByOGSReq(@WebParam(name = "catalogoCuentasTercerosPK") CatalogoCuentasTercerosPK catalogoCuentasTercerosPK);

}
