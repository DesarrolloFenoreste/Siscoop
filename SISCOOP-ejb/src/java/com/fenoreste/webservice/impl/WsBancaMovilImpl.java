/*
 * IMPLEMENTACION PARA BANCA MOVIL
 */
package com.fenoreste.webservice.impl;

import com.fenoreste.modelo.dto.BancaMovilUsuariosDTO;
import com.fenoreste.modelo.dto.TablasDTO;
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
import com.fenoreste.modelo.ejb.interfaceService.BancaMovilUsuariosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SiscoopServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.ejb.util.CodigoError;
import com.fenoreste.modelo.ejb.util.Convertidor;
import com.fenoreste.modelo.ejb.util.JpaUtil;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import com.fenoreste.modelo.entidad.TablasPK;
import com.fenoreste.webservice.WsBancaMovil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
/*
import java.util.logging.Level;
import java.util.logging.Logger;
*/
/**
 *
 * @author gerardo
 */
@WebService(endpointInterface = "com.fenoreste.webservice.WsBancaMovil")
@Stateless()
public class WsBancaMovilImpl implements WsBancaMovil {

    @EJB
    private TablasServiceLocal tablasService;
    @EJB
    private BancaMovilUsuariosServiceLocal bancaMovilUsuariosService;
    @EJB
    private Convertidor convertidor;
    @EJB
    private SiscoopServiceLocal siscoop;

    private final String idTabla = "siscoop_banca_movil";
    //San Nicolas
    /*
    private final String conceptoPolizaPropia = "Traspaso CSN Movil";//"Traspaso Cuentas Propias Mitras Movil";//"Traspaso CSN Movil";
    private final String conceptoPolizaTercero = "Traspaso a Tercero CSN Movil";//"Traspaso Cuentas Terceros Mitras Movil";//"Traspaso a Tercero CSN Movil";
     */
    // Mitras
    
    private final String conceptoPolizaPropia = "Traspaso Cuentas Propias Mitras Movil";
    private final String conceptoPolizaTercero = "Traspaso Cuentas Terceros Mitras Movil";

    private final int tipoPolizaVentanilla = 1;
    private final int tipoPolizaTraspaso = 3;

    JpaUtil jpa = new JpaUtil();

    public WsBancaMovilImpl() {
        jpa.cambiarDispositivo(1);
    }

    // DATOS DEL PRODUCTO
    @Override
    public ProductoDTO wsGetAccountReq(String IdAcct, String AcctType, String AcctStatus, String TxType, String SessionId, String User, String Password) {
       /*
       Logger DAtosproducto = Logger.getLogger("Entro al wsBancamovil datos de productos ");
       DAtosproducto.log(Level.WARNING, " wsGetAccountReq(String IdAcct, String AcctType, String AcctStatus, String TxType, String SessionId, String User, String Password) ");
       */
        // El ultimo parametro se refiere a si fue llamado desde cajero o banca movil
        ProductoDTO productoDTO = siscoop.buscaProducto(IdAcct, AcctType, AcctStatus, TxType, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
        productoDTO.setIdClient(OGStoAlias(productoDTO.getIdClient())); // alias
        // si no tiene alias mandar ogs
        return productoDTO;
    }

    // DATOS DEL SOCIO
    @Override
    public DatoClienteDTO wsGetClientDataReq(String IdClient, String TxType, String SessionId, String User, String Password) {
        System.out.println("Llegando a web service");
        System.out.println("IdCliente:"+IdClient+"\n TxType:"+TxType+"\n SessionId:"+SessionId+"\n User:"+User+"\n Password:"+Password);
        /*
        Logger DAtossocio = Logger.getLogger("Entro al wsBancamovil datas de socio ");
       DAtossocio.log(Level.WARNING, " wsGetClientDataReq(String IdClient, String TxType, String SessionId, String User, String Password) ");
*/
        // Obtiene todos los datos del socio    
        DatoClienteDTO datoClienteDTO = new DatoClienteDTO();
        //   datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
        //   if (IdClient.contains("111111111")==true){
        System.out.println("datoCli:"+datoClienteDTO.getIdClient()+datoClienteDTO.getAdress1());
        String tibm = tipoIdentificacion();
        // System.out.println("tibm: " + tibm);
        System.out.println("todavia llleo:"+datoClienteDTO.getIdClient());
        
       // EntityManagerFactory emf=new 
        
        if (tibm.isEmpty() || TxType.equals("09")) {//con alias y numero de socio
            System.out.println("si entro " + TxType);
            datoClienteDTO = siscoop.buscaClienteBancaMovil(aliasToOGS(IdClient, null), TxType, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
            datoClienteDTO.setIdClient(IdClient);
            
            return datoClienteDTO;
        } else {
            /**
             * **
             */
            
            if (tibm.equals("0")) {//solo alias
                String v = aliasToOGS(IdClient, 0);
                if (v != null) {
                    datoClienteDTO = siscoop.buscaClienteBancaMovil(aliasToOGS(IdClient, null), TxType, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
                   
                    datoClienteDTO.setIdClient(IdClient);
                    return datoClienteDTO;
                } else {
                    datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
                }
            }
            /**
             * **
             */
            if (tibm.equals("1")) {//solo numero de socio  /Nota: Si no trae datos aplicar --- update tablas set dato1=1 where idtabla='siscoop_banca_movil' and idelemento='id_cliente_autorizado'; 
                
                if (IdClient.length() == 32) {
                    datoClienteDTO = siscoop.buscaClienteBancaMovil(IdClient, TxType, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
                    datoClienteDTO.setIdClient(IdClient);
                    return datoClienteDTO;
                } else {
                    datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
                }
            }
            /**
             * **
             */
        }

        // }
        return datoClienteDTO;
    }

    // LISTA DE LOS FOLIOS DEL SOCIO
    @Override
    public List<CuentaUsuarioDTO> wsGetAccountsListReq(String IdClient, String AcctType, String IdChannel, String IdTeller, String SessionId, String User, String Password) {
        /*
        Logger ListaFoliossSocio = Logger.getLogger("Entro al wsBancamovil lista de folios de socios ");
       ListaFoliossSocio.log(Level.WARNING, " wsGetAccountsListReq(String IdClient, String AcctType, String IdChannel, String IdTeller, String SessionId, String User, String Password) ");
        */
        // Numero de socio
        String socio = aliasToOGS(IdClient, null);
        // El ultimo parametro se refiere a si fue llamado desde cajero o banca movill
        List<CuentaUsuarioDTO> cuentaUsuario = siscoop.buscarCuentasUsuario(socio, AcctType, IdChannel, IdTeller, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
        List<CuentaUsuarioDTO> cuentaUsuarioDTOFinal = new ArrayList<>(0);
        cuentaUsuario.stream().map((cuentaUsuarioDTO) -> {
            cuentaUsuarioDTO.setIdClient(IdClient);
            return cuentaUsuarioDTO;
        }).forEach((cuentaUsuarioDTO) -> {
            cuentaUsuarioDTOFinal.add(cuentaUsuarioDTO);
        });
        return cuentaUsuarioDTOFinal;
    }

    // DEPOSITO A UNA CUENTA
    @Override
    public ResponseDepositoACuentaDTO wsDepositAccountReq(String IdTeller, String IdAcct, String TxType, Integer IdOperation, String Date, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password) {
        System.out.println("Llegando al web service..");
        
         // idelemento = usuario, IdTeller = identificador, IdAcct = opa, TxType = tipo de producto, IdOperation = id registrado, Date = fecha de operacion, Amount1 = monto a depositar, Amount2 = cambio, Reference = texto de referencia, Concept = concepto,  
        // SessionId = sesion del cajero, User = usaurio del cajero, Password = pass del cajero, tipoPolizaVentanilla = tipo de poliza, conceptoPoliza = concepto de la poliza, 1 = lo llamo banca movil, false = es condonacion, false = es poliza de resumen
        return siscoop.depositoACuenta(IdTeller, IdAcct, TxType, IdOperation, Date, Amount1, Amount2, Reference, Concept, SessionId, User, Password, tipoPolizaVentanilla, conceptoPolizaPropia, 1, false, true, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // TRANSFERENCIA A CUENTAS PROPIAS
    @Override
    public ResponseTransferenciaACuentaDTO wsTransferOwnReq(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date DateOper, Date DateProg, String IdOperation, String Amount, String Fee, String Reference, String Concept, String SessionId, String User, String Password) {
        /*
        Logger TRansfePropia = Logger.getLogger("Transferencia a cuenta propia");
        TRansfePropia.log(Level.WARNING, "TRANSFERENCIA A CUENTA PROPIA wsTransferOwnReq");
         */
        // idelemento = usuario, IdTeller = identificador, IdAcct = opa, TxType = tipo de producto, IdOperation = id registrado, Date = fecha de operacion, Amount1 = monto a depositar, Amount2 = cambio, Reference = texto de referencia, Concept = concepto,  
        // SessionId = sesion del cajero, User = usaurio del cajero, Password = pass del cajero, tipoPolizaVentanilla = tipo de poliza, conceptoPoliza = concepto de la poliza, 1 = lo llamo banca movil, false = es condonacion, false = es poliza de resumen
        return siscoop.transferenciaACuentaPropia(IdTeller, TxType, IdAcctO, IdAcctD, DateOper, IdOperation, Amount, Amount, Reference, Concept, SessionId, User, Password, tipoPolizaTraspaso, conceptoPolizaPropia, 1, false, false, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // TRANSFERENCIA A CUENTAS DE TERCEROS
    @Override
    public ResponseTransferenciaACuentaDTO wsTransferReq(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password) {
        /*
        Logger TRansfereReq = Logger.getLogger("Transferencia a cuenta de terceros");
        TRansfereReq.log(Level.WARNING, "TRANSFERENCIA A CUENTA DE TERCEROS wsTransferReq");
         */
        // idelemento = usuario, IdTeller = identificador, IdAcct = opa, TxType = tipo de producto, IdOperation = id registrado, Date = fecha de operacion, Amount1 = monto a depositar, Amount2 = cambio, Reference = texto de referencia, Concept = concepto,  
        // SessionId = sesion del cajero, User = usaurio del cajero, Password = pass del cajero, tipoPolizaVentanilla = tipo de poliza, conceptoPoliza = concepto de la poliza, 1 = lo llamo banca movil, false = es condonacion, false = es poliza de resumen
        return siscoop.transferenciaACuentaDeTerceros(IdTeller, TxType, IdAcctO, IdAcctD, Date, IdOperation, Amount1, Amount2, Reference, Concept, SessionId, User, Password, tipoPolizaTraspaso, conceptoPolizaTercero, 1, false, false, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // TRANSFERENCIA A CUENTAS TERCEROS
    @Override
    public ResponseTransferenciaACuentaDTO wsTransferThirdReq(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date DateOper, Date DateProg, String IdOperation, String Amount, String Fee, String Reference, String Concept, String SessionId, String User, String Password) {
        /*
        Logger TRansferThir = Logger.getLogger("Transferencia a cuenta de terceros");
        TRansferThir.log(Level.WARNING, "TRANSFERENCIA A CUENTA DE TERCEROS wsTransferThirdReq");
         */
        // idelemento = usuario, IdTeller = identificador, IdAcct = opa, TxType = tipo de producto, IdOperation = id registrado, Date = fecha de operacion, Amount1 = monto a depositar, Amount2 = cambio, Reference = texto de referencia, Concept = concepto,  
        // SessionId = sesion del cajero, User = usaurio del cajero, Password = pass del cajero, tipoPolizaVentanilla = tipo de poliza, conceptoPoliza = concepto de la poliza, 1 = lo llamo banca movil, false = es condonacion, false = es poliza de resumen
        return siscoop.transferenciaACuentaDeTerceros(IdTeller, TxType, IdAcctO, IdAcctD, DateOper, IdOperation, Amount, Amount, Reference, Concept, SessionId, User, Password, tipoPolizaTraspaso, conceptoPolizaTercero, 1, false, false, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // LISTA CON LOS MOVIMIENTOS DEL FOLIO
    @Override
    public List<MovimientoCuentaDTO> wsGetAccountTxsReq(String IdAcct, String IdTeller, String SessionId, String User, String Password) {
  /*      
         Logger ListaMovFolios = Logger.getLogger("Entro al wsBancamovil lista con los moviminetos de folios ");
       ListaMovFolios.log(Level.WARNING, " wsGetAccountTxsReq(String IdAcct, String IdTeller, String SessionId, String User, String Password) ");
*/
        
        // idelemento = usuario, IdAcct = opa, IdTeller = identificador, SessionId = sesion, User = usuario, Password = pass del usuario, 1 = lo llamo banca movil
        return siscoop.buscaMovimientosCuenta(IdAcct, IdTeller, SessionId, User, Password, 1, idTabla); // 0 = Cajero, 1 = BancaMovil, 2 = SPEI
    }

    // -------------------------------------------------------------------------
    // -- TRAE UN ARREGLO CON LA LISTA DE LOS PRODUCTOS DE ORIGEN Y DESTINO PARA
    // -- LA TRANSFERENCIA ENTRE CUENTAS PROPIAS -------------------------------
    // -------------------------------------------------------------------------
    @Override
    public ProductosTrasODDTO wsGetAccountTrasReq(String IdClient, String IdTeller, String tipoTransferencia) {
        /*
        Logger ListaProductos = Logger.getLogger("Entro al wsBancamovil lista de productos para transferencia ");
       ListaProductos.log(Level.WARNING, " wsGetAccountTrasReq(String IdClient, String IdTeller, String tipoTransferencia) ");
        */
        // Lista de string con las cuentas de origen y destino
        return siscoop.productosOrigenDestino(aliasToOGS(IdClient, null), IdTeller, tipoTransferencia, idTabla);
    }

    private String tipoIdentificacion() {
        System.out.println("Entro a Tipo Identificacion");
        TablasPK tpk0 = new TablasPK(idTabla, "id_cliente_autorizado");
        TablasDTO tdto0 = tablasService.buscaTabla(tpk0);
        if (tdto0 != null) {
            if (tdto0.getDato1() != null) {
                if (!tdto0.getDato1().replace(" ", "").isEmpty()) {
                    return tdto0.getDato1().replace(" ", "");
                }
            }
        }
        return "";
    }

    // --- Obtenemos el numero de socio en base al alias (32 digitos)
    private String aliasToOGS(String IdClient, Integer tid) {
        try {
            // Mediante el identificador del alias se busca el ogs
            BancaMovilUsuariosDTO alias = bancaMovilUsuariosService.buscaAliasBancaMovil(IdClient.replaceFirst("^0*", "")); // Elimina los ceros del inicio
            if (alias.getIdsocio() > 0) {
                // Se geenra la cadena a 32 digitos
                return String.format("%024d", alias.getIdorigen()) + alias.getIdgrupo() + String.format("%06d", alias.getIdsocio());
            }
        } catch (Exception e) {
            System.out.println("El socio no tiene alias. " + e.getMessage());
        }
        if (tid == null) {
            //si la busqueda se puede realizar por alias y numero de socioo y no encontro el alias es que es numero de socio y lo retornamos
            return IdClient;
        } else {
            //si la autorizacion es solo por alias y no se encontro el alias en la tabla retorna null
            return null;
        }
    }

    // --- Obtenemos el numero de socio en base al alias (32 digitos)
    private String OGStoAlias(String IdClient) {
        try {
            int[] ogs = convertidor.getOGS(IdClient);
            // Mediante el identificador dela lias se busca el ogs
            BancaMovilUsuariosDTO alias = bancaMovilUsuariosService.buscaAliasBancaMovil(ogs[0], ogs[1], ogs[2]); // Elimina los ceros del inicio
            // Se geenra la cadena a 32 digitos
            return String.format("%032d", Integer.parseInt(alias.getAliasUsuario()));
        } catch (Exception e) {
        }
        // Si no tiene alias regresar el numero de socio a 32 digitos
        return IdClient;
    }
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // ------------------------- SAI_AUXILIAR ----------------------------------
    // -------------------------------------------------------------------------
    @Override
    public SaiAuxiliarPrestamoDTO wsGetSaiAuxiliarPresReq(int idorigenp, int idproducto, int idauxiliar) {
        return siscoop.ejecutaSaiAuxiliarPrestamo(idorigenp, idproducto, idauxiliar);
    }

    @Override
    public SaiAuxiliarAhorroDTO wsGetSaiAuxiliarAhoReq(int idorigenp, int idproducto, int idauxiliar) {
        return siscoop.ejecutaSaiAuxiliarAhorro(idorigenp, idproducto, idauxiliar);
    }

    @Override
    public SaldoAuxiliarDTO wsGetSaldoAuxiliarDTOReq(int idorigenp, int idproducto, int idauxiliar, String idTeller) {
        return siscoop.saldoAuxiliarDTO(idorigenp, idproducto, idauxiliar, idTeller, idTabla);
    }

    @Override
    public List<CuentasTercerosDTO> wsGetAccountThirdByOGSReq(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK) {
        return siscoop.buscaCuentasTerceros(catalogoCuentasTercerosPK, idTabla);
    }
    // -------------------------------------------------------------------------

}
