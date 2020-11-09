/*
 * IMPLEMENTACION PARA TDD
 */
package com.fenoreste.webservice.impl;

import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseTransferenciaACuentaDTO;
import com.fenoreste.modelo.ejb.util.CodigoError;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import com.fenoreste.modelo.entidad.SaldoTddPK;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import com.fenoreste.webservice.WsTDD;
import com.syc.ws.endpoint.siscoop.BalanceQueryResponseDto;
import serviciosExternos.utilidades.TarjetaDeDebito;

/**
 *
 * @author gerardo
 */
@WebService(endpointInterface = "com.fenoreste.webservice.WsTDD")
@Stateless()
public class WsTDDImpl implements WsTDD {

    @EJB
    private TarjetaDeDebito tarjetaDeDebito;

    private final String idTabla = "siscoop_tdd";

    JpaUtil jpa = new JpaUtil();
    public WsTDDImpl() {
        jpa.cambiarDispositivo(1);
    }

    // VALIDA QUE ESTE ACTIVADA LA TABLA PARA LA TDD
    private boolean validaTablaTDD() {
        TablasDTO activarTDDSaldo = tarjetaDeDebito.valorTablas(idTabla, "activar_tdd");
        System.out.println("Tabla encontrada con exito:"+activarTDDSaldo);
        return "1".equals(activarTDDSaldo.getDato1());
    }

    // SALDO DE LA TDD
    @Override
    public BalanceQueryResponseDto wsSaldoTDD(String cuenta, String idusuario) {
        System.out.println("Ya llego al web service:wsSaldoTDD");
        idusuario="1224";
        if (validaTablaTDD()) {
            System.out.println("Se encontro la tabla:"+validaTablaTDD());
            AuxiliaresPK auxiliar = tarjetaDeDebito.buscarOPACuentaTDD(cuenta);
            SaldoTddPK saldoTddPK = new SaldoTddPK(auxiliar.getIdorigenp(), auxiliar.getIdproducto(), auxiliar.getIdauxiliar());
            return tarjetaDeDebito.saldoTDD(saldoTddPK, Integer.parseInt(idusuario));
        } else {
            System.out.println("Error");
            return new BalanceQueryResponseDto();
        }
    }

    // RETIRO DE LA TDD
    @Override
    public boolean wsRetiroTDD(String cuenta, String monto, String idusuario) {
        System.out.println("Ya llego al web service:wsRetiroTDD");
        System.out.println("Cuenta:"+cuenta+"\n monto:"+monto+"\n idUsuario:"+idusuario);
        if (validaTablaTDD()) {
            // Voy preparando el response con los datos que tengo disponibles
            ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
            rdepo.setResponseCode(CodigoError.CE096.getIdError());
            rdepo.setDescription(CodigoError.CE096.getMensaje());
            AuxiliaresPK auxiliar = tarjetaDeDebito.buscarOPACuentaTDD(cuenta);
            return tarjetaDeDebito.retiroTdd(rdepo, auxiliar, Double.parseDouble(monto), Integer.parseInt(idusuario));
        } else {
            return false;
        }
    }

    // DEPOSITO DE LA TDD
    @Override
    public boolean wsDepositoTDD(String cuenta, String monto, String idusuario) {
        System.out.println("Entrando al webservice:wsDepositoTDD");
        System.out.println("Cuenta:"+cuenta+"\n monto:"+monto+"\n idUsuario:"+idusuario);
        if (validaTablaTDD()) {
            // Voy preparando el response con los datos que tengo disponibles
            ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
            rdepo.setResponseCode(CodigoError.CE096.getIdError());
            rdepo.setDescription(CodigoError.CE096.getMensaje());
            AuxiliaresPK auxiliar = tarjetaDeDebito.buscarOPACuentaTDD(cuenta);
            System.out.println("RDepo:"+rdepo+"\n auxiliar:"+auxiliar+"\n monto:"+monto+"\n idusuario:"+idusuario);
            return tarjetaDeDebito.depositoTdd(rdepo, auxiliar, Double.parseDouble(monto), Integer.parseInt(idusuario));
        } else {
            return false;
        }
    }

    // ASIGNAR CUENTA TDD
    @Override
    public boolean wsAsignarCuentaTDD(String pan, String cuenta, String nombreLargo, String nombreCorto, String direccion, String colonia, String entidadFederativa, String codigoPostal, String rfc, String telefono, String telefono2, String sucursal, String producto){
        if (validaTablaTDD()) {
            return tarjetaDeDebito.asignarCuentaTDD(pan, cuenta, nombreLargo, nombreCorto, direccion, colonia, entidadFederativa, codigoPostal, rfc, telefono, telefono2, sucursal, producto);
        } else {
            return false;
        }
    }
    
}



/*

assignmentAccount : Registra una tarjeta
       Parametros : El numero de tarjeta y los datos de registro

activateCard : Activa una tarjeta
  Parametros : El numero de tarjeta

getCardholderInformation : Obtiene la informacion relacionada con una tarjeta
              Parametros : El numero de tarjeta

getLastestTransactions : Obtiene las ultimas transacciones realizadas
            Parametros : El numero de tarjeta, la cantidad de transacciones y el
                         tipo de operaciÃ³n [1 deposito; 2 retiro; 3 todas]

temporaryLock : Bloquea temporalmente una tarjeta
   Parametros : El numero de tarjeta y el tipo de operaciÃ³n
                [1 bloqueo; 2 desbloqueo]

--------------------------------------------------------------------------------

getBalanceQuery : Obtiene el saldo
     Parametros : El numero de tarjeta

loadBalance : Hace un deposito
 Parametros : El numero de tarjeta y el monto del deposito

doWithdrawalAccount : Hace un retiro
         Parametros : El numero de tarjeta y el monto del retiro

*/