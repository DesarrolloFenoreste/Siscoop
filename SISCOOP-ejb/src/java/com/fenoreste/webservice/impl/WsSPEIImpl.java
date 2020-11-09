/*
 * IMPLEMENTACION PARA SPEI
 */
package com.fenoreste.webservice.impl;

import com.fenoreste.modelo.dto.SPEI.AbonospeiDTO;
import com.fenoreste.modelo.dto.SPEI.CuentaToOpaDTO;
import com.fenoreste.modelo.dto.SPEI.ResponseAbonoSPEIDTO;
import com.fenoreste.modelo.dto.UsuariosDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseTransferenciaACuentaDTO;
import com.fenoreste.modelo.ejb.interfaceService.SiscoopServiceLocal;
import com.fenoreste.modelo.ejb.util.CodigoError;
import com.fenoreste.modelo.ejb.util.Convertidor;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import com.fenoreste.modelo.ejb.util.Validaciones;
import com.fenoreste.webservice.WsSPEI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import serviciosExternos.utilidades.SPEI;


/**
 *
 * @author gerardo
 */
@WebService(endpointInterface = "com.fenoreste.webservice.WsSPEI")
@Stateless()
public class WsSPEIImpl implements WsSPEI {

    @EJB
    private SPEI spei;
    @EJB
    private Validaciones validaciones;
    @EJB
    private SiscoopServiceLocal siscoop;
    @EJB
    private Convertidor convertidor;

    private final String idTabla = "siscoop_spei";
    //private final String listaSopar = "spei_lista_negra";
    
    JpaUtil jpa = new JpaUtil();

    public WsSPEIImpl() {
        jpa.cambiarDispositivo(2);
    }
    
    // TRANSFERENCIA A CUENTAS 
    @Override
    public ResponseAbonoSPEIDTO wsAbonoSPEIReq(String Clave, String FechaOperacion, String InstitucionOrdenante, String InstitucionBeneficiaria, String ClaveRastreo, String Monto, String NombreOrdenante, String TipoCuentaOrdenante, String CuentaOrdenante, String RfcCurpOrdenante, String NombreBeneficiario, String TipoCuentaBeneficiario, String CuentaBeneficiario, String RfcCurpBeneficiario, String ConceptoPago, String ReferenciaNumerica, String Empresa, String IdTeller, String SessionId, String User, String Password) {
        ResponseAbonoSPEIDTO resp = new ResponseAbonoSPEIDTO();
        resp.setResponseCode(CodigoError.SPEI057.getIdError()); // Error por default
        try {
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida la hora para no realizar movimientos fuera de horario
                if (siscoop.ServicioActivo(idTabla)) { // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
                    if (siscoop.estatusOrigen(siscoop.idusuario(IdTeller, idTabla))) {
                        // Usuario asignado al SPEI
                        UsuariosDTO usuario = spei.usuario(IdTeller, idTabla);
                        if (usuario.getIdusuario() > 0) {
                            // Valida que el monto sea mayor o igual al configurado en tablas 
                            Double monto_minimo = Double.parseDouble(siscoop.valorTablas(idTabla, "monto_minimo").getDato1());
                            Double monto_maximo = Double.parseDouble(siscoop.valorTablas(idTabla, "monto_maximo").getDato1());
                            if (Double.parseDouble(Monto) >= monto_minimo) {
                                // OPA asignado a la cuenta
                                CuentaToOpaDTO opa = spei.opaSPEI(TipoCuentaBeneficiario, CuentaBeneficiario);
                                if (opa != null) {
                                    // Estatus de la tarjeta - Tiene que estar desbloqueada
                                    if (!opa.isBloqueada()) {
                                        // Estatus de la tarjeta - Tiene que estar asignada
                                        if (opa.isAsignada()) {
                                            // Estatus de la tarjeta - Tiene que estar activa
                                            if (opa.isActiva()) {
                                                // Valida que el monto no pase de cierta cantidad acumulada al dia en transferencias
                                                double montoDiarioAcumulado = spei.montoDiarioSPEI(CuentaBeneficiario).doubleValue(); // Monto acumulado en el dia
                                                double acumuladoMasMonto = montoDiarioAcumulado + Double.parseDouble(Monto); // Monto acumulado en el dia mas el monto a transferir
                                                if (montoDiarioAcumulado <= monto_maximo && acumuladoMasMonto <= monto_maximo) {
                                                    ResponseTransferenciaACuentaDTO abonoSPEI = new ResponseTransferenciaACuentaDTO();
                                                    AbonospeiDTO buscaAbono = spei.buscaAbonoSPEI(Clave);
                                                    // Concepto de la poliza
                                                    String concepto = "Transferencia SPEI";
                                                    // Si la clave no existe (es null) se inserta y se aplica
                                                    if (buscaAbono.getClave() == null) {
                                                        // Inserto el log para que quede registrado que existio un movimiento
                                                        spei.insertaAbonoSPEI(Clave, FechaOperacion, InstitucionOrdenante, InstitucionBeneficiaria, ClaveRastreo, Monto, NombreOrdenante, TipoCuentaOrdenante, CuentaOrdenante, RfcCurpOrdenante, NombreBeneficiario, TipoCuentaBeneficiario, CuentaBeneficiario, RfcCurpBeneficiario, ConceptoPago, ReferenciaNumerica, Empresa, IdTeller, SessionId);
                                                        // --- Cuenta contable, numero de tarjeta, idteller, monto, tipo poliza, concepto poliza, Dato para saber si imprimir datos del origen o destino, tipo 0 = cajero, 1 = banca_movil, 2 = spei, false = condonacion, false = es poliza de resumen
                                                        abonoSPEI = siscoop.realizarTransferenciaSPEI(spei.cuentaContableSPEI(idTabla), convertidor.getOPA32(opa.getIdorigenp(), opa.getIdproducto(), opa.getIdauxiliar()), IdTeller, Monto, 1, concepto, 1, 2, false, false, idTabla); // Se considera tipopoliza = 1 para que entre en el reporte de relevantes. Antes de concepto es la poliza
                                                    } else {
                                                        // Si es la misma clave y cuenta se procesa. Si es diferente la cuenta se envia error
                                                        if (buscaAbono.getCuentabeneficiario().equals(opa.getCuenta())) {
                                                            // Si Aplicado es false, es porque esta en proceso
                                                            if (!buscaAbono.isAplicado()) {
                                                                // Si el movimiento esta en proceso se envia el valor 050 que es el que se ingreso inicialemnte
                                                                ResponseAbonoSPEIDTO respProcesando = new ResponseAbonoSPEIDTO();
                                                                respProcesando.setResponseCode(buscaAbono.getResponsecode()); // Procesando
                                                                return respProcesando;
                                                            } else {
                                                                // Se envia el codigo de response (para que el cliente sepa que ya esta procesado)
                                                                abonoSPEI.setResponseCode(buscaAbono.getResponsecode());
                                                            }
                                                        }
                                                    }
                                                    // Si se proceso se actualiza el estatus de aplicado - Ya sea 000 o 057
                                                    spei.actualizaAbonoSPEI(Clave, abonoSPEI.getResponseCode(), true);
                                                    resp.setResponseCode(abonoSPEI.getResponseCode()); // Pago realizado
                                                } else {
                                                    System.out.println("El monto sobrepasa la cantidad diaria a depositar.");
                                                }
                                            } else {
                                                System.out.println("Error la tarjeta esta inactiva.");
                                            }
                                        } else {
                                            System.out.println("Error la tarjeta no se ha asignado.");
                                        }
                                    } else {
                                        System.out.println("Error la tarjeta esta bloqueada.");
                                    }
                                } else {
                                    System.out.println("Error no existe la tarjeta TDD o la CLABE Interbancaria.");
                                }
                            } else {
                                System.out.println("Error en el monto minimo o maximo configurado.");
                            }
                        } else {
                            System.out.println("Error no existe el usuario.");
                        }
                    } else {
                        System.out.println("Error origen inactivo.");
                    }
                } else {
                    System.out.println("Error movimiento fuera de horario.");
                }
            } else {
                System.out.println("Error en validar usuario del metodo.");
            }
        } catch (Exception e) {
            System.out.println("Error en wsAbonoSPEIReq: " + e.getMessage());
        }
        return resp;
    }

}
