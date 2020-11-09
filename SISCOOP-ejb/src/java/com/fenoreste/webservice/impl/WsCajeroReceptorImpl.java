/*
 * IMPLEMENTACION PARA CAJERO RECEPTOR
 */
package com.fenoreste.webservice.impl;

import com.fenoreste.modelo.dto.siscoop.ProductoDTO;
import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import com.fenoreste.modelo.dto.siscoop.CuentaUsuarioDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseDepositoACuentaDTO;
import com.fenoreste.modelo.ejb.interfaceService.SiscoopServiceLocal;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import com.fenoreste.webservice.WsCajeroReceptor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author gerardo
 */
@WebService(endpointInterface = "com.fenoreste.webservice.WsCajeroReceptor")
@Stateless()
public class WsCajeroReceptorImpl implements WsCajeroReceptor {

    @EJB
    private SiscoopServiceLocal siscoop;

    private final String idTabla = "siscoop_cajero_receptor";
    private final String conceptoPoliza = "Cajero receptor";
    private final int tipoPolizaVentanilla = 1;
    
    JpaUtil jpa = new JpaUtil();
    
    public WsCajeroReceptorImpl() {
        jpa.cambiarDispositivo(0);
    }
    
    // DATOS DEL PRODUCTO
    @Override
    public ProductoDTO wsGetAccountReq(String IdAcct, String AcctType, String AcctStatus, String TxType, String SessionId, String User, String Password) {
        // El ultimo parametro se refiere a si fue llamado desde cajero o banca movill
        return siscoop.buscaProducto(IdAcct, AcctType, AcctStatus, TxType, SessionId, User, Password, 0, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // DATOS DEL SOCIO
    @Override
    public DatoClienteDTO wsGetClientDataReq(String IdClient, String TxType, String SessionId, String User, String Password) {
        // El ultimo parametro se refiere a si fue llamado desde cajero o banca movill
        System.out.println("LLego al metodo");
        
        return siscoop.buscaCliente(IdClient, TxType, SessionId, User, Password, 0, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // LISTA DE LOS FOLIOS DEL SOCIO
    @Override
    public List<CuentaUsuarioDTO> wsGetAccountsListReq(String IdClient, String AcctType, String IdChannel, String IdTeller, String SessionId, String User, String Password) {
        // El ultimo parametro se refiere a si fue llamado desde cajero o banca movill
        return siscoop.buscarCuentasUsuario(IdClient, AcctType, IdChannel, IdTeller, SessionId, User, Password, 0, idTabla);  // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // DEPOSITO A UNA CUENTA
    @Override
    public ResponseDepositoACuentaDTO wsDepositAccountReq(String IdTeller, String IdAcct, String TxType, Integer IdOperation, String Date, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password) {
        // idelemento = usuario, IdTeller = identificador cajero, IdAcct = opa, TxType = tipo de producto, IdOperation = id registrado, Amount1 = monto a depositar, Amount2 = cambio, Reference = texto de referencia, Concept = concepto,  
        // SessionId = sesion del cajero, User = usaurio del cajero, Password = pass del cajero, tipoPolizaVentanilla = tipo de poliza, conceptoPoliza = concepto de la poliza, 0 = lo llamo el Cajero, false = es condonacion, true = es poliza de resumen
        return siscoop.depositoACuenta(IdTeller, IdAcct, TxType, IdOperation, Date, Amount1, Amount2, Reference, Concept, SessionId, User, Password, tipoPolizaVentanilla, conceptoPoliza, 0, false, true, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI                  
    }

}
