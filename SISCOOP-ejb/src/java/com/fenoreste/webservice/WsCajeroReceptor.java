package com.fenoreste.webservice;

import com.fenoreste.modelo.dto.siscoop.ProductoDTO;
import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import com.fenoreste.modelo.dto.siscoop.CuentaUsuarioDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseDepositoACuentaDTO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * @author gerardo
 *
 */
@WebService()
public interface WsCajeroReceptor {

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

}
