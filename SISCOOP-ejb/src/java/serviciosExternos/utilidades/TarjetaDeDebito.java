/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosExternos.utilidades;

import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseTransferenciaACuentaDTO;
import com.fenoreste.modelo.dto.tdd.WsSiscoopFoliosTarjetasDTO;
import com.fenoreste.modelo.dto.tdd.WsSiscoopResultadoFinalBancamovilDTO;
import com.fenoreste.modelo.ejb.interfaceService.SaldoTddServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopFoliosTarjetasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopResultadoFinalBancamovilServiceLocal;
import com.fenoreste.modelo.ejb.service.WsSiscoopFoliosTarjetasService_1;
import com.fenoreste.modelo.ejb.util.CodigoError;
import com.fenoreste.modelo.ejb.util.SaiFunciones;
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import com.fenoreste.modelo.entidad.SaldoTddPK;
import com.fenoreste.modelo.entidad.TablasPK;
import com.fenoreste.modelo.entidad.WsSiscoopResultadoFinalBancamovilPK;
import com.syc.ws.endpoint.siscoop.AssignmentAccount.ClientInfo;
import com.syc.ws.endpoint.siscoop.AssignmentAccountResponse;
import com.syc.ws.endpoint.siscoop.BalanceQueryResponseDto;
import com.syc.ws.endpoint.siscoop.DoWithdrawalAccountResponse;
import com.syc.ws.endpoint.siscoop.LoadBalanceResponse;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import serviciosExternos.SiscoopTDD;

/**
 *
 * @author gerardo
 */
@Stateless
public class TarjetaDeDebito {

    // -------------------------------------------------------------------------
    // ------------------------- METODOS TarjetaDeDebito -----------------------
    // -------------------------------------------------------------------------
    @EJB
    private SaiFunciones saiFunciones;
    @EJB
    private TablasServiceLocal tablasService;
    @EJB
    private WsSiscoopFoliosTarjetasServiceLocal wsSiscoopFoliosTarjetasService;
    @EJB
    private SaldoTddServiceLocal saldoTddService;
    @EJB
    private WsSiscoopResultadoFinalBancamovilServiceLocal wsSiscoopResultadoFinalBancamovilService;
    // Clase principal para realizar la conexion al wsdl de SYC
    @EJB
    private SiscoopTDD siscoopTDD;

    // PRODUCTO VALIDO PARA LA TDD
    public boolean productoTddwebservice(int idproducto) {
        try {
            // Producto de la tdd
            TablasPK tablasPK = new TablasPK("param", "producto_para_webservice");
            TablasDTO productoTDD = tablasService.buscaTabla(tablasPK);
            System.out.println("saleeeeeeeeeeeeeeeeeee:"+productoTDD.getDato2());
            return idproducto == Integer.parseInt(productoTDD.getDato2());
        } catch (NumberFormatException e) {
            System.out.println("Error en consultar producto en producto_para_webservice de TarjetaDeDebito." + e.getMessage());
        }
        return false;
    }
 
    // CONSULTA Y ACTUALIZA EL SALDO DE LA TarjetaDeDebito
    public BalanceQueryResponseDto saldoTDD(SaldoTddPK saldoTddPK, int idusuario) {
        BalanceQueryResponseDto balanceQueryResponseDto = new BalanceQueryResponseDto();
        WsSiscoopFoliosTarjetasDTO tarjeta = wsSiscoopFoliosTarjetasService.buscaTarjetaTDD(saldoTddPK.getIdorigenp(), saldoTddPK.getIdproducto(), saldoTddPK.getIdauxiliar());
        if (tarjeta.getWsSiscoopFoliosTarjetasPK() != null) {
            // La tarjeta tiene que estar activa
            if (tarjeta.getActiva()) {
                try {
                    balanceQueryResponseDto = siscoopTDD.getSiscoop().getBalanceQuery(tarjeta.getWsSiscoopFoliosTarjetasPK().getIdtarjeta());
                    if (balanceQueryResponseDto.getCode() == 1) {
                        int actualizaSaldoTdd = saldoTddService.actualizaSaldoTdd(saldoTddPK, new BigDecimal(balanceQueryResponseDto.getAvailableAmount()), saiFunciones.saiFechaDB("24"));
                        if (actualizaSaldoTdd == 0) {
                            System.out.println("Error no se actualizo el saldo de la tarjeta TDD en la tabla saldo_tdd. ");
                            balanceQueryResponseDto.setCode(0);
                            return balanceQueryResponseDto;
                        }
                    }
                    if (!BalanceQueryResponse(balanceQueryResponseDto, tarjeta, idusuario)) {
                        System.out.println("Error no se inserto el registro en el log de TDD, tabla ws_siscoop_resultado_final_bancamovil. ");
                    }
                } catch (Exception e) {
                    balanceQueryResponseDto.setDescription("Connect timed out");
                    BalanceQueryResponse(balanceQueryResponseDto, tarjeta, idusuario);
                    System.out.println("Error al consultar SYC, tiempo agotado. " + e.getMessage());
                   
                }
            } else {
                //balanceQueryResponseDto.setCode(0);
                balanceQueryResponseDto.setDescription("La tarjeta esta inactiva: " + tarjeta.getWsSiscoopFoliosTarjetasPK().getIdtarjeta());
            }
        }
        return balanceQueryResponseDto;
    }
  
    
    
    // VALIDO QUE SE RETORNEN LOS SALDOS DEL ORIGEN Y DESTINO
    public void validaSaldoTDD(ResponseTransferenciaACuentaDTO rdepo, AuxiliaresPK auxpkOrigen, AuxiliaresPK auxpkDestino, int idusuario) {
        SaldoTddPK saldoTddPKOrigen = new SaldoTddPK(auxpkOrigen.getIdorigenp(), auxpkOrigen.getIdproducto(), auxpkOrigen.getIdauxiliar());
        SaldoTddPK saldoTddPKDestino = new SaldoTddPK(auxpkDestino.getIdorigenp(), auxpkDestino.getIdproducto(), auxpkDestino.getIdauxiliar());
        // Obtenemos el saldo disponible de SYC para el origen, si no retornamos error
        if (productoTddwebservice(saldoTddPKOrigen.getIdproducto())) {
            rdepo.setXtra2("1");
            BalanceQueryResponseDto saldoTddOrigen = saldoTDD(saldoTddPKOrigen, idusuario);
            if (saldoTddOrigen.getCode() == 1) {
                rdepo.setXtra1(String.valueOf(saldoTddOrigen.getAvailableAmount())); // Se apsa el saldo al xtra1 - temporalmente
            } else {
                rdepo.setResponseCode("100");
                rdepo.setDescription(CodigoError.CE100.getMensaje());
                return;
            }
        }
        // Obtenemos el saldo disponible de SYC para el destino, si no retornamos error
        if (productoTddwebservice(saldoTddPKDestino.getIdproducto())) {
            rdepo.setXtra2("1");
            BalanceQueryResponseDto saldoTddDestino = saldoTDD(saldoTddPKDestino, idusuario);
            if (saldoTddDestino.getCode() != 1) {
                rdepo.setResponseCode("101");
                rdepo.setDescription(CodigoError.CE101.getMensaje());
            }
        }
    }
    
    
    // REALIZA EL RETIRO DE LA TARJETA TDD
    public boolean retiroTdd(ResponseTransferenciaACuentaDTO rdepo, AuxiliaresPK auxiliar, double monto, int idusuario) {
        System.out.println("Llegando a metodo de retiro");
        System.out.println("idProducto:"+auxiliar.getIdproducto());
        LoadBalanceResponse.Return loadBalanceResponse = new LoadBalanceResponse.Return();
        DoWithdrawalAccountResponse.Return doWithdrawalAccountResponse = new DoWithdrawalAccountResponse.Return();
        System.out.println("prob metodo:"+productoTddwebservice(auxiliar.getIdauxiliar()));
        if (productoTddwebservice(auxiliar.getIdproducto())) {
            System.out.println("Entro al if:"+auxiliar.getIdproducto());
            System.out.println("Monto Retirando:"+monto);
            rdepo.setXtra3("1");
            SaldoTddPK saldoTddPK = new SaldoTddPK(auxiliar.getIdorigenp(), auxiliar.getIdproducto(), auxiliar.getIdauxiliar());
            WsSiscoopFoliosTarjetasDTO tarjeta = wsSiscoopFoliosTarjetasService.buscaTarjetaTDD(saldoTddPK.getIdorigenp(), saldoTddPK.getIdproducto(), saldoTddPK.getIdauxiliar());
            System.out.println("Tarjeta:"+tarjeta);
            if (tarjeta.getWsSiscoopFoliosTarjetasPK() != null) {
                // La tarjeta tiene que estar activa
                if (tarjeta.getActiva()) {
                    // Por default es true porque se selecciona que existe error
                    boolean retiro = true;
                    try {
                        doWithdrawalAccountResponse = siscoopTDD.getSiscoop().doWithdrawalAccount(tarjeta.getWsSiscoopFoliosTarjetasPK().getIdtarjeta(), monto);
                        if (doWithdrawalAccountResponse.getCode() == 0) {
                            // 0 = Existe error
                            retiro = false;
                        }
                    } catch (Exception e) {
                        retiro = errorRetiroDespositoSYC(loadBalanceResponse, e);
                    }
                    // realizar un retiro al saldo
                    doWithdrawalAccountResponse(doWithdrawalAccountResponse, tarjeta, idusuario, monto, "description");
                    if (!retiro) {
                        rdepo.setResponseCode("102");
                        rdepo.setDescription(CodigoError.CE102.getMensaje());
                    }
                    return retiro;
                } else {
                    rdepo.setResponseCode("104");
                    rdepo.setDescription(CodigoError.CE104.getMensaje());
                }
            }
        }
        return true;
    }

    // REALIZA EL DEPOSITO DE LA TARJETA TDD
    public boolean depositoTdd(ResponseTransferenciaACuentaDTO rdepo, AuxiliaresPK auxiliar, double monto, int idusuario) {
        LoadBalanceResponse.Return loadBalanceResponse = new LoadBalanceResponse.Return();
        if (productoTddwebservice(auxiliar.getIdproducto())) {
            rdepo.setXtra3("1");
            SaldoTddPK saldoTddPK = new SaldoTddPK(auxiliar.getIdorigenp(), auxiliar.getIdproducto(), auxiliar.getIdauxiliar());
            WsSiscoopFoliosTarjetasDTO tarjeta = wsSiscoopFoliosTarjetasService.buscaTarjetaTDD(saldoTddPK.getIdorigenp(), saldoTddPK.getIdproducto(), saldoTddPK.getIdauxiliar());
            if (tarjeta.getWsSiscoopFoliosTarjetasPK() != null) {
                // La tarjeta tiene que estar activa
                if (tarjeta.getActiva()) {
                    // Por default es true porque se selecciona que existe error
                    boolean deposito = true;
                    try {
                        // realizar una recargar el saldo
                        loadBalanceResponse = siscoopTDD.getSiscoop().loadBalance(tarjeta.getWsSiscoopFoliosTarjetasPK().getIdtarjeta(), monto);
                        if (loadBalanceResponse.getCode() == 0) {
                            deposito = false;
                        }
                    } catch (Exception e) {
                        deposito = errorRetiroDespositoSYC(loadBalanceResponse, e);
                    }
                    loadBalanceResponse(loadBalanceResponse, tarjeta, idusuario, monto, "description");
                    if (!deposito) {
                        rdepo.setResponseCode("103");
                        rdepo.setDescription(CodigoError.CE103.getMensaje());
                    }
                    return deposito;
                } else {
                    rdepo.setResponseCode("104");
                    rdepo.setDescription(CodigoError.CE104.getMensaje());
                }
            }
        }
        return true;
    }

    // ASIGNAR CUENTA 
    public boolean asignarCuentaTDD(String pan,String cuenta, String nombreLargo, String nombreCorto, String direccion, String colonia, String entidadFederativa, String codigoPostal, String rfc, String telefono, String telefono2, String sucursal, String producto){
        AssignmentAccountResponse.Return assignmentAccount = new AssignmentAccountResponse.Return();
        
        
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setCuenta("09353495389395034");
        clientInfo.setNombreLargo("este un nombre");
        clientInfo.setNombreCorto(nombreCorto);
        clientInfo.setDireccion(direccion);
        clientInfo.setColonia(colonia);
        clientInfo.setEntidadFederativa(entidadFederativa);
        clientInfo.setCodigoPostal(codigoPostal);
        clientInfo.setRfc(rfc);
        clientInfo.setTelefono(telefono);
        clientInfo.setTelefono2(telefono2);
        clientInfo.setSucursal(sucursal);
        clientInfo.setProducto(producto);
        assignmentAccount = siscoopTDD.getSiscoop().assignmentAccount(pan, clientInfo);
        return false;
    }
    
    // ERROR AL CONSULTAR SYC TIEMPO AGOTADO
    private boolean errorRetiroDespositoSYC(LoadBalanceResponse.Return loadBalanceResponse, Exception e) {
        System.out.println("Error al consultar SYC, tiempo agotado. " + e.getMessage());
        loadBalanceResponse.setDescription("Connect timed out");
        return false;
    }

    // INSERTA EL LOG AL CONSULTAR EL SALDO DE TDD
    private boolean BalanceQueryResponse(BalanceQueryResponseDto balanceQueryResponseDto, WsSiscoopFoliosTarjetasDTO wsSiscoopFoliosTarjetasDTO, int idusuario) {
        WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovilDTO = wsSiscoopResultadoFinalBancamovil(wsSiscoopFoliosTarjetasDTO, idusuario, "getBalanceQuery");
        // Envio
        String envio = wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdtarjeta();
        wsSiscoopResultadoFinalBancamovilDTO.setEnvio("Número de Tarjeta a consultar: " + envio);
        // Respuesta
        String respuesta = "Código de respuesta: " + balanceQueryResponseDto.getCode() + " | "
                + "Descripción del Código Devuelto: " + balanceQueryResponseDto.getDescription() + " | "
                + "Saldo Disponible: " + balanceQueryResponseDto.getAvailableAmount();
        wsSiscoopResultadoFinalBancamovilDTO.setRespuesta(respuesta);
        return wsSiscoopResultadoFinalBancamovilService.insertaResponse(wsSiscoopResultadoFinalBancamovilDTO) == 1;
    }

    // INSERTA EL LOG AL DEPOSITAR EN LA TDD
    private boolean loadBalanceResponse(LoadBalanceResponse.Return loadBalanceResponse, WsSiscoopFoliosTarjetasDTO wsSiscoopFoliosTarjetasDTO, int idusuario, double amount, String description) {
        WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovilDTO = wsSiscoopResultadoFinalBancamovil(wsSiscoopFoliosTarjetasDTO, idusuario, "loadBalance");
        // Envio
        String envio = "Número de Tarjeta a consultar: " + wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdtarjeta() + " | "
                + "Importe a Abonar: " + amount + " | "
                + "Descripción de la transacción: " + description;
        wsSiscoopResultadoFinalBancamovilDTO.setEnvio(envio);
        // Respuesta
        String respuesta = "Número de Autorización: " + loadBalanceResponse.getAuthorization() + " | "
                + "Código de respuesta: " + loadBalanceResponse.getCode() + " | "
                + "Descripción: " + loadBalanceResponse.getDescription() + " | "
                + "Saldo antes del Abono: " + loadBalanceResponse.getBalance() + " | "
                + "Saldo después del Abono: " + loadBalanceResponse.getCurrentBalance();
        wsSiscoopResultadoFinalBancamovilDTO.setRespuesta(respuesta);
        return wsSiscoopResultadoFinalBancamovilService.insertaResponse(wsSiscoopResultadoFinalBancamovilDTO) == 1;
    }

    // INSERTA EL LOG AL RETIRAR EN LA TDD
    private boolean doWithdrawalAccountResponse(DoWithdrawalAccountResponse.Return doWithdrawalAccountResponse, WsSiscoopFoliosTarjetasDTO wsSiscoopFoliosTarjetasDTO, int idusuario, double amount, String description) {
        WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovilDTO = wsSiscoopResultadoFinalBancamovil(wsSiscoopFoliosTarjetasDTO, idusuario, "doWithdrawal");
        // Envio
        String envio = "Número de Tarjeta a consultar: " + wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdtarjeta() + " | "
                + "Importe a retirar: " + amount + " | "
                + "Descripción sobre el depósito: " + description;
        wsSiscoopResultadoFinalBancamovilDTO.setEnvio(envio);
        // Respuesta
        String respuesta = "Número de Autorización: " + doWithdrawalAccountResponse.getAuthorization() + " | "
                + "Código de respuesta: " + doWithdrawalAccountResponse.getCode() + " | "
                + "Descripción: " + doWithdrawalAccountResponse.getDescription() + " | "
                + "Saldo antes del retiro: " + doWithdrawalAccountResponse.getBalance() + " | "
                + "Saldo después del retiro: " + doWithdrawalAccountResponse.getCurrentBalance();
        wsSiscoopResultadoFinalBancamovilDTO.setRespuesta(respuesta);
        return wsSiscoopResultadoFinalBancamovilService.insertaResponse(wsSiscoopResultadoFinalBancamovilDTO) == 1;
    }

    // PRECARGA DE DATOS PARA WsSiscoopResultadoFinalBancamovilDTO
    private WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovil(WsSiscoopFoliosTarjetasDTO wsSiscoopFoliosTarjetasDTO, int idusuario, String metodo) {
        WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovilDTO = new WsSiscoopResultadoFinalBancamovilDTO();
        WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK = new WsSiscoopResultadoFinalBancamovilPK();
        wsSiscoopResultadoFinalBancamovilPK.setOpa(String.format("%06d", wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdorigenp())
                + String.format("%03d", wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdproducto())
                + String.format("%06d", wsSiscoopFoliosTarjetasDTO.getWsSiscoopFoliosTarjetasPK().getIdauxiliar()));
        wsSiscoopResultadoFinalBancamovilPK.setFecha(saiFunciones.saiFechaDB("24"));
        wsSiscoopResultadoFinalBancamovilPK.setMetodo(metodo);
        wsSiscoopResultadoFinalBancamovilDTO.setWsSiscoopResultadoFinalBancamovilPK(wsSiscoopResultadoFinalBancamovilPK);
        wsSiscoopResultadoFinalBancamovilDTO.setIdusuario(idusuario);
        return wsSiscoopResultadoFinalBancamovilDTO;
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    public TablasDTO valorTablas(String idtabla, String idelemento) {
        try {
            TablasPK tpkcon = new TablasPK(idtabla, idelemento);
            // obtenemos la configuracion que utilizara el cliente para mostrar los datos
            return tablasService.buscaTabla(tpkcon);
        } catch (Exception e) {
            System.out.println("No existe la tabla: " + idtabla + " con el elemento: " + idelemento + " - " + e.getMessage());
            return new TablasDTO();
        }
    }
    
    public AuxiliaresPK buscarOPACuentaTDD(String cuenta) {
        cuenta="5062470100708923";
        WsSiscoopFoliosTarjetasService_1 wsd=new WsSiscoopFoliosTarjetasService_1();
        List<Object[]>lista=wsd.buscaTarjetaTDD(cuenta);
        int o=0,p=0,a=0;  
        for(Object[]objetos:lista){
            o=Integer.parseInt(objetos[0].toString());
            p=Integer.parseInt(objetos[1].toString());
            a=Integer.parseInt(objetos[2].toString());
         }
         System.out.println("IdOrigenp:"+o+", IdProducto:"+p+", IdAuxiliar:"+a);
        //System.out.println("Regreso con exito:"+tarjeta.getActiva());
        //int idorigen=30201,idproducto=33,idauxiliar=1;
        //return new AuxiliaresPK(tarjeta.getWsSiscoopFoliosTarjetasPK().getIdorigenp(), tarjeta.getWsSiscoopFoliosTarjetasPK().getIdproducto(), tarjeta.getWsSiscoopFoliosTarjetasPK().getIdauxiliar());
        return  new AuxiliaresPK(o,p,a);
    }
    
}
