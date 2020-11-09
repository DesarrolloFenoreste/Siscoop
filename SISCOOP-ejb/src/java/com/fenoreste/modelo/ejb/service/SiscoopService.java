/*
 * CLASE: SiscoopService.java
 * Esta clase contiene todos los metodos que procesan los datos para aplicar
 * movimientos, consultar informacion, validar datos entre otros.
 */
package com.fenoreste.modelo.ejb.service;

// -----------------------------------------------------------------------------
// --- DTO ---------------------------------------------------------------------
// -----------------------------------------------------------------------------
// --- SAICOOP -----------------------------------------------------------------
import com.fenoreste.modelo.dto.AmortizacionesDTO;
import com.fenoreste.modelo.dto.AuxiliaresDTO;
import com.fenoreste.modelo.dto.siscoop.ProductoDTO;
import com.fenoreste.modelo.dto.AuxiliaresdDTO;
import com.fenoreste.modelo.dto.BancaMovilUsuariosDTO;
import com.fenoreste.modelo.dto.ColoniasDTO;
import com.fenoreste.modelo.dto.EstadosDTO;
import com.fenoreste.modelo.dto.MunicipiosDTO;
import com.fenoreste.modelo.dto.OrigenesDTO;
import com.fenoreste.modelo.dto.PersonasDTO;
import com.fenoreste.modelo.dto.ProductosDTO;
import com.fenoreste.modelo.dto.SaiComisionCobranzaDTO;
import com.fenoreste.modelo.dto.SaiSeguroHipotecarioDTO;
import com.fenoreste.modelo.dto.SegurosComisionesDTO;
import com.fenoreste.modelo.dto.SoparDTO;
import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.dto.TemporalDTO;
import com.fenoreste.modelo.dto.UsuariosDTO;
import com.fenoreste.modelo.dto.ValidaMovimientoCajeroReceptorDTO;
// --- SISCOOP -----------------------------------------------------------------
import com.fenoreste.modelo.dto.siscoop.CatAcctTypeDTO;
import com.fenoreste.modelo.dto.siscoop.CatTxTypeDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseTransferenciaACuentaDTO;
import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import com.fenoreste.modelo.dto.siscoop.CuentaUsuarioDTO;
import com.fenoreste.modelo.dto.siscoop.CuentasTercerosDTO;
import com.fenoreste.modelo.dto.siscoop.DistribucionPrestamoDTO;
import com.fenoreste.modelo.dto.siscoop.MovimientoCuentaDTO;
import com.fenoreste.modelo.dto.siscoop.ProductosTrasODDTO;
import com.fenoreste.modelo.dto.siscoop.ResponseDepositoACuentaDTO;
import com.fenoreste.modelo.dto.siscoop.SaiPromocionPuntosSaicoopDTO;
import com.fenoreste.modelo.dto.siscoop.SaldoAuxiliarDTO;
import com.fenoreste.modelo.dto.siscoop.saiAuxiliar.SaiAuxiliarAhorroDTO;
import com.fenoreste.modelo.dto.siscoop.saiAuxiliar.SaiAuxiliarPrestamoDTO;
import com.fenoreste.modelo.ejb.facade.TablasFacade;
import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.ejb.interfaceService.AbonoAdelantadoInteresServiceLocal;
// -----------------------------------------------------------------------------
// --- EJB ---------------------------------------------------------------------
// -----------------------------------------------------------------------------
// --- INTERFACE SERVICE - SAICOOP ---------------------------------------------
import com.fenoreste.modelo.ejb.interfaceService.AmortizacionesServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.AuxiliaresDServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.AuxiliaresServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.BancaMovilUsuariosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.CatAcctTypeServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.ColoniasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SiscoopServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.CuentasTercerosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.EstadosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.MunicipiosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.OrigenesServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.PersonasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.ProductosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SaiComisionCobranzaServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SaiSeguroHipotecarioServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SoparServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.TemporalServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.UsuariosServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.ValidaMovimientoCajeroReceptorServiceLocal;
// --- UTIL --------------------------------------------------------------------
import com.fenoreste.modelo.ejb.util.CodigoError;
import com.fenoreste.modelo.ejb.util.Convertidor;
import com.fenoreste.modelo.ejb.util.EnviarMail;
import com.fenoreste.modelo.ejb.util.EnviaSMS;
import com.fenoreste.modelo.ejb.util.SaiFunciones;
import com.fenoreste.modelo.ejb.util.Validaciones;
import com.fenoreste.modelo.ejb.util.ProsaCalixta;
import com.fenoreste.modelo.ejb.util.NumeroEnLetra;
import com.fenoreste.modelo.ejb.util.Seguridad;
import com.fenoreste.modelo.entidad.AbonoAdelantadoInteres;
import com.fenoreste.modelo.entidad.AbonoAdelantadoInteresPK;
// -----------------------------------------------------------------------------
// --- ENTIDAD -----------------------------------------------------------------
// -----------------------------------------------------------------------------
import com.fenoreste.modelo.entidad.AmortizacionesPK;
import com.fenoreste.modelo.entidad.AuxiliaresDPK;
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import com.fenoreste.modelo.entidad.PersonasPK;
import com.fenoreste.modelo.entidad.SaldoTddPK;
import com.fenoreste.modelo.entidad.Tablas;
import com.fenoreste.modelo.entidad.TablasPK;
// -----------------------------------------------------------------------------
// --- CLASES JAVA -------------------------------------------------------------
// -----------------------------------------------------------------------------
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
// -----------------------------------------------------------------------------
// --- SERVICIOS PARA SISCOOP --------------------------------------------------
// -----------------------------------------------------------------------------
import serviciosExternos.utilidades.TarjetaDeDebito;
import com.syc.ws.endpoint.siscoop.BalanceQueryResponseDto;
import java.lang.reflect.InvocationTargetException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import static javax.ws.rs.client.Entity.entity;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author gerardo
 */
@Stateless
public class SiscoopService implements SiscoopServiceLocal {

    @EJB
    private AuxiliaresServiceLocal auxiliaresService;
    @EJB
    private AbonoAdelantadoInteresServiceLocal abonoAdelantadoInteresService;
    @EJB
    private BancaMovilUsuariosServiceLocal bancaMovilUsuariosService;
    @EJB
    private Validaciones validaciones;
    @EJB
    private CuentasTercerosServiceLocal cuentasTercerosService;
    @EJB
    private AuxiliaresDServiceLocal auxiliaresDService;
    @EJB
    private TemporalServiceLocal temporalService;
    @EJB
    private SaiComisionCobranzaServiceLocal saiComisionCobranzaService;
    @EJB
    private SaiSeguroHipotecarioServiceLocal saiSeguroHipotecarioService;
    @EJB
    private ColoniasServiceLocal coloniasService;
    @EJB
    private MunicipiosServiceLocal municipiosService;
    @EJB
    private EstadosServiceLocal estadosService;
    @EJB
    private UsuariosServiceLocal usuariosService;
    @EJB
    private OrigenesServiceLocal origenesService;
    @EJB
    private SaiFunciones saiFunciones;
    @EJB
    private AmortizacionesServiceLocal amortizacionesService;
    @EJB
    private Convertidor convertidor;
    @EJB
    private PersonasServiceLocal personasService;
    @EJB
    private TablasServiceLocal tablasService;
    @EJB
    private ProductosServiceLocal productosService;
    @EJB
    private CatAcctTypeServiceLocal catAcctTypeServiceLocal;
    @EJB
    private ValidaMovimientoCajeroReceptorServiceLocal validaMovimientoCajeroReceptorService;
    @EJB
    private SoparServiceLocal soparService;
    @EJB
    private TarjetaDeDebito tarjetaDeDebito;

    private int idproductoOrigen;
    private String celOrigen = "";
    private int idproductoDestino;
    private String celDestino = "";
    private int tipoDestino = 0;
    private int movimiento = 0;
    private String emailOrigen = "";
    private String nomOrigen = "";
    private String nomDestino = "";
    private String nAo = "";
    private String nAd = "";
    // private final Seguridad seguridad;
    private final DecimalFormat formatea;
    private final BigDecimal bigd = new BigDecimal(0.00);

    public SiscoopService() {
        this.formatea = new DecimalFormat("###,###,##0.00");
        //this.seguridad = new Seguridad();
    }

    // BUSCA LOS DATOS DEL PRODUCTO
    @Override
    public ProductoDTO buscaProducto(String IdAcct, String AcctType, String AcctStatus, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
        productoDTO.setDescription(CodigoError.CE096.getMensaje());//Mensaje del Error Desconocido
        try {
            // Se Valida el usuario, comparando el usuario y el password que envia el cliente con el usuario y pass de la bd
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida la hora para no realizar movimientos fuera de horario
                if (ServicioActivo(idTabla)) {
                    // Se Valida que el IdAcct tenga la cantidad correcta de digitos y se valida que el cliente haya enviado el tipo de productos 
                    if (validaciones.validaOPA(IdAcct) == true && !AcctType.isEmpty()) {
                        //se obtiene el opa del idacct
                        int[] opa = convertidor.getOPA(IdAcct);
                        //se prepara el pk con el opa(las clases PK representan el primarykey de la tabla)
                        AuxiliaresPK auxiliaresPK = new AuxiliaresPK(opa[0], opa[1], opa[2]);
                        // Busca el auxiliar con el pk que tiene el opa
                        AuxiliaresDTO aux = auxiliaresService.buscarAuxiliar(auxiliaresPK);
                        // Valida que haya retornado los datos del Auxiliar
                        if (aux.getAuxiliaresPK() != null) {
                            for (String listaProducto : listaProductoValido(AcctType, idTabla)) {
                                // El producto tiene que estar en la lista y su estatus es 2 (activo)
                                //System.out.println(aux.getAuxiliaresPK().getIdorigenp() + " " + aux.getAuxiliaresPK().getIdproducto() + " " + aux.getAuxiliaresPK().getIdauxiliar() + " - " + aux.getEstatus());
                                //System.out.println(listaProducto + " - " + aux.getAuxiliaresPK().getIdproducto());
                                if (listaProducto.equals(Integer.toString(aux.getAuxiliaresPK().getIdproducto())) && aux.getEstatus() == 2) {
                                    String description = null;
                                    String maxAmountDeposit = "0.00";
                                    // Busca el producto
                                    ProductosDTO pro = productosService.buscarProducto(aux.getAuxiliaresPK().getIdproducto());
                                    // Nombre del producto - Dato dependiendo si fue por cajero o bancaMovil
                                    if (tipoEntrada == 0) { // Cajero receptor - 0
                                        PersonasPK apk = new PersonasPK(aux.getIdorigen(), aux.getIdgrupo(), aux.getIdsocio());
                                        PersonasDTO per = personasService.buscarPersona(apk);
                                        String nombre_socio = per.getNombre() + " " + per.getAppaterno() + " " + per.getApmaterno();
                                        description = pro.getNombre() + "\n" + nombre_socio;
                                    } else if (tipoEntrada == 1) { // BancaMOvil - 1
                                        description = pro.getNombre();
                                        productoDTO.setAvailBalance(String.valueOf(aux.getSaldo().subtract(aux.getGarantia())));
                                    }
                                    TablasDTO tablasDTO = valorTablas(idTabla, "ws_get_account_req");
                                    // Identifica si es ahorro o prestamo
                                    if (!pro.getTipoproducto().equals(2)) {
                                        // Configuracion de los dato que se van amostrar (ahorro)
                                        productoDTO.setShowConf(tablasDTO.getDato1());
                                    } else {
                                        ArrayList<String> datoPrestamo = datosPrestamo(aux);
                                        // Si es hipotecario su monto tiene que ser igual o menor al pago siguiente
                                        if (aux.getTipoamortizacion() == 5) {
                                            // Total a pagar hipotecario
                                            maxAmountDeposit = datoPrestamo.get(2);
                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // fue la unica configuracion que me acepto
                                        productoDTO.setCourtDate(formatter.parse(datoPrestamo.get(0))); // Fecha de corte
                                        productoDTO.setNextPayAmount(formatea.format(Double.parseDouble(datoPrestamo.get(2)))); // Monto siguiente pago
                    // Modificado por fredy 10/03/2020
           //////////////////////////////////////////////////////////////////////////////////////////////
  /*         
                                        Logger montoInteresLog = Logger.getLogger("montoInteres");
                                        montoInteresLog.log(Level.SEVERE, "montoInteres: " + formatea.format(Double.parseDouble(datoPrestamo.get(3))));
*/
                                        BigDecimal total_a_pagar = BigDecimal.ZERO;
                                        OrigenesDTO origenesdto = origenesService.buscarOrigen(aux.getIdorigen());
                                        SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar(), origenesdto.getFechatrabajo());
                                        total_a_pagar = aux.getSaldo().add(saiAuxiliarPrestamoDTO.getMontoIoTotal());
                                        total_a_pagar = total_a_pagar.add(saiAuxiliarPrestamoDTO.getIvaIoTotal());
                                        total_a_pagar = total_a_pagar.add(saiAuxiliarPrestamoDTO.getMontoImTotal());
                                        total_a_pagar = total_a_pagar.add(saiAuxiliarPrestamoDTO.getIvaImTotal());
                                        total_a_pagar = total_a_pagar.add(saiAuxiliarPrestamoDTO.getComisionNpTotal());                          
                                        productoDTO.setAvailBalance(String.valueOf(total_a_pagar));
           /////////////////////////////////////////////////////////////////////////////////////////////////
                                        
                                        productoDTO.setShowConf(tablasDTO.getDato2()); // Configuracion de los dato que se van amostrar (prestamo)
                                    }
                                    // RESULTADO FINAL
                                    productoDTO.setResponseCode(CodigoError.CE000.getIdError());
                                    productoDTO.setMaxAmountDeposit(maxAmountDeposit);
                                    productoDTO.setDescription(description);
                                    //productoDTO.setIdClient(bancaMovilUsuariosService.buscaAliasBancaMovil(aux.getIdorigen(), aux.getIdgrupo(), aux.getIdsocio()).getAliasUsuario()); // idcliente en base al alias
                                    productoDTO.setIdClient(convertidor.getOGS32(aux.getIdorigen(), aux.getIdgrupo(), aux.getIdsocio()));  // idcliente en base al ogs
                                    productoDTO.setAcctType(getAcctTypeProducto(aux.getAuxiliaresPK().getIdproducto(), idTabla)); //
                                    productoDTO.setIdAcct(convertidor.getOPA32(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar()));
                                    productoDTO.setAcctStatus("1");
                                    break;
                                } else {
                                    productoDTO.setResponseCode(CodigoError.CE084.getIdError()); // No essta configurada la lista de productos validos
                                    productoDTO.setDescription(CodigoError.CE084.getMensaje());
                                }
                            }
                        } else {
                            productoDTO.setResponseCode(CodigoError.CE089.getIdError()); // No existe el opa
                            productoDTO.setDescription(CodigoError.CE089.getMensaje());
                        }
                    } else {
                        productoDTO.setResponseCode(CodigoError.CE092.getIdError()); // Folio incorrecto
                        productoDTO.setDescription(CodigoError.CE092.getMensaje());
                    }
                } else {
                    productoDTO.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada
                    productoDTO.setDescription(CodigoError.CE095.getMensaje());
                }
            } else {
                productoDTO.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado
                productoDTO.setDescription(CodigoError.CE050.getMensaje());
            }
        } catch (NumberFormatException | NullPointerException | ParseException e) {
            System.out.println("Error en buscaProducto de SiscoopService: " + e.getMessage());
        }
        // Retorna el DTO con la lista de productos
        return productoDTO;
    }

    // BUSCA LOS DATOS DEL CLIENTE EN BASE A UN OGS. (METODO REDUCIDO).
    @Override
    public DatoClienteDTO buscaCliente(String IdClient, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla) {
        DatoClienteDTO datoClienteDTO = new DatoClienteDTO();
        datoClienteDTO.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
        try {
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida la hora para no realizar movimientos fuera de horario
                if (ServicioActivo(idTabla)) {
                    //valida que el id del cliente tenga la cantidad correcta de digitos
                    if (validaciones.validaOGS(IdClient) == true) {
                        //se extraen el ogs  de la cadena idclient
                        int[] ogs = convertidor.getOGS(IdClient);
                        //se prepara personaspk con el ogs para consultar al socio
                        PersonasPK personasPK = new PersonasPK(ogs[0], ogs[1], ogs[2]);
                        //se consultan los datos del socio
                        PersonasDTO per = personasService.buscarPersona(personasPK);
                        // valido si me trajo los datos del usuario 
                        if (per.getPersonasPK() != null) {
                            // Tabla que contiene el valor de ShowConf
                            TablasDTO tablasDTO = valorTablas(idTabla, "ws_get_client_data_req");
                            // Resultado final.
                            datoClienteDTO.setResponseCode(CodigoError.CE000.getIdError());
                            datoClienteDTO.setIdClient(convertidor.getOGS32(per.getPersonasPK().getIdorigen(), per.getPersonasPK().getIdgrupo(), per.getPersonasPK().getIdsocio()));
                            datoClienteDTO.setClientType("00");
                            datoClienteDTO.setName(per.getNombre());           // datoCli.setName(seguridad.enmascararCadenaIni(per.getNombre()));
                            datoClienteDTO.setLastName(per.getAppaterno());    // datoCli.setLastName(seguridad.enmascararCadenaFin(per.getAppaterno()));
                            datoClienteDTO.setMaidenName(per.getApmaterno());  // datoCli.setMaidenName(seguridad.enmascararCadenaFin(per.getApmaterno()));
                            datoClienteDTO.setCountry("484");
                            datoClienteDTO.setShowConf(tablasDTO.getDato2());
                        } else {
                            datoClienteDTO.setResponseCode(CodigoError.CE090.getIdError()); // No existe el socio
                        }
                    } else {
                        datoClienteDTO.setResponseCode(CodigoError.CE091.getIdError()); // Numero de socio incorrecto
                    }
                } else {
                    datoClienteDTO.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada
                }
            } else {
                datoClienteDTO.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado
            }
        } catch (Exception e) {
            System.out.println("Error en buscaCliente de SiscoopService: " + e.getMessage());
        }
        return datoClienteDTO;
    }

    // BUSCA LOS DATOS DEL CLIENTE EN BASE A UN OGS. (METODO COMPLETO).
    @Override
    public DatoClienteDTO buscaClienteBancaMovil(String IdClient, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla) {
        DatoClienteDTO datoClienteDTO = new DatoClienteDTO();
        datoClienteDTO.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
        try {
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida la hora para no realizar movimientos fuera de horario
                if (ServicioActivo(idTabla)) {
                    if (validaciones.validaOGS(IdClient) == true) {
                        int[] ogs = convertidor.getOGS(IdClient);
                        BancaMovilUsuariosDTO bmudto = bancaMovilUsuariosService.buscaAliasBancaMovil(ogs[0], ogs[1], ogs[2]); // Elimina los ceros del inicio
                        if (bmudto != null || TxType.equals("09")) {
                            if ((bmudto.getIdsocio() > 0 && bmudto.isEstatus() == true) || TxType.equals("09")) {
                                PersonasPK personasPK = new PersonasPK(ogs[0], ogs[1], ogs[2]);
                                // El socio debe contar con el producto de banca movil (producto para validar quien esta registrado)
                                if (existeSocioProducto(ogs[0], ogs[1], ogs[2], idTabla) || "09".equals(TxType)) { // Se envia un 09 en el TxType es para activar cuenta.
                                    // Datos persona.
                                    PersonasDTO per = personasService.buscarPersona(personasPK);
                                    if (per.getPersonasPK() != null) {
                                        // Datos persona - appaterno, apmaterno y nombre en UTF8.
                                        PersonasDTO perUTF8 = personasService.caracteresUTF8(personasPK);
                                        // Datos de la colonia.
                                        ColoniasDTO col = coloniasService.buscarColonia(per.getIdcolonia());
                                        // Datos del municipio.
                                        MunicipiosDTO mun = municipiosService.buscarMunicipio(col.getIdmunicipio());
                                        // Datos del estado.
                                        EstadosDTO est = estadosService.buscarEstado(mun.getIdestado());
                                        // Tabla que contiene el valor de ShowConf.
                                        TablasDTO tablasDTO = valorTablas(idTabla, "ws_get_client_data_req");
                                        // Resultado final.
                                        datoClienteDTO.setResponseCode(CodigoError.CE000.getIdError());
                                        datoClienteDTO.setIdClient(convertidor.getOGS32(per.getPersonasPK().getIdorigen(), per.getPersonasPK().getIdgrupo(), per.getPersonasPK().getIdsocio()));
                                        datoClienteDTO.setClientType(TxType);
                                        datoClienteDTO.setName(perUTF8.getNombre());
                                        datoClienteDTO.setLastName(perUTF8.getAppaterno());
                                        datoClienteDTO.setMaidenName(perUTF8.getApmaterno());
                                        datoClienteDTO.setAdress1(per.getCalle() + " " + per.getNumeroext());
                                        datoClienteDTO.setAdress2(col.getNombre());
                                        datoClienteDTO.setCity(mun.getNombre());
                                        datoClienteDTO.setState(est.getNombre());
                                        datoClienteDTO.setZIP(col.getCodigopostal());
                                        datoClienteDTO.setCountry("484");
                                        datoClienteDTO.setPhone1(per.getTelefono());
                                        datoClienteDTO.setPhone2(per.getTelefonorecados());
                                        datoClienteDTO.setMobile(per.getCelular());
                                        datoClienteDTO.setEmail(per.getEmail());
                                        datoClienteDTO.setGerender(getSexoPersona(per.getSexo()));
                                        datoClienteDTO.setDate1(per.getFechaingreso());
                                        datoClienteDTO.setDate2(per.getFechanacimiento());
                                        datoClienteDTO.setSSN1(per.getRfc());
                                        datoClienteDTO.setSSN2(per.getCurp());
                                        datoClienteDTO.setStatus(getStatus(per.getEstatus()));
                                        datoClienteDTO.setShowConf(tablasDTO.getDato2());
                                    } else {
                                        datoClienteDTO.setResponseCode(CodigoError.CE090.getIdError()); // No existe el socio
                                    }
                                } else {
                                    datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
                                }
                            } else {
                                datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
                            }
                        } else {
                            datoClienteDTO.setResponseCode(CodigoError.CE082.getIdError()); // Socio no registrado a CSN movil
                        }
                    } else {
                        datoClienteDTO.setResponseCode(CodigoError.CE091.getIdError()); // Numero de socio incorrecto.
                    }
                } else {
                    datoClienteDTO.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada
                }
            } else {
                datoClienteDTO.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado
            }
        } catch (Exception e) {
            System.out.println("Error en buscaClienteTodo de SiscoopService: " + e.getMessage());
        }
        // Retorna los datos del cliente
        return datoClienteDTO;
    }

    // BUSCA LAS CUENTAS DEL SOCIO Y LAS AGREGA A UNA LISTA.
    @Override
    public List<CuentaUsuarioDTO> buscarCuentasUsuario(String IdClient, String AcctType, String IdChannel, String IdTeller, String SessionId, String User, String Password, int tipoEntrada, String idTabla) {
        List<CuentaUsuarioDTO> ListCuentasUsuario = new ArrayList<>(0);
        CuentaUsuarioDTO cudto = new CuentaUsuarioDTO();
        try {
            // Valida el usuario y contraseña del metodo
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida la hora para no realizar movimientos fuera de horario
                if (ServicioActivo(idTabla)) {
                    // Valida el estatus del origen
                    if (estatusOrigen(idusuario(IdTeller, idTabla))) {
                        UsuariosDTO usuario = datoUsuario(IdTeller, idTabla);
                        // Valida que el usuario este activo
                        if (usuario.isActivo()) {
                            // Esto es para bloquear socios en una lista negra
                            if (!socioBloqueado(IdClient, usuario.getIdusuario(), idTabla)) {
                                // Valida el ogs
                                int[] ogs = convertidor.getOGS(IdClient);
                                if (validaciones.validaOGS(IdClient) == true && !AcctType.isEmpty()) {
                                    List<AuxiliaresDTO> auxiliares = auxiliaresService.buscaAuxiliarPorOGS(ogs[0], ogs[1], ogs[2]);
                                    if (!auxiliares.isEmpty()) {
                                        List<String> listaProductoValidos = listaProductoValido(AcctType, idTabla);
                                        for (String productoVailido : listaProductoValidos) {
                                            for (AuxiliaresDTO auxiliar : auxiliares) {
                                                if (productoVailido.equals(Integer.toString(auxiliar.getAuxiliaresPK().getIdproducto())) && auxiliar.getEstatus() == 2) {
                                                    ProductosDTO producto = productosService.buscarProducto(auxiliar.getAuxiliaresPK().getIdproducto());
                                                    CuentaUsuarioDTO cuantaUsuario = new CuentaUsuarioDTO();
                                                    cuantaUsuario.setIdClient(IdClient);
                                                    cuantaUsuario.setIdAcct(convertidor.getOPA32(auxiliar.getAuxiliaresPK().getIdorigenp(), auxiliar.getAuxiliaresPK().getIdproducto(), auxiliar.getAuxiliaresPK().getIdauxiliar()));
                                                    // Valida que no exista el registro en la lista, si no existe se agrega
                                                    if (!ListCuentasUsuario.contains(cuantaUsuario)) {
                                                        cuantaUsuario.setAcctType(getAcctTypeProducto(producto.getIdproducto(), idTabla));
                                                        cuantaUsuario.setAvailBalance(auxiliar.getSaldo().toString());
                                                        // Dato dependiendo si fue por cajero o bancaMovil
                                                        if (tipoEntrada == 0) { // Cajero receptor - 0
                                                            PersonasPK apk = new PersonasPK(ogs[0], ogs[1], ogs[2]);
                                                            PersonasDTO per = personasService.buscarPersona(apk);
                                                            String nombre_socio = per.getNombre() + " " + per.getAppaterno() + " " + per.getApmaterno();
                                                            cuantaUsuario.setDescription(producto.getNombre() + "\n" + nombre_socio);
                                                        } else if (tipoEntrada == 1) { // BancaMOvil - 1
                                                            cuantaUsuario.setDescription(producto.getNombre());
                                                            // SALDO TDD -- excluir si no consulta
                                                            BalanceQueryResponseDto saldoTDD = consultarTDD(auxiliar, IdTeller, idTabla);
                                                            if (saldoTDD.getCode() == 1) {
                                                                cuantaUsuario.setAvailBalance(String.valueOf(saldoTDD.getAvailableAmount()));
                                                            } else if (saldoTDD.getCode() == 0) {
                                                                // Si retorna error no se agrega a la lista
                                                                break;
                                                            }
                                                        }
                                                        TablasDTO tablasDTO = valorTablas(idTabla, "ws_get_accounts_list_req");
                                                        // Tipo producto 2 son prestamos
                                                        if (!producto.getTipoproducto().equals(2)) {
                                                            // Captacion
                                                            cuantaUsuario.setConfig(tablasDTO.getDato1());
                                                            cuantaUsuario.setCourtDate(new Date("01/01/1900"));
                                                        } else {
                                                            // Prestamo
                                                            ArrayList<String> datoPrestamo = datosPrestamo(auxiliar);
                                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // fue la unica configuracion que me acepto
                                                            cuantaUsuario.setCourtDate(formatter.parse(datoPrestamo.get(0)));
                                                            cuantaUsuario.setNextPayAmount(formatea.format(Double.parseDouble(datoPrestamo.get(2))));
                                                            cuantaUsuario.setConfig(tablasDTO.getDato2());
                                                            
                                                            // Modificado por fredy 11/03/2020
                                                            ////////////////////////////////////
                                                            
                                                            BigDecimal total_a_pagar_saldo_prestamo = BigDecimal.ZERO;
                                        OrigenesDTO origenesdto = origenesService.buscarOrigen(auxiliar.getIdorigen());
                                        SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliar.getAuxiliaresPK().getIdorigenp(), auxiliar.getAuxiliaresPK().getIdproducto(), auxiliar.getAuxiliaresPK().getIdauxiliar(), origenesdto.getFechatrabajo());
                                        total_a_pagar_saldo_prestamo = auxiliar.getSaldo().add(saiAuxiliarPrestamoDTO.getMontoIoTotal());
                                        total_a_pagar_saldo_prestamo = total_a_pagar_saldo_prestamo.add(saiAuxiliarPrestamoDTO.getIvaIoTotal());
                                        total_a_pagar_saldo_prestamo = total_a_pagar_saldo_prestamo.add(saiAuxiliarPrestamoDTO.getMontoImTotal());
                                        total_a_pagar_saldo_prestamo = total_a_pagar_saldo_prestamo.add(saiAuxiliarPrestamoDTO.getIvaImTotal());
                                        total_a_pagar_saldo_prestamo = total_a_pagar_saldo_prestamo.add(saiAuxiliarPrestamoDTO.getComisionNpTotal());
                                        cuantaUsuario.setAvailBalance(String.valueOf(total_a_pagar_saldo_prestamo));
                                                            
                                                            ////////////////////////////////////
                                                        }
                                                        cuantaUsuario.setResponseCode(CodigoError.CE000.getIdError()); // Codigo 000 todo bien
                                                        
                                                   /*     Logger DAtosCuentaUsuario = Logger.getLogger("DAtos de cuentaUsuario:");
                                                               DAtosCuentaUsuario.log(Level.WARNING, "DAtos: ", cuantaUsuario);
        */
                                                        ListCuentasUsuario.add(cuantaUsuario);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        cudto.setResponseCode(CodigoError.CE090.getIdError()); // El socio no tiene folios
                                        //cudto.setDescription(CodigoError.CE090.getMensaje());
                                        ListCuentasUsuario.add(cudto);
                                    }
                                } else {
                                    cudto.setResponseCode(CodigoError.CE091.getIdError()); // Error en el ogs
                                    //cudto.setDescription(CodigoError.CE091.getMensaje());
                                    ListCuentasUsuario.add(cudto);
                                }
                            } else {
                                cudto.setResponseCode(CodigoError.CE083.getIdError()); // Socio bloqueado
                                //cudto.setDescription(CodigoError.CE083.getMensaje());
                                ListCuentasUsuario.add(cudto);
                            }
                        } else {
                            cudto.setResponseCode(CodigoError.CE097.getIdError()); // Usuario no activo
                            //cudto.setDescription(CodigoError.CE097.getMensaje());
                            ListCuentasUsuario.add(cudto);
                        }
                    } else {
                        cudto.setResponseCode(CodigoError.CE081.getIdError()); // Origen cerrado del usuario
                        //cudto.setDescription(CodigoError.CE081.getMensaje());
                        ListCuentasUsuario.add(cudto);
                    }
                } else {
                    cudto.setResponseCode(CodigoError.CE095.getIdError()); // Transacción fuera de horario
                    //cudto.setDescription(CodigoError.CE095.getMensaje());
                    ListCuentasUsuario.add(cudto);
                }
            } else {
                cudto.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado
                //cudto.setDescription(CodigoError.CE050.getMensaje());
                ListCuentasUsuario.add(cudto);
            }
        } catch (ParseException | NumberFormatException e) {
            cudto.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
            //cudto.setDescription(CodigoError.CE096.getMensaje());
            ListCuentasUsuario.add(cudto);
            System.out.println("Error en buscarCuentasUsuario de SiscoopService: " + e.getMessage());
        }
        // Retorna la lista de cuentas
        return ListCuentasUsuario;
    }

    
    
    
    // REALIZA EL DEPOSITO A UNA CUENTA EN ESPECIFICO.
    @Override
    public ResponseDepositoACuentaDTO depositoACuenta(String IdTeller, String IdAcct, String TxType, Integer IdOperation, String Date, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla) {
        movimiento = 0;
        ResponseDepositoACuentaDTO rdepo = new ResponseDepositoACuentaDTO();
        rdepo.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
        try {
            // Valida el usuario y contraseña del metodo
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida el estatus del origen
                if (estatusOrigen(idusuario(IdTeller, idTabla))) {
                    // Valida que el usuario este activo
                    UsuariosDTO usuario = datoUsuario(IdTeller, idTabla);
                    if (usuario.isActivo()) {
                        // Que el folio y el monto no esten vacios
                        if (!IdTeller.isEmpty() && !IdAcct.isEmpty() && !Amount1.isEmpty() && validaciones.validaOPA(IdAcct.trim()) == true) {
                            // Valida que el monto sea mayor a cero
                            if (new BigDecimal(Amount1).compareTo(BigDecimal.ZERO) == 1) {
                                // Valida si el movimiento ya fue aplicado
                                if (!validaMovimientoCajeroReceptor(IdTeller, IdAcct, TxType, IdOperation, Date, Amount1, Amount2)) {
                                    // Consultamos tablas para obtener el usuario que corresponde a esa caja
                                    TablasDTO tablasDTOusuario = valorTablas(idTabla, IdTeller);
                                    UsuariosDTO usuariosDTO = usuariosService.buscaUsuario(Integer.parseInt(tablasDTOusuario.getDato1().trim())); // consultamos usuarios
                                    // Si no existe el usuario no se hace nada (error 096).
                                    if (usuariosDTO != null) {
                                        int[] opa = convertidor.getOPA(IdAcct); // convierto el opa de 32 digitos a opa separado dentro de un arreglo
                                        AuxiliaresPK auxiliaresPK = new AuxiliaresPK(opa[0], opa[1], opa[2]);
                                        AuxiliaresDTO auxiliaresDTO = auxiliaresService.buscarAuxiliar(auxiliaresPK); // consulto auxiliares con el opa
                                        if (auxiliaresDTO != null) {
                                            // Esto es para bloquear socios en una lista negra
                                            String IdClient = convertidor.getOGS32(auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdsocio());
                                            if (!socioBloqueado(IdClient, usuario.getIdusuario(), idTabla)) {
                                                // Crea la sesion del usuario
                                                String miSesion = "W" + usuariosDTO.getIdusuario() + "-" + convertidor.getOGSsesion(auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdgrupo(), auxiliaresDTO.getIdsocio());
                                                // Elimino todos los temporales que sean de esta sesion
                                                System.out.println("Llego a eliminar temporal");
                                                eliminaTemporal(miSesion);
                                                System.out.println("Paso de eliminar temporal");
                                                Integer insertaAhorro = 0;
                                                Integer insertaCuentaEje = 0;
                                                Integer insertaPrestamo = 0;
                                                Integer segurosComisionesProcesados = 0;
                                                String xtra3 = "";
                                                String xtra4 = "";
                                                String xtra5 = "\n\n";
                                                Date fechaTrabajo = saiFunciones.saiFechaTrabajo(auxiliaresDTO.getIdorigen()); // consulto origenes para sacar la fecha de trabajo
                                                ProductosDTO productosDTO = productosService.buscarProducto(auxiliaresDTO.getAuxiliaresPK().getIdproducto()); // consulto productos, necesitare el nombre y el tipo del producto
                                                // Esto se hace si el amoun1 es mayor al total de lo que puede liquidar de su prestamo
                                                System.out.println("paso1");
                                                if (productosDTO.getTipoproducto() == 2) {
                                                    System.out.println("si es prestamo");
                                                    Double montoMaximo;
                                                    if (auxiliaresDTO.getTipoamortizacion() == 5) {
                                                        System.out.println("no se que sea");
                                                        montoMaximo = Double.valueOf(datosPrestamo(auxiliaresDTO).get(2));
                                                    } else {
                                                        System.out.println("normal");
                                                        montoMaximo = maximoCubrir(auxiliaresPK, fechaTrabajo, auxiliaresDTO.getSaldo());
                                                    }
                                                    // Si amount1 es mayor al monto maximo obtenemos la diferencia y moontomaximo sera amoun1 y el resto amoun2
                                                    Double monto1 = Double.valueOf(Amount1);
                                                    if (monto1 > montoMaximo) {
                                                        System.out.println("en montos");
                                                        double diferencia = monto1 - montoMaximo;
                                                        Amount1 = String.valueOf(montoMaximo);//deposito a cuenta
                                                        Amount2 = String.valueOf(Double.parseDouble(Amount2) + diferencia);
                                                    }
                                                }
                                                // Si el monto 2 no viene vacio, se deposita en una cuenta eje.
                                                if (!Amount2.isEmpty()) {
                                                    System.out.println("entro porque monto 2 si tiene algo");
                                                    if (Double.parseDouble(Amount2) > 0) {
                                                        // INSERT INTO tablas (idtabla, idelemento, dato2) VALUES ('siscoop', 'cuenta_eje', '131, 120, 125');
                                                        // Obtengo de tablas en dato1 el idproducto de mi cuenta eje
                                                        TablasDTO tablasDTOcuentaEje = valorTablas(idTabla, "cuenta_eje");
                                                        // Arreglo con los porductos para el deposito de la cuenta eje
                                                        String[] cuentasEje = tablasDTOcuentaEje.getDato2().split(",");
                                                        for (String cuenta : cuentasEje) {
                                                            int productoEje = Integer.parseInt(cuenta.trim());
                                                            if (opa[1] != productoEje) {
                                                                List<AuxiliaresDTO> ListAuxiliaresDTO = auxiliaresService.buscaAuxiliarPorOGS(auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdgrupo(), auxiliaresDTO.getIdsocio());
                                                                // Auxiliar para la cuenta eje
                                                                AuxiliaresDTO auxiliaresDTOcuentaEje = listaAuxiliaresDTO(String.valueOf(productoEje), ListAuxiliaresDTO);
                                                                // Si enceuntra el folio en auxiliares lo inserta
                                                                if (auxiliaresDTOcuentaEje != null) {
                                                                    // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                                                                    SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTOCuentaEje = ejecutaSaiAuxiliarAhorro(auxiliaresDTOcuentaEje.getAuxiliaresPK().getIdorigenp(), auxiliaresDTOcuentaEje.getAuxiliaresPK().getIdproducto(), auxiliaresDTOcuentaEje.getAuxiliaresPK().getIdauxiliar());
                                                                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                                    insertaCuentaEje = insertaTemporalCaptacion(auxiliaresDTOcuentaEje, miSesion, usuariosDTO.getIdusuario(), Amount2, "0", Amount2, " ", true, bigd, saiAuxiliarAhorroDTOCuentaEje.getMontoIo(), fechaTrabajo);
                                                                    if (insertaCuentaEje != 0) {
                                                                        ProductosDTO pdsr = productosService.buscarProducto(auxiliaresDTOcuentaEje.getAuxiliaresPK().getIdproducto());
                                                                        xtra5 = xtra5 + "Se realizó un deposito a su producto " + pdsr.getNombre() + " por la cantidad de $" + formatea.format((new BigDecimal(Amount2)).setScale(2, BigDecimal.ROUND_HALF_EVEN)) + " MN. \n\n";
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        }//deposito a cuenta
                                                        if (insertaCuentaEje == 0 && Double.parseDouble(Amount2) > 0) {
                                                            Amount1 = String.valueOf(Double.parseDouble(Amount1) + Double.parseDouble(Amount2));
                                                            xtra5 = xtra5 + "Se realizó un deposito a su producto " + productosDTO.getNombre() + " por la cantidad de $" + formatea.format((new BigDecimal(Amount2)).setScale(2, BigDecimal.ROUND_HALF_EVEN)) + " MN. \n\n";
                                                        }
                                                    }
                                                }
                                                xtra5 = xtra5 + "Lo atendió: " + usuariosDTO.getNombre() + "\n\n";
                                                // --- SI EL DESTINO ES UN AHORRO ENTRA -----------------------------------------
                                                if (productosDTO.getTipoproducto() == 0) {
                                                    System.out.println("el tipo producto es igual a 0");
                                                    // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                                                    SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTO = ejecutaSaiAuxiliarAhorro(auxiliaresDTO.getAuxiliaresPK().getIdorigenp(), auxiliaresDTO.getAuxiliaresPK().getIdproducto(), auxiliaresDTO.getAuxiliaresPK().getIdauxiliar());
                                                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                    insertaAhorro = insertaTemporalCaptacion(auxiliaresDTO, miSesion, usuariosDTO.getIdusuario(), Amount1, "0", Amount1, " ", true, bigd, saiAuxiliarAhorroDTO.getMontoIo(), fechaTrabajo);
                                                }
                                                // --- TERMINA AHORRO -----------------------------------------------------------
                                                // --- SI EL DESTINO ES UN PRESTAMO ENTRA ---------------------------------------
                                                if (productosDTO.getTipoproducto() == 2) {
                                                    System.out.println(" el tipo producto es igual a 2");
                                                    // Distribucion del Amount1
                                                    DistribucionPrestamoDTO distribucionPrestamoDTO = saiFunciones.distribucionMonto(auxiliaresDTO.getAuxiliaresPK().getIdorigenp(), auxiliaresDTO.getAuxiliaresPK().getIdproducto(), auxiliaresDTO.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo, new BigDecimal(Amount1));
                                                    // obtenemos informacion acerca del prestamo con la funcion saiAuxiliarPrestamo
                                                    SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTO.getAuxiliaresPK().getIdorigenp(), auxiliaresDTO.getAuxiliaresPK().getIdproducto(), auxiliaresDTO.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo);
                                                    // ------------------------------------------------------------------------------------
                                                    // --- ESTA PARTE OBTIENE LOS FOLIOS PARA LOS SEGUROS HIPOTECARIOS Y LAS COMISIONES ---
                                                    // ------------------------------------------------------------------------------------
                                                    int seguroInsertado;
                                                    // Inserta los seguros y las comisiones
                                                    List<SegurosComisionesDTO> segurosComisionesDTO = saiFunciones.distribucionSegurosComisionCobranza(auxiliaresDTO, fechaTrabajo, distribucionPrestamoDTO, saiAuxiliarPrestamoDTO.getDiasVencidos());
                                                    for (SegurosComisionesDTO seguroComisionDTO : segurosComisionesDTO) {
                                                        AuxiliaresDTO auxiliarSegHipDTO = new AuxiliaresDTO();
                                                        auxiliarSegHipDTO.setIdorigen(auxiliaresDTO.getIdorigen());
                                                        auxiliarSegHipDTO.setIdgrupo(auxiliaresDTO.getIdgrupo());
                                                        auxiliarSegHipDTO.setIdsocio(auxiliaresDTO.getIdsocio());
                                                        AuxiliaresPK auxiliarPk = new AuxiliaresPK();
                                                        auxiliarPk.setIdorigenp(seguroComisionDTO.getIdorigenp());
                                                        auxiliarPk.setIdproducto(seguroComisionDTO.getIdproducto());
                                                        auxiliarPk.setIdauxiliar(seguroComisionDTO.getIdauxiliar());
                                                        auxiliarSegHipDTO.setAuxiliaresPK(auxiliarPk);
                                                        // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                        seguroInsertado = insertaTemporalCaptacion(auxiliarSegHipDTO, miSesion, usuariosDTO.getIdusuario(), seguroComisionDTO.getApagar().toString(), "0", "0.00", " ", true, seguroComisionDTO.getIvaapagar(), bigd, fechaTrabajo);
                                                        segurosComisionesProcesados = segurosComisionesProcesados + seguroInsertado;
                                                    }
                                                    if (segurosComisionesProcesados != segurosComisionesDTO.size()) {
                                                        rdepo.setResponseCode(CodigoError.CE074.getIdError()); // Error no se inserto el total de seguros y comisiones
                                                        return rdepo;
                                                    }
                                                    // --- TERMINA LOS SEGUROS HIPOTECARIOS Y LAS COMISIONES -----------------------------
                                                    Double[] distribucionMonto = {distribucionPrestamoDTO.getIo().doubleValue(), distribucionPrestamoDTO.getIm().doubleValue(), distribucionPrestamoDTO.getIvaIo().doubleValue(), distribucionPrestamoDTO.getIvaIm().doubleValue(), distribucionPrestamoDTO.getaCapital().doubleValue()};
                                                    // Valida los montos antes de aplicarlos
                                                    if (validaciones.exiteMontoMayorACero(distribucionMonto)) {
                                                        // Inserta el temporal AuxiliaresDTO, sesion, idusuario, DistribucionPrestamoDTO, idcuenta, amount1, cargoabono
                                                        
                                                        System.out.println("insertando temporal");
                                                        insertaPrestamo = insertaTemporalPrestamo(auxiliaresDTO, miSesion, usuariosDTO.getIdusuario(), distribucionPrestamoDTO, saiAuxiliarPrestamoDTO, "0", Amount1, "", true, bigd, fechaTrabajo);
                                                        System.out.println("paso de insertar temporal");
                                                        // Cuenta las amortizaciones adelantadas para el tema de los boletos de buenos aires
                                                        totalAmortizacionesCubiertas(fechaTrabajo, miSesion);
                                                        
                                                        // Pre-carga los datos que se van a mostrar
                                                        xtra3 = String.format("%1$-15s", "Abono: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getaCapital().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                + String.format("%1$-12s", "Int ordinario: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                + String.format("%1$-12s", "Iva int. ord.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIvaIo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                + String.format("%1$-12s", "Int moratorio: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIm().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                + String.format("%1$-12s", "Iva int. mor.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIvaIm().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                        if (distribucionPrestamoDTO.getSegHip().doubleValue() > 0.0) {
                                                         
                                                            xtra3 = xtra3 + String.format("%1$-14s", "Seguro hip.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getSegHip().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n");
                                                        }
                                                        if (distribucionPrestamoDTO.getComCob().doubleValue() > 0.0) {
                                                            
                                                            xtra3 = xtra3 + String.format("%1$-17s", "Comision: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getComCob().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n");
                                                        }
                                                        
                                                        xtra4 = String.format("%1$-12s", "Dias vencidos: ") + " " + String.format("%11s", saiAuxiliarPrestamoDTO.getDiasVencidos()) + "\n"
                                                                + String.format("%1$-12s", "Monto vencido: ") + "$" + String.format("%11s", formatea.format(saiAuxiliarPrestamoDTO.getMontoVencido().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                        
                                                    }
                                                }
                                               
                                                // --- TERMINA DE PROCESAR PRESTAMO ---------------------------------------------
                                                // Return de xtras
                                                PersonasPK personasPK = new PersonasPK(auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdgrupo(), auxiliaresDTO.getIdsocio());
                                                PersonasDTO personasDTO = personasService.buscarPersona(personasPK);
                                                String xtra1 = "Persona: " + personasDTO.getPersonasPK().getIdorigen() + "-" + personasDTO.getPersonasPK().getIdgrupo() + "-" + personasDTO.getPersonasPK().getIdsocio() + "\n"
                                                        + "Nombre: " + personasDTO.getNombre() + " " + personasDTO.getAppaterno() + " " + personasDTO.getApmaterno();
                                                String xtra2 = "RFC: " + personasDTO.getRfc();
                                                if (insertaAhorro == 1 || insertaCuentaEje == 1 || segurosComisionesProcesados == 1 || insertaPrestamo == 1) {
                                                    // ---------------------- TDD ---------------------------
                                                    // Activar TDD
                                                    
                                                    System.out.println("aquiiii");
                                                   TablasDTO activarTDD = valorTablas(idTabla, "activar_tdd");
                                                    System.out.println("aquiiiiii2");
                                                    System.out.println("activar tdd dato1:"+activarTDD.getDato1());
                                                    if (activarTDD.getDato1().equals("1")) {
                                                        System.out.println("entro");
                                                        // Se realiza el retiro en SYC
                                                        ResponseTransferenciaACuentaDTO rdepoPaso = new ResponseTransferenciaACuentaDTO();
                                                        boolean retiroTdd = tarjetaDeDebito.retiroTdd(rdepoPaso, auxiliaresPK, Double.parseDouble(Amount1), usuariosDTO.getIdusuario());
                                                        if (rdepoPaso.getXtra3().equals("1")) {
                                                            if (!retiroTdd) {
                                                                return rdepo;
                                                            }
                                                        }
                                                    }
                                                    System.out.println("siiiiiiiiiiiiiiiiiiiiiiiiiii");
                                                    // --- TERMINA TDD --------------------------------------
                                                    // ElementosProcesar es la cantidad de insert que se hicieron este mas adelante se compara con la cantidad de registros de temporales procesados
                                                    int elementosProcesar = insertaAhorro + insertaCuentaEje + segurosComisionesProcesados + insertaPrestamo;
                                                    // Procesa los movimientos de temporal
                                                    int elementosProcesados = saiFunciones.procesaTemporal(usuariosDTO.getIdusuario(), miSesion, fechaTrabajo, usuariosDTO.getIdorigen(), tipoPoliza, conceptoPoliza, usuariosDTO.getPingreso(), condonar, esResumen);
                                                    // Se actualiza el xtra3 con el nuevo saldo
                                                    auxiliaresDTO = auxiliaresService.buscarAuxiliar(auxiliaresPK);
                                                    xtra3 = xtra3 + String.format("%1$-15s", "Nuevo Saldo: ") + "$" + String.format("%11s", formatea.format(auxiliaresDTO.getSaldo().setScale(2, BigDecimal.ROUND_HALF_EVEN)));
                                                    // Verifica que los elementos totales sean los procesados
                                                    if (elementosProcesar == elementosProcesados) {
                                                        String referencia = "";
                                                        List<TemporalDTO> ListTemporalDTO = temporalService.ListTemporal(miSesion);
                                                        for (TemporalDTO temporalDTO : ListTemporalDTO) {
                                                            if (temporalDTO.getReferencia() != null && !"0".equals(temporalDTO.getReferencia())) {
                                                                referencia = referencia + temporalDTO.getReferencia() + "\n";
                                                            }
                                                        }
                                                        // --------------------------------------------------------
                                                        // --- Se ejecuta la funcion para el sorteo - BUENOS AIRES
                                                        // --------------------------------------------------------
                                                       
                                                        usuariosDTO = usuariosService.buscaUsuario(Integer.parseInt(tablasDTOusuario.getDato1().trim())); // consultamos usuarios
                                                        String[] arrPingre = usuariosDTO.getPingreso().split("\\|");
                                                        String numeroPoliza = arrPingre[arrPingre.length - 1];
                                                        int sorteoEntreAmigos = saiFunciones.saiBuenosairesSorteoEntreAmigos(usuariosDTO.getIdusuario().toString(), miSesion, auxiliaresDTO.getIdorigen().toString(), convertidor.formatoFecha(fechaTrabajo, "yyyyMM"), 1, numeroPoliza);
                                                        if (!"".equals(referencia) && sorteoEntreAmigos == 1 && referencia.length() > 2) {
                                                            boolean des = false;
                                                            String[] paso = referencia.split(" ");
                                                            for (String i : paso) {
                                                                if (i.equals("DESCALIFICADO")) {
                                                                    des = true;
                                                                    break;
                                                                }
                                                            }
                                                            if (des || referencia.isEmpty()) {
                                                                xtra5 = referencia + "\n";
                                                            } else {
                                                                xtra5 = xtra5 + "Su boleto para la promocion entre amigos es: " + "\n" + referencia + "\n";
                                                            }
                                                        }
                                                        
                                                        
                                                        
                                                        // --------------------------------------------------------
                                                        // Datos de auxiliares despues de aplicar el movimiento
                                                        AuxiliaresdDTO auxiliaresDTOdatos = auxiliaresDTOtipo(auxiliaresPK, tipoPoliza);
                                                        // --------------------------------------------------------
                                                        // --- Se ejecuta la funcion para puntomania - BUENOS AIRES
                                                        // --------------------------------------------------------
                                                       /* EntityManager em=AbstractFacade.getEntityManager();
                                                        System.out.println("    asdddddddddddddddddddddddddddddddddddddddddddddd addddddddddddddddddddddd   adadadadsa");
                                                        Query que=em.createNativeQuery("SELECT CASE WHEN (SELECT COUNT(*) FROM tablas t WHERE t.idtabla = 'param' "
                                                                                     + "AND t.idelemento = 'programa_puntos' "
                                                                                     + "AND dato1 IS NOT NULL "
                                                                                     + "AND dato1 != '' "
                                                                                     + "AND dato1 = '1' "
                                                                                     + "AND dato3 IS NOT NULL "
                                                                                     + "AND dato3 != ''  "
                                                                                     + "AND dato4 IS NOT NULL "
                                                                                     + "AND dato4 != ''  "
                                                                                     + "AND date((select fechatrabajo from origenes limit 1)) between date(dato3) and date(dato4) "
                                                                                     + "limit 1)>0 THEN 'true' ELSE 'false' END");
                                                        String r=(String) que.getSingleResult();
                                                        System.out.println("R:"+r);
                                                       */
                                                        TablasDTO puntomania = tablasService.buscaTablaPuntomania();
                                                        if (puntomania.getTablasPK() != null) {
                                                            // Se ejecuta la funcion que calcula los puntos
                                                            saiFunciones.saiPromocionPuntosSaicoop(usuariosDTO.getIdusuario(), miSesion, fechaTrabajo.toString(), usuariosDTO.getIdorigen(), tipoPoliza, auxiliaresDTOdatos.getIdpoliza());
                                                            // Se ejecuta la fucncion para obtener los puntos del folio
                                                            SaiPromocionPuntosSaicoopDTO saiPromocionPuntosSaicoop = saiFunciones.saiPromocionPuntosSaicoopImprimeTicket(usuariosDTO.getIdusuario(), miSesion, fechaTrabajo.toString(), auxiliaresDTOdatos.getTicket());
                                                            // Se imprime el titulo de la tabla
                                                            xtra5 = xtra5 + puntomania.getDato2() + "\n\n";
                                                            xtra5 = xtra5 + saiPromocionPuntosSaicoop.getOgs_nombre() + "\n";
                                                            if (saiPromocionPuntosSaicoop.getDescalificacion() == null || "".equals(saiPromocionPuntosSaicoop.getDescalificacion())) {
                                                                xtra5 = xtra5 + saiPromocionPuntosSaicoop.getPuntos_ganados() + "\n";
                                                                xtra5 = xtra5 + saiPromocionPuntosSaicoop.getPuntos_globales() + "\n";
                                                            } else {
                                                                xtra5 = xtra5 + saiPromocionPuntosSaicoop.getDescalificacion() + "\n";
                                                                xtra5 = xtra5 + saiPromocionPuntosSaicoop.getMotivodesc() + "\n";
                                                            }
                                                            xtra5 = xtra5 + "------------------------------------------\n\n";
                                                        }
                                                        
                                                        
                                                        
                                                        // ---------------------------------------------------------
                                                        // obtenemos el dato del xtra5
                                                        TablasDTO tablasDTOxtra5 = valorTablas(idTabla, "xtra5");
                                                        xtra5 = xtra5 + tablasDTOxtra5.getDato2();
                                                        // Si se inserto un prestamo y se aplico buscamos los datos del siguiente abono
                                                        if (insertaPrestamo != 0) {
                                                            // Datos del siguiente abono
                                                            SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTO.getAuxiliaresPK().getIdorigenp(), auxiliaresDTO.getAuxiliaresPK().getIdproducto(), auxiliaresDTO.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo);
                                                            if (saiAuxiliarPrestamoDTO != null) {
                                                                xtra4 = xtra4 + String.format("%1$-15s", "Siguiente abono: ") + "\n"
                                                                        + String.format("%1$-15s", "  Fecha: ") + " " + String.format("%11s", saiAuxiliarPrestamoDTO.getFechaSigAbono()) + "\n"
                                                                        + String.format("%1$-15s", "  Monto: ") + "$" + String.format("%11s", formatea.format(saiAuxiliarPrestamoDTO.getMontoPorVencer().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                            }
                                                        }
                                                        // Obtenemos la configuracion que utilizara el cliente para mostrar los datos
                                                        TablasDTO tablasDTOdeposito = valorTablas(idTabla, "ws_deposit_account_req");
                                                        // Retornmos los datos
                                                        rdepo.setIdTeller(IdTeller);
                                                        rdepo.setIdAcct(IdAcct);
                                                        rdepo.setAmount1(Amount1);
                                                        rdepo.setAmount2(Amount2);
                                                        rdepo.setDateTime(auxiliaresDTOdatos.getAuxiliaresDPK().getFecha());
                                                        rdepo.setConfig(tablasDTOdeposito.getDato2());
                                                        rdepo.setAutorizationNumber(String.valueOf(auxiliaresDTOdatos.getTransaccion()));
                                                        rdepo.setXtra1(xtra1);
                                                        rdepo.setXtra2(xtra2);
                                                        rdepo.setXtra3(xtra3);
                                                        rdepo.setXtra4(xtra4);
                                                        rdepo.setXtra5(xtra5);
                                                        rdepo.setResponseCode(CodigoError.CE000.getIdError());
                                                        
                                                        System.out.println("fgdgdgfdgdfgfdg");
                                                        // Elimino temporal
                                                        
                                                        //eliminaTemporal(miSesion);
                                                        // Si se realizo el deposito se cambia el estatus del registro de valida movimiento
                                                        actualizaMovimientoCajeroReceptor(IdTeller, IdAcct, TxType, IdOperation, Date, Amount1, Amount2, true);
                                                        eliminaTemporal(miSesion);
                                                        System.out.println("Depo:"+rdepo);
                                                    } else {
                                                        rdepo.setResponseCode(CodigoError.CE075.getIdError()); // Error al validar que se insertaron los temporales
                                                        System.out.println("Error al procesar elementos elementosProcesar = " + elementosProcesar + " y elementosProcesados = " + elementosProcesados);
                                                    }
                                                }
                                            } else {
                                                rdepo.setResponseCode(CodigoError.CE083.getIdError()); // Socio bloqueado
                                            }
                                        }
                                    }
                                } else {
                                    // El movimiento ya se aplico
                                    rdepo.setIdTeller(IdTeller);
                                    rdepo.setIdAcct(IdAcct);
                                    rdepo.setAmount1(Amount1);
                                    rdepo.setAmount2(Amount2);
                                    rdepo.setResponseCode(CodigoError.CE000.getIdError());
                                }
                            } else {
                                rdepo.setResponseCode(CodigoError.CE087.getIdError()); // Monto igual a 0
                            }
                        } else {
                            rdepo.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
                        }
                    } else {
                        rdepo.setResponseCode(CodigoError.CE097.getIdError()); // Usuario no activo
                    }
                } else {
                    rdepo.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada
                }
            } else {
                rdepo.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado
            }
        } catch (Exception e) {
            System.out.println("Error al realizar deposito. " + e.getMessage());
        }
        
        return rdepo;
    }

    
    
    
    
    
    
    
    
    // REALIZA LAS TRANSFERENCIAS ENTRE SUS MISMAS CUENTAS DEL SOCIO.
    @Override
    public ResponseTransferenciaACuentaDTO transferenciaACuentaPropia(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla) {
        // En este caso el celular del origen es el mismo que el del destino
        celOrigen = "";
        ResponseTransferenciaACuentaDTO deteo = realizarTransferencia(IdTeller, TxType, IdAcctO, IdAcctD, Date, IdOperation, Amount1, Amount2, Reference, Concept, SessionId, User, Password, tipoPoliza, conceptoPoliza, 1, tipoEntrada, true, condonar, esResumen, idTabla); // 1 = origen en xtra3
        // Esto es para Banca movil
        if (tipoEntrada == 1) {
            /*
            Logger TipoApp = Logger.getLogger("Es Bancamovil");
            TipoApp.log(Level.SEVERE, "ENTRA A BANCA MOVIL POR CUENTA PROPIA");
             */
            try {
                String fechayHora = convertidor.formatoFecha(saiFunciones.saiFechaDB("24"), "dd/MM/yyyy HH:mm:ss");
                // Envio un correo si el monto es igual o menor al configurado
                TablasDTO tablasMontoEmail = valorTablas(idTabla, "monto_menor_envio_email");
                if (Double.parseDouble(Amount1) <= Double.parseDouble(tablasMontoEmail.getDato1()) && emailOrigen.contains("@")) {
                    enviaEmail(Amount1, fechayHora, true, idTabla);
                }
                // Obtenemos el monto minimo para poder enviar el mensaje
                TablasDTO tablasMontoSMS = valorTablas(idTabla, "monto_minimo_sms");
                if (Double.parseDouble(Amount1) >= Double.parseDouble(tablasMontoSMS.getDato1())) {
                    if (deteo.getResponseCode().equals("000") && validaParametroSMS(idTabla) && validaEnviaSMS(idproductoOrigen, idTabla)) {
                        enviaSMS(Amount1, fechayHora, true, idTabla, true);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al enviar mensaje email o sms en transferenciaACuentaPropia. " + e.getMessage());
            }
        }
        return deteo;
    }

    // REALIZA LAS TRANSFERENCIAS ENTRE SU CATALOGO DE CUENTAS DE TERCEROS.
    @Override
    public ResponseTransferenciaACuentaDTO transferenciaACuentaDeTerceros(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla) {
        /* 
        Logger TransferAcuentaTer = Logger.getLogger("Transfe a cuenta terceros 1");
        TransferAcuentaTer.log(Level.WARNING, "DAtos Principal:");
         */
        celOrigen = "";
        celDestino = "";
        ResponseTransferenciaACuentaDTO deteo = realizarTransferencia(IdTeller, TxType, IdAcctO, IdAcctD, Date, IdOperation, Amount1, Amount2, Reference, Concept, SessionId, User, Password, tipoPoliza, conceptoPoliza, 2, tipoEntrada, false, condonar, esResumen, idTabla); // 2 = destino en xtra3 - Es transferencia a cuentas propias
        // Esto es para Banca movil
        if (tipoEntrada == 1) {
            /*
            Logger TipoAppTer = Logger.getLogger("Es Bancamovil");
            TipoAppTer.log(Level.SEVERE, "ENTRA A BANCA MOVIL POR CUENTA TERCEROS");
             */
            try {
                String fechayHora = convertidor.formatoFecha(saiFunciones.saiFechaDB("24"), "dd/MM/yyyy HH:mm:ss");
                // Envio un correo si el monto es igual o menor al configurado
                TablasDTO tablasMontoEmail = valorTablas(idTabla, "monto_menor_envio_email");
                if (Double.parseDouble(Amount1) <= Double.parseDouble(tablasMontoEmail.getDato1()) && emailOrigen.contains("@")) {
                    enviaEmail(Amount1, fechayHora, false, idTabla);
                }
                // Obtenemos el monto minimo para poder enviar el mensaje
                TablasDTO tablasDTO = valorTablas(idTabla, "monto_minimo_sms");
                /*
                Logger Montoconfig = Logger.getLogger("Monto Configurado");
                Montoconfig.log(Level.SEVERE, "Monto configurado: " + Double.parseDouble(tablasDTO.getDato1()) + " ");
                Logger MontoAplic = Logger.getLogger("Monto Aplicado");
                MontoAplic.log(Level.SEVERE, "Monto Aplicado: " + Double.parseDouble(Amount1) + " ");
                 */
                // Si el monto aplicado es mayor o igual al monto configurado se envia sms
                if (Double.parseDouble(Amount1) >= Double.parseDouble(tablasDTO.getDato1())) {
                    if (deteo.getResponseCode().equals("000") && validaParametroSMS(idTabla)) {
                        /*
                        Logger DatosOrigen = Logger.getLogger("Datos de Origen");
                        DatosOrigen.log(Level.WARNING, "Datos del producto de origen " + idproductoOrigen
                                + " Hora: " + fechayHora
                                + " Monto: " + Amount1);
                         */
                        if (validaEnviaSMS(idproductoOrigen, idTabla)) {
                            enviaSMS(Amount1, fechayHora, false, idTabla, true);
                        }
                        /*
                        Logger DatosDestino = Logger.getLogger("Datos del Destino");
                        DatosDestino.log(Level.WARNING, "Datos del producto del destino " + idproductoDestino
                                + " Hora: " + fechayHora
                                + " Monto: " + Amount1);
                         */
                        if (validaEnviaSMS(idproductoDestino, idTabla)) {
                            enviaSMS(Amount1, fechayHora, false, idTabla, false);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al enviar mensaje email o sms en transferenciaACuentaDeTerceros." + e.getMessage());
            }
        }
        return deteo;
    }

    /*
    // ENVIA EL SMS
    private void enviaSMS(String Amount1, String fechayHora, boolean esPropia, String idTabla) {
        TablasDTO tablasUrlSMS = valorTablas(idTabla, "urlsms");
        TablasDTO tablasContenidoSMS;
        EnviaSMS enviaSMS = new EnviaSMS();
        // Si es a cuentas propias solo envia dados del retiro y solo al origen
        if (esPropia) {
            tablasContenidoSMS = valorTablas(idTabla, "sms_retiro_cuenta_propia");
            enviaSMS.enviar(tablasUrlSMS.getDato2(), celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
        } else {
            tablasContenidoSMS = valorTablas(idTabla, "sms_retiro_cuenta_tercero");
            enviaSMS.enviar(tablasUrlSMS.getDato2(), celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
            if (tipoDestino == 2) {
                tablasContenidoSMS = valorTablas(idTabla, "sms_abono_cuenta_tercero");
                enviaSMS.enviar(tablasUrlSMS.getDato2(), celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
            } else {
                tablasContenidoSMS = valorTablas(idTabla, "sms_deposito_cuenta_tercero");
                enviaSMS.enviar(tablasUrlSMS.getDato2(), celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
            }
        }
    }
     */

 /*
    INSERT INTO tablas(idtabla,idelemento,nombre,dato1,dato3,dato4,dato5)VALUES(
    'siscoop','servidor-sms','envios-sms','url=sms.prosa.com.mx','port=80','protocol=http','timeout=40');

    INSERT INTO tablas(idtabla,idelemento,nombre,dato1,dato2,dato3)VALUES(
    'siscoop', 'seguridad-sms', 'envios-sms', 
    'clientid=42610', 'password=dc65becc7c2e21abb23c6ff20a38ef0d0384efce8b87d161ebe1c82fecbe205d', 'user=majuanjose@cajamitras.com');
     */
    // ENVIA EL SMS
    private void enviaSMS(String Amount1, String fechayHora, boolean esPropia, String idTabla, boolean esOrigen) {
        //se crea una instancia que consume calixta
        ProsaCalixta pcal = null;
        //consulto en tablas si existe la url del script de san nicolas para envio de mensajes
        TablasDTO tablasUrlSMS = valorTablas(idTabla, "urlsms");
        //estas son las instancias de la configuracion de consumo de calixta, esto de mitras
        TablasDTO tbasvr = null;//configuraciones para consumir
        TablasDTO tbaseg = null;//configuraciones de seguridad
        boolean esCalixta = false;
        //sino existe el script de php de san nicolas entonces busca las configuraciones de calixta
        if (tablasUrlSMS.getDato2().length() == 0) {
            //consulto en tablas si existe una configuracion de consumo de calixta, esto de san nicolas
            tbasvr = tablasService.buscaTabla(new TablasPK("siscoop_banca_movil", "servidor-sms"));
            tbaseg = tablasService.buscaTabla(new TablasPK("siscoop_banca_movil", "seguridad-sms"));
            //si existen las configuraciones, saca los datos necesarios para consumir calixta(mitras)
            if (tbasvr.getNombre() != null && tbaseg.getNombre() != null) {
                String clienteid = tbaseg.getDato1().split("=")[1];
                String password = tbaseg.getDato2().split("=")[1];
                String user = tbaseg.getDato3().split("=")[1];
                String url = tbasvr.getDato1().split("=")[1];
                int port = Integer.parseInt(tbasvr.getDato3().split("=")[1].trim());
                String protocol = tbasvr.getDato4().split("=")[1];
                String timeout = tbasvr.getDato5().split("=")[1];//este hasta el momento no se usara 
                //ProsaCalixta(String clientid,String password,String user,String protocol,String url,int port)
                pcal = new ProsaCalixta(clienteid, password, user, protocol, url, port);
                esCalixta = true;
            } else {
                System.out.println("Error: No esta configurado el parametro para enviar SMS");
            }
        }
        TablasDTO tablasContenidoSMS;
        EnviaSMS enviaSMS = null;
        if (esCalixta == false) {
            enviaSMS = new EnviaSMS();
        }

        // Si es a cuentas propias solo envia dados del retiro y solo al origen
        if (esPropia) {
            tablasContenidoSMS = valorTablas(idTabla, "sms_retiro_cuenta_propia");
            //SI EL TAMAÑO DEL DATO 2 ES DIFERENTE A CERO QUIERE DECIR QUE EL MENSAJE ES A SAN NICO
            if (tablasUrlSMS.getDato2().length() != 0) {
                enviaSMS.enviar(tablasUrlSMS.getDato2(), celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
            } else {//System.out.print("b - " + b); SI ES IGUAL A CERO EL TAMAÑO DEL DATO2 ENTONCES ES A MITRAS
                System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
                if (esCalixta == true) {
                    // System.out.print(celOrigen);
                    // System.err.println("TRANSFERENCIA A CUENTA PROPIA MENSAJE " + LocalTime.now());
                    Logger MensajeTransferenciaProp = Logger.getLogger("Transferencia a cuenta propia");
                    MensajeTransferenciaProp.log(Level.INFO, "TRANSFERENCIA A CUENTA PROPIA MENSAJE " + LocalTime.now());

                    pcal.enviar(celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                }
            }
        } else {//mensaje a terceros
            System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
            if (esOrigen == true) {
                tablasContenidoSMS = valorTablas(idTabla, "sms_retiro_cuenta_tercero");
                /*
                Logger TablaSmsRetiroCuentaTerceros = Logger.getLogger("TablaRetiroCuentaTerceros");
                TablaSmsRetiroCuentaTerceros.log(Level.INFO, "Tabla UrlsSmsDato2" + tablasUrlSMS.getDato2());
                 */
                if (tablasUrlSMS.getDato2().length() != 0) {
                    enviaSMS.enviar(tablasUrlSMS.getDato2(), celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                } else {
                    System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
                    if (esCalixta == true) {
                        Logger MensajeTransferenciaOrigen = Logger.getLogger("Transferencia a cuenta tercero origen");
                        MensajeTransferenciaOrigen.log(Level.INFO, "MENSAJE A ORIGEN DE TRANSFERENCIA A CUENTA DE TERCERO " + LocalTime.now());
                        /*
                        System.out.println("MENSAJE A ORIGEN DE TRANSFERENCIA A CUENTA DE TERCERO " + LocalTime.now());
                        System.err.println("TRANSFERENCIA A CUENTA DE TERCERO MENSAJE " + LocalTime.now());
                         */
                        pcal.enviar(celOrigen, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                    }
                }
            }
            System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
            if (esOrigen == false) {
                if (tipoDestino == 2) {
                    tablasContenidoSMS = valorTablas(idTabla, "sms_abono_cuenta_tercero");
                    if (tablasUrlSMS.getDato2().length() != 0) {
                        enviaSMS.enviar(tablasUrlSMS.getDato2(), celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                    } else {
                        System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
                        if (esCalixta == true) {
                            Logger MensajeTransferenciaDestinoAbono = Logger.getLogger("Transferencia a cuenta tercero abono destino");
                            MensajeTransferenciaDestinoAbono.log(Level.INFO, "MENSAJE A DESTINO DE TRANSFERENCIA A CUENTA DE TERCERO ABONO " + LocalTime.now());
                            /*
                            System.out.println("MENSAJE A DESTINO DE TRANSFERENCIA A CUENTA DE TERCERO ABONO " + LocalTime.now());
                            System.err.println("TRANSFERENCIA A CUENTA DE TERCERO MENSAJE " + LocalTime.now());
                             */
                            pcal.enviar(celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                        }
                    }
                } else {
                    tablasContenidoSMS = valorTablas(idTabla, "sms_deposito_cuenta_tercero");
                    if (tablasUrlSMS.getDato2().length() != 0) {
                        enviaSMS.enviar(tablasUrlSMS.getDato2(), celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                    } else {
                        System.out.println("    ");//este es un system.out.println para que el formateo no me transforme el "else{if(){}}" por "else if(){}"
                        if (esCalixta == true) {
                            Logger MensajeTransferenciaDestionoDepos = Logger.getLogger("Transferencia a cuenta tercero deposito destino");
                            MensajeTransferenciaDestionoDepos.log(Level.INFO, "MENSAJE A DESTIONO DE TRANSFERENCIA A CUENTA DE TERCERO DEPOSITO " + LocalTime.now());
                            /*
                            System.out.println("MENSAJE A DESTIONO DE TRANSFERENCIA A CUENTA DE TERCERO DEPOSITO " + LocalTime.now());
                            System.err.println("TRANSFERENCIA A CUENTA DE TERCERO MENSAJE " + LocalTime.now());
                             */

                            pcal.enviar(celDestino, contenidoSMS(tablasContenidoSMS.getDato2(), Amount1, fechayHora));
                        }
                    }
                }
            }
        }
    }

    // RELLENA EL CONTENIDO DEL SMS
    private String contenidoSMS(String contenidoSMS, String Amount1, String fechayHora) {
        return contenidoSMS.replace("@monto@", Amount1)
                .replace("@fechayHora@", fechayHora)
                .replace("@productoOrigen@", nomOrigen)
                .replace("@productoDestino@", nomDestino)
                .replace("@autorizacionOrigen@", nAo)
                .replace("@autorizacionDestino@", nAo);
    }

    // Enviar Email
    private void enviaEmail(String Amount1, String fechayHora, boolean esPropia, String idTabla) {
        TablasDTO tablasEmail = valorTablas(idTabla, "parametros_email");
        TablasDTO tablasContenidoEmail;
        if (esPropia) {
            tablasContenidoEmail = valorTablas(idTabla, "contenido_email_cuenta_propia");
        } else {
            tablasContenidoEmail = valorTablas(idTabla, "contenido_email_cuenta_tercero");
        }
        String contenidoEmail = tablasContenidoEmail.getDato2();
        contenidoEmail = contenidoEmail.replace("@monto@", Amount1)
                .replace("@fechayHora@", fechayHora)
                .replace("@productoOrigen@", nomOrigen)
                .replace("@productoDestino@", nomDestino)
                .replace("@autorizacion_origen@", nAo)
                .replace("@autorizacion_destino@", nAd);
        // PARAMETROS:                         host,               puerto,                   usuario,              password,              email,       destinatario,     tipo SSL o TLS,           titulo del correo,           mensaje
        new EnviarMail().enviar(tablasEmail.getDato1(), tablasEmail.getDato2(), tablasEmail.getDato3(), tablasEmail.getDato4(), tablasEmail.getDato3(), emailOrigen, tablasEmail.getDato5(), tablasContenidoEmail.getDato1(), contenidoEmail);
    }

    // VALIDA QUE LOS PARAMETROS ESTEN CONFIGURADOS PARA EL ENVIO DE SMS
    private boolean validaParametroSMS(String idTabla) {
        TablasPK tpk = new TablasPK(idTabla, "smsactivo");
        TablasDTO tdto = tablasService.buscaTabla(tpk);
        if (tdto != null) {
            if (tdto.getDato1() != null) {
                if (tdto.getDato1().equals("1")) {
                    return true;
                } else {
                    System.out.println("Error: Esta desactivado el envio de SMS");
                }
                System.out.println("Error: No esta configurado el parametro para enviar SMS");
            }
            System.out.println("Error: No esta configurada la tabla para enviar SMS");
        }
        return false;
    }

    /*  el opa de 32 digitos(00000000000000001230012300001234) o idacct se construye de la siguiente forma
        13 ceros + 6 digitos de idorigen + 5 digitos de idproducto + 8 digitos de idauxiliar
        0000000000000 000123 00123 00001234
    
        el ogs de 32 digitos (00000000000000000000123410000123) o idclient se construye de la siguiente forma
        18 ceros + 6 de idorigen + 2 de idgrupo + 6 de idsocio
        000000000000000000 001234 10 000123
     */
 /* REALIZA LA TRANSFERENCIA.*/
    private ResponseTransferenciaACuentaDTO realizarTransferencia(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int oriDes, int tipoEntrada, boolean esPropias, boolean condonar, boolean esResumen, String idTabla) {
        /*
        Logger realizaTrans = Logger.getLogger("RealizaTRasferencia");
        realizaTrans.log(Level.WARNING, "DAtos Recibidos: IdTeller:");
         */
        movimiento = 0;
        // Voy preparando el response con los datos que tengo disponibles
        ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
        rdepo.setResponseCode(CodigoError.CE096.getIdError());
        
        
        if (esPropias == true) {
            rdepo.setDescription("Transferencia a Cuenta Propia");
        } else {
            rdepo.setDescription("Transferencia a Cuenta de Tercero");
        }
        try {
            // Valida el usuario y contraseña del metodo
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida el estatus del origen
                if (estatusOrigen(idusuario(IdTeller, idTabla))) {
                    UsuariosDTO usuario = datoUsuario(IdTeller, idTabla);
                    // Valida que el usuario este activo
                    if (usuario.isActivo()) {
                        // No se puede realizar una transferencia al mismo folio solo si es a cuentas de terceros
                        if (!IdAcctO.equals(IdAcctD) || !esPropias) {
                            // Se valida el folio de origen y destino
                            if (validaciones.validaOPA(IdAcctO.trim()) == true && validaciones.validaOPA(IdAcctD.trim()) == true) {
                                // Verifica si esta configurado el producto para el origen
                                if (productoValidoParaMovieminto(esPropias, 0, IdAcctO, idTabla)) {
                                    // Verifica si esta configurado el producto para el destino
                                    if (productoValidoParaMovieminto(esPropias, 1, IdAcctD, idTabla)) {
                                        // Valida que el monto sea mayor a cero
                                        if (new BigDecimal(Amount1).compareTo(BigDecimal.ZERO) == 1) {
                                            //if (new BigDecimal(Amount1) <= 123)
                                            // Se generan los folios para el origen
                                            int[] opaOrigen = convertidor.getOPA(IdAcctO);
                                            //System.out.println("arreglo origen " + Arrays.toString(opaOrigen));
                                            //--------------------------------------------------idorigen-----idproducto----idauxiliar
                                            AuxiliaresPK auxiliaresPKOrigen = new AuxiliaresPK(opaOrigen[0], opaOrigen[1], opaOrigen[2]);

                                            AuxiliaresDTO auxiliaresDTOOrigen = auxiliaresService.buscarAuxiliar(auxiliaresPKOrigen);
                                            //System.out.println(auxiliaresDTOOrigen.getAuxiliaresPK().getIdorigenp() + " " + auxiliaresDTOOrigen.getAuxiliaresPK().getIdproducto() + " " + auxiliaresDTOOrigen.getAuxiliaresPK().getIdauxiliar() + " " + auxiliaresDTOOrigen.getIdorigen() + " " + auxiliaresDTOOrigen.getIdgrupo() + " " + auxiliaresDTOOrigen.getIdsocio());
                                            //String misesion = "W" + usuario.getIdusuario() + "-" + convertidor.getOGSsesion(auxiliaresDTOOrigen.getIdorigen(), auxiliaresDTOOrigen.getIdgrupo(), auxiliaresDTOOrigen.getIdsocio());
                                            //ELIMINAMOS TODOS LOS TEMPORALES QUE SE HAYAN QUEDADO SIN PROCESAR DE ESE SOCIO Y CON EL USUARIO DE BANCA MOVIL
                                            /**
                                             * *************************************************************************************************************
                                             */

                                            //eliminaTemporal(misesion);//elimina todos los temporales que se hayan quedado sin procesar de ese socio y con el usuario de banca movil
                                            String IdClient = convertidor.getOGS32(auxiliaresDTOOrigen.getIdorigen(), auxiliaresDTOOrigen.getIdgrupo(), auxiliaresDTOOrigen.getIdsocio());
                                            // Esto es para bloquear socios en una lista negra
                                            if (!socioBloqueado(IdClient, usuario.getIdusuario(), idTabla)) {
                                                if (estaEnGrupoPermitido(IdAcctD, idTabla) == true) {
                                                    ProductosDTO productosDTOOrigen = productosService.buscarProducto(auxiliaresDTOOrigen.getAuxiliaresPK().getIdproducto());
                                                    idproductoOrigen = productosDTOOrigen.getIdproducto();
                                                    nomOrigen = productosDTOOrigen.getNombre();
                                                    PersonasPK personasPKOrigen = new PersonasPK(auxiliaresDTOOrigen.getIdorigen(), auxiliaresDTOOrigen.getIdgrupo(), auxiliaresDTOOrigen.getIdsocio());
                                                    PersonasDTO personasDTOOrigen = personasService.buscarPersona(personasPKOrigen);
                                                    // Email del socio
                                                    emailOrigen = personasDTOOrigen.getEmail();
                                                    // Numero celular origen
                                                    celOrigen = validaNumeroCelular(personasDTOOrigen.getCelular());
                                                    // Se generan los folios para el destino
                                                    int[] opaDestino = convertidor.getOPA(IdAcctD);
                                                    //System.out.println("arreglo detino " + Arrays.toString(opaDestino));
                                                    AuxiliaresPK auxiliaresPKDestino = new AuxiliaresPK(opaDestino[0], opaDestino[1], opaDestino[2]);
                                                    AuxiliaresDTO auxiliaresDTODestino = auxiliaresService.buscarAuxiliar(auxiliaresPKDestino);
                                                    //System.out.println(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp() + " " + auxiliaresDTODestino.getAuxiliaresPK().getIdproducto() + " " + auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar() + " " + auxiliaresDTODestino.getIdorigen() + " " + auxiliaresDTODestino.getIdgrupo() + " " + auxiliaresDTODestino.getIdsocio());
                                                    ProductosDTO productosDTODestino = productosService.buscarProducto(auxiliaresDTODestino.getAuxiliaresPK().getIdproducto());
                                                    idproductoDestino = productosDTODestino.getIdproducto();
                                                    nomDestino = productosDTODestino.getNombre();
                                                    PersonasPK personasPKDestino = new PersonasPK(auxiliaresDTODestino.getIdorigen(), auxiliaresDTODestino.getIdgrupo(), auxiliaresDTODestino.getIdsocio());
                                                    PersonasDTO personasDTODestino = personasService.buscarPersona(personasPKDestino);
                                                    // Numero celular destino
                                                    celDestino = validaNumeroCelular(personasDTODestino.getCelular());
                                                    // Se obtiene la fecha de trabajo.
                                                    Date fechaTrabajo = saiFunciones.saiFechaTrabajo(auxiliaresDTOOrigen.getIdorigen()); // consulto origenes para sacar la fecha de trabajo
                                                    // Consulto el usuario con el que creare la poliza
                                                    TablasDTO tablasDTO = valorTablas(idTabla, IdTeller);
                                                    UsuariosDTO usuariosDTO = usuariosService.buscaUsuario(Integer.parseInt(tablasDTO.getDato1().trim()));
                                                    // Si no existe el usuario no hace nada (se queda con error 096). 
                                                    if (usuariosDTO != null) {
                                                        // Verifica si tiene fondos para la transferencia
                                                        BigDecimal saldoDisponible = auxiliaresDTOOrigen.getSaldo().subtract(auxiliaresDTOOrigen.getGarantia());
                                                        // ---------------------- TDD -------------------------------------
                                                        // Activar TDD
                                                        TablasDTO activarTDDSaldo = valorTablas(idTabla, "activar_tdd");
                                                        if ("1".equals(activarTDDSaldo.getDato1())) {
                                                            // Obtenemos el saldo disponible de SYC para el origen y el destino, si manda error termina el proceso
                                                            tarjetaDeDebito.validaSaldoTDD(rdepo, auxiliaresPKOrigen, auxiliaresPKDestino, usuario.getIdusuario());
                                                            // Si el xtra2 no manda un 1 entonces no es porducto de TDD
                                                            if (rdepo.getXtra2() != null) {
                                                                if (rdepo.getXtra2().equals("1")) {
                                                                    // Si el Xtra1 tiene dato es porque consulto correctamente el saldo en SYC
                                                                    if (rdepo.getXtra1().length() > 0) {
                                                                        saldoDisponible = new BigDecimal(rdepo.getXtra1());
                                                                    } else {
                                                                        return rdepo;
                                                                    }
                                                                }
                                                            }
                                                            rdepo.setXtra2("");
                                                        }

                                                        // --- TERMINA TDD ------------------------------------------------
                                                        // Valida que el saldo disponible sea menor o igual al monto a retirar
                                                        if (validaFondos(saldoDisponible, Amount1)) {
                                                            // Valida el monto maximo que puede transferir en un menor (Si no es menor continua)
                                                            if (restringirSaldoUDIS(rdepo, auxiliaresDTODestino, Amount1, idTabla)) {
                                                                // Elimino todos los temporales que sean de este usuario concatenado al socio
                                                                //String misesion = "W" + usuariosDTO.getIdusuario() + "-" + convertidor.getOGSsesion(auxiliaresDTOOrigen.getIdorigen(), auxiliaresDTOOrigen.getIdgrupo(), auxiliaresDTOOrigen.getIdsocio());
                                                                String misesion = "W" + usuario.getIdusuario() + "-" + convertidor.getOGSsesion(auxiliaresDTOOrigen.getIdorigen(), auxiliaresDTOOrigen.getIdgrupo(), auxiliaresDTOOrigen.getIdsocio());
                                                                eliminaTemporal(misesion);//elimina todos los temporales que se hayan quedado sin procesar de ese socio y con el usuario de banca movil
                                                                Integer insertaOrigen;
                                                                Integer insertaDestino = 0;
                                                                Integer insertaAdelantoDeInteres = 0;
                                                                Integer segurosComisionesProcesados = 0;
                                                                String xtra3 = "";
                                                                String xtra4 = "";
                                                                // Que tipo de producto es el origen
                                                                ProductosDTO productosDTOTipoOrigen = productosService.buscarProducto(auxiliaresDTOOrigen.getAuxiliaresPK().getIdproducto()); // Consulto productos
                                                                // VALIDA QUE EL ORIGEN AL QUE SE LE VA A RETIRAR NO SEA UN PRESTAMO/**********************/ 
                                                                /**
                                                                 * *************************************************************************************************************************************************
                                                                 */
                                                                if (productosDTOTipoOrigen.getTipoproducto() != 2) {
                                                                    BigDecimal montoAdelantoInteres = BigDecimal.ZERO;
                                                                    AbonoAdelantadoInteres abonoAdelantaInteres = null;
                                                                    // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                                                                    SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTOOrigen = ejecutaSaiAuxiliarAhorro(auxiliaresDTOOrigen.getAuxiliaresPK().getIdorigenp(), auxiliaresDTOOrigen.getAuxiliaresPK().getIdproducto(), auxiliaresDTOOrigen.getAuxiliaresPK().getIdauxiliar());
                                                                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                                    //SE INSERTA EN TEMPORAL EL CARGO AL AHORRO ORIGEN AL QUE SE VA A RETIRAR PARA TRANSFERIRLO A UNA CUENTA PROPIA O A UNA DE TERCEROS/**********************/ 
                                                                    /**
                                                                     * ***********************************************************************************************************************************************
                                                                     */
                                                                    System.out.println("se inserta origen ahorro");
                                                                    insertaOrigen = insertaTemporalCaptacion(auxiliaresDTOOrigen, misesion, usuariosDTO.getIdusuario(), Amount1, "0", "0.00", " ", false, bigd, saiAuxiliarAhorroDTOOrigen.getMontoIo(), fechaTrabajo);
                                                                   // System.out.println("se inserto");
                                                                    ProductosDTO productosDTODestinoTemp = productosService.buscarProducto(auxiliaresDTODestino.getAuxiliaresPK().getIdproducto()); // Consulto productos
                                                                    // Se procesa el producto de destino - si es un ahorro
                                                                    tipoDestino = productosDTODestinoTemp.getTipoproducto();
                                                                    // ------ SI EL DESTINO ES UN AHORRO ENTRA ----------------------
                                                                    if (tipoDestino == 0) {
                                                                        //System.out.println("destino ahorro");
                                                                        //SI EL PRODUCTO DE DESTINO ES UN AHORRO SE INSERTA EN TEMPORAL COMO ABONO /**********************/ 
                                                                        /**
                                                                         * ***********************************************************************************************************************************************
                                                                         */
                                                                        // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                                                                        SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTODestino = ejecutaSaiAuxiliarAhorro(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar());
                                                                        // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                                        //System.out.println("inserta temporal captacion");
                                                                        insertaDestino = insertaTemporalCaptacion(auxiliaresDTODestino, misesion, usuariosDTO.getIdusuario(), Amount1, "0", "0.00", "", true, bigd, saiAuxiliarAhorroDTODestino.getMontoIo(), fechaTrabajo);
                                                                        //System.out.println("se inserto");
                                                                        // --- TERMINA AHORRO -------------------------------------------
                                                                        //System.out.println("termina destino ahorro");
                                                                    } else if (tipoDestino == 2) {
                                                                        System.out.println("destino prestamo");
                                                                        // SI EL DESTINO ES UN PRESTAMO ENTRA 
////////////////////////////////////////////////////////////////////////////////     
/*
                                        BigDecimal total_abono_ingresado = BigDecimal.ZERO;
                                        total_abono_ingresado = new BigDecimal(Amount1).add(new BigDecimal(Amount2));

                                        BigDecimal total_credito = BigDecimal.ZERO;
                                        int comparaSaldoCredito = 0;
                                        SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo);
                                        total_credito = auxiliaresDTODestino.getSaldo().add(saiAuxiliarPrestamoDTO.getMontoIoTotal());
                                        total_credito = total_credito.add(saiAuxiliarPrestamoDTO.getIvaIoTotal());
                                        total_credito = total_credito.add(saiAuxiliarPrestamoDTO.getMontoImTotal());
                                        total_credito = total_credito.add(saiAuxiliarPrestamoDTO.getIvaImTotal());
                                        total_credito = total_credito.add(saiAuxiliarPrestamoDTO.getComisionNpTotal());                          
                                        comparaSaldoCredito = total_credito.compareTo(total_abono_ingresado);
                                        // se revisa que el saldoTotal de credito sea mayor a la suma de Amount1 y Amount2 
                                        if (comparaSaldoCredito >= 0){
                                            */
                                        // productoDTO.setAvailBalance(String.valueOf(total_a_pagar));

                                                                      //  if ()
                                                                        /**
                                                                         * ***********************************************************************************************************************************************
                                                                         */
                                                                        List<String> dtsPrest = datosPrestamo(auxiliaresDTODestino);
                                                                        String fechaSigAbono = dtsPrest.get(0);
                                                                        BigDecimal montoTotalAPagar = new BigDecimal(dtsPrest.get(1));
                                                                        BigDecimal montoTotalAPagarMasAdelantoDeInteres = new BigDecimal(dtsPrest.get(2));
                                                                        BigDecimal montoInteres = new BigDecimal(dtsPrest.get(3));
                                                                        BigDecimal ivaMontoInteres = new BigDecimal(dtsPrest.get(4));
                                                                        BigDecimal adelantoInteres = new BigDecimal(dtsPrest.get(5));
                                                                        String fechaSigPagoIntAde = dtsPrest.get(6);
                                                                        // SI ES PRESTAMO HIPOTECARIO Y EL MONTO A PAGAR ES MENOR AL TOTAL A CUBRIR ENTONCES TERMINAMOS Y RETORNA UN ERROR AL CLIENTE
                                                                        if (auxiliaresDTODestino.getTipoamortizacion() == 5) {
                                                                            Double montoMaximoCubrir = Double.valueOf(montoTotalAPagar.toString());
                                                                            // PARA QUE PUEDA PROCESAR SI ES HIPOTECARIO EL MONTO TIENE QUE SER EXACTO
                                                                            if (Double.parseDouble(Amount1) > montoMaximoCubrir) {
                                                                                rdepo.setResponseCode(CodigoError.CE076.getIdError()); // El monto en los hipotecarios debe ser exacto
                                                                                //eliminaTemporal(misesion);//BORRAMOS DE TEMPORAL EL CARGO AL AHORRO 
                                                                                return rdepo;//<-------------- TERMINA
                                                                            }
                                                                        }
                                                                        //VERIFICAMOS SI EL ADELANTO DE INTERES ESTA HABILITADO, 
                                                                        //OBTENEMOS EL PRODUCTO DE AHORRO EN EL QUE SE ABONARA EL ADELANTO Y VERIFICAMOS QUE EL DATO1 SEA MAYOR A CERO
                                                                        System.out.println("Revisa MontoInteres");
                                                                        
                                                                        if (montoInteres.compareTo(BigDecimal.ZERO) == 1) {
                                                                     //       System.out.println("MontoInteres: " + montoInteres.toString());
                                                                            //System.out.println("0");
                                                                            TablasPK tpkCisp = new TablasPK("param", "cobrar_interes_hasta_el_sig_pago");
                                                                            TablasDTO tbsdtoCisp = tablasService.buscaTabla(tpkCisp);
                                                                            if (tbsdtoCisp != null) {
                                                                                //System.out.println("1");
                                                                                if (tbsdtoCisp.getDato1() != null) {
                                                                                    //System.out.println("2");
                                                                                    if (tbsdtoCisp.getDato2() != null) {
                                                                                        //System.out.println("3");
                                                                                        if (Integer.parseInt(tbsdtoCisp.getDato1().trim()) > 0) {
                                                                                            //System.out.println("4");
                                                                                            /*
                                                                                                si el monto es menor a montoTatalAPagar // lo dejo seguir
                                                                                                si el monto es igual a montoTotalAPagar// lo dejo pasar
                                                                                                si el monto es mayor a montoTotalAPagar pero menor a montoTotalAPagarMasAdelantoDeInteres
                                                                                                si el monto es igual a montoTotalAPagarMasAdelantoDeInteres
                                                                                                si el monto es mayor a montoTotalAPagarMasAdelantoDeInteres
                                                                                             */
                                                                                            //CALCULAMOS EL ADELANTO DE INTERES Y EL MONTO A ABONAR

                                                                                            if (new BigDecimal(Amount1).compareTo(montoTotalAPagar) == 1) {//si es mayor a montoTotalAPagar
                                                                                                if (new BigDecimal(Amount1).compareTo(montoTotalAPagarMasAdelantoDeInteres) == 1) {//si el monto es mayor a montoTotalAPagarMasAdelantoDeInteres
                                                                                                    montoAdelantoInteres = montoAdelantoInteres.add(adelantoInteres);
                                                                                                    //Amount1 = (new BigDecimal(Amount1).subtract(montoAdelantoInteres)).toString();
                                                                                                } else {//si es mayor a montoTotalAPagar y no es mayor a montoTotalAPagarMasAdelantoDeInteres// menos o exacto de adelanto de interes 
                                                                                                    montoAdelantoInteres = new BigDecimal(Amount1).subtract(montoTotalAPagar);
                                                                                                    //Amount1=(new BigDecimal(Amount1).subtract(montoAdelantoInteres)).toString();
                                                                                                }

                                                                                            }
                                                                                            if (montoAdelantoInteres.compareTo(BigDecimal.ZERO) == 1) {
                                                                                                //System.out.println("5");//si el monto de adelanto de interes es mayor a cero restalo al monto
                                                                                                //SE LE RESTA EL ADELANTO DE INTERES AL MONTO A ABONAR AL CREDITO
                                                                                                Amount1 = (new BigDecimal(Amount1).subtract(montoAdelantoInteres)).toString();
                                                                                                if (new BigDecimal(Amount1).compareTo(BigDecimal.ZERO) == 1) {
                                                                                                    //System.out.println("6");
                                                                                                    AuxiliaresDTO auxDTOAdeInt = auxiliaresSebuscaAuxiliarPorOGSP(auxiliaresDTODestino.getIdorigen(), auxiliaresDTODestino.getIdgrupo(), auxiliaresDTODestino.getIdsocio(), Integer.parseInt(tbsdtoCisp.getDato2().trim()));
                                                                                                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                                                                    if (auxDTOAdeInt != null) {
                                                                                                        //System.out.println("7");
                                                                                                        if (auxDTOAdeInt.getAuxiliaresPK() != null) {
                                                                                                            //System.out.println("8");
                                                                                                            if (auxDTOAdeInt.getAuxiliaresPK().getIdauxiliar() != null) {
                                                                                                                //System.out.println("9");
                                                                                                                /*
                                                                                                            Ademas, al insertar el adelanto de interés en la tabla temporal para procesar junto con el pago del préstamo, en ese insert en el campo referencia debes poner una cadena de texto con los siguientes datos:
                                                                                                            AI|<idorigenp del préstamo>
                                                                                                            |<idproducto del préstamo>
                                                                                                            |<idauxiliar del préstamo>
                                                                                                            |<monto del interés>
                                                                                                            |<monto del IVA>
                                                                                                            |<fecha de cuando debe aplicarse el pago del interés>
                                                                                                                 */
                                                                                                                String referencia = "AI|" + auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp() + "|"
                                                                                                                        + auxiliaresDTODestino.getAuxiliaresPK().getIdproducto() + "|"
                                                                                                                        + auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar() + "|"
                                                                                                                        + montoInteres + "|" + ivaMontoInteres + "|" + fechaSigPagoIntAde;
                                                                                                                //INSERTA EN TEMPORAL EL ADELANTO DE INTERES
                                                                                                                insertaAdelantoDeInteres = insertaTemporalCaptacion(auxDTOAdeInt, misesion, usuariosDTO.getIdusuario(), montoAdelantoInteres.toString(), "0", "0.00", referencia, true, bigd, BigDecimal.ZERO, fechaTrabajo);
                                                                                                                //System.out.println("se inserto el adelanto de interes en el producto de adelanto de interes");
                                                                                                                if (insertaAdelantoDeInteres != null) {
                                                                                                                    if (insertaAdelantoDeInteres != 0) {
                                                                                                                        /*
                                                                                                                        ----------------------------------------------------------------------
                                                                                                                        insert into abono_adelantado_interes (idorigenp_p, idproducto_p, idauxiliar_p,
                                                                                                                        idorigenp_a, idproducto_a, idauxiliar_a,
                                                                                                                        fecha_mov, interes, iva_interes,
                                                                                                                        interes_ap, iva_interes_ap,
                                                                                                                        fecha, aplicado)
                                                                                                                        values (<idorigenp del prestamo>, <idproducto del prestamo>, <idauxiliar del prestamo>,
                                                                                                                        <idorigenp del 186>, <186 p el idproducto para guardar el adelanto>, <idauxiliar del 186>,
                                                                                                                        <fecha actual en formato timestamp>, <monto del interes adelantado>, <IVA del interes adelantado>,
                                                                                                                        0.0, 0.0,
                                                                                                                        <fecha de cuando se debe aplicar el pago, formato dd/mm/aaaa>, FALSE);
                                                                                                                        -----------------------------------------------------------------------
                                                                                                                         String fechaSigAbono = dtsPrest.get(0);
                                                                                                                        BigDecimal montoTotalAPagar = new BigDecimal(dtsPrest.get(1));
                                                                                                                        BigDecimal montoTotalAPagarMasAdelantoDeInteres = new BigDecimal(dtsPrest.get(2));
                                                                                                                        BigDecimal montoInteres = new BigDecimal(dtsPrest.get(3));
                                                                                                                        BigDecimal ivaMontoInteres = new BigDecimal(dtsPrest.get(4));
                                                                                                                        BigDecimal adelantoInteres = new BigDecimal(dtsPrest.get(5));
                                                                                                                        String fechaSigPagoIntAde = dtsPrest.get(6);
                                                                                                                        ------------------------------------------------------------------------
                                                                                                                         */

                                                                                                                        Date fechaSigPagoIntAdeDate = convertidor.formatoDeFecha.parse(fechaSigPagoIntAde);
                                                                                                                        //System.out.println(fechaSigPagoIntAde);
                                                                                                                        //System.out.println(fechaSigPagoIntAdeDate);
                                                                                                                        abonoAdelantaInteres = new AbonoAdelantadoInteres();
                                                                                                                        abonoAdelantaInteres.setAbonoAdelantadoInteresPK(new AbonoAdelantadoInteresPK(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), auxDTOAdeInt.getAuxiliaresPK().getIdorigenp(), auxDTOAdeInt.getAuxiliaresPK().getIdproducto(), auxDTOAdeInt.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo, fechaSigPagoIntAdeDate));
                                                                                                                        abonoAdelantaInteres.setAplicado(false);
                                                                                                                        abonoAdelantaInteres.setEstatusAp(0);
                                                                                                                        abonoAdelantaInteres.setInteres(montoInteres);
                                                                                                                        abonoAdelantaInteres.setInteresAp(new BigDecimal("0.0"));
                                                                                                                        abonoAdelantaInteres.setIvaInteres(ivaMontoInteres);
                                                                                                                        abonoAdelantaInteres.setIvaInteresAp(new BigDecimal("0.0"));
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        System.out.println("auxiliar nulo no se creo y no existe");
                                                                                                    }
                                                                                                } else {
                                                                                                    eliminaTemporal(misesion);//elimina todos los temporales que se hayan quedado sin procesar de ese socio y con el usuario de banca movil
                                                                                                    rdepo.setResponseCode(CodigoError.CE087.getIdError()); // Monto igual a 0
                                                                                                    //rdepo.setDescription(CodigoError.CE087.getMensaje());
                                                                                                    return rdepo;
                                                                                                }
                                                                                            }
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        
                                                                        // obtenemos informacion acerca del prestamo con la funcion saiAuxiliarPrestamo
                                                                        SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo);
                                                                        System.out.println("Corrio el SaiAuxiliar");
                                                                        // Distribucion del Amount1
                                                                        DistribucionPrestamoDTO distribucionPrestamoDTO = saiFunciones.distribucionMonto(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo, new BigDecimal(Amount1));
                                                                        // ------------------------------------------------------------------------------------
                                                                        // --- ESTA PARTE OBTIENE LOS FOLIOS PARA LOS SEGUROS HIPOTECARIOS Y LAS COMISIONES ---
                                                                        // ------------------------------------------------------------------------------------
                                                                        int seguroInsertado;
                                                                        // Inserta los seguros y las comisiones
                                                                        List<SegurosComisionesDTO> segurosComisionesDTO = saiFunciones.distribucionSegurosComisionCobranza(auxiliaresDTODestino, fechaTrabajo, distribucionPrestamoDTO, saiAuxiliarPrestamoDTO.getDiasVencidos());
                                                                        for (SegurosComisionesDTO seguroComisionDTO : segurosComisionesDTO) {
                                                                            AuxiliaresDTO auxiliarSegHipDTO = new AuxiliaresDTO();
                                                                            auxiliarSegHipDTO.setIdorigen(auxiliaresDTODestino.getIdorigen());
                                                                            auxiliarSegHipDTO.setIdgrupo(auxiliaresDTODestino.getIdgrupo());
                                                                            auxiliarSegHipDTO.setIdsocio(auxiliaresDTODestino.getIdsocio());
                                                                            AuxiliaresPK auxiliarPk = new AuxiliaresPK();
                                                                            auxiliarPk.setIdorigenp(seguroComisionDTO.getIdorigenp());
                                                                            auxiliarPk.setIdproducto(seguroComisionDTO.getIdproducto());
                                                                            auxiliarPk.setIdauxiliar(seguroComisionDTO.getIdauxiliar());
                                                                            auxiliarSegHipDTO.setAuxiliaresPK(auxiliarPk);
                                                                            // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                                                                            seguroInsertado = insertaTemporalCaptacion(auxiliarSegHipDTO, misesion, usuariosDTO.getIdusuario(), seguroComisionDTO.getApagar().toString(), "0", "0.00", " ", true, seguroComisionDTO.getIvaapagar(), bigd, fechaTrabajo);
                                                                            segurosComisionesProcesados = segurosComisionesProcesados + seguroInsertado;
                                                                        }
                                                                        if (segurosComisionesProcesados != segurosComisionesDTO.size()) {
                                                                            rdepo.setResponseCode(CodigoError.CE074.getIdError()); // Error no se inserto el total de seguros y comisiones
                                                                            return rdepo;
                                                                        }
                                                                        // ------------------------------------------------------------------------------------
                                                                        Double[] distribucionMonto = {distribucionPrestamoDTO.getIo().doubleValue(), distribucionPrestamoDTO.getIm().doubleValue(), distribucionPrestamoDTO.getIvaIo().doubleValue(), distribucionPrestamoDTO.getIvaIm().doubleValue(), distribucionPrestamoDTO.getaCapital().doubleValue()};
                                                                   //     System.out.println("REvisa Validacion de montoMayor");
                                                                        // Valida los montos antes de aplicarlos
                                                                        if (validaciones.exiteMontoMayorACero(distribucionMonto)) {
                                                                    //        System.out.println("insertando temporal prestamo");
                                                                            // Inserta el temporal AuxiliaresDTO, sesion, idusuario, DistribucionPrestamoDTO, idcuenta, efectivo, cargoabono, aiva
                                                                            insertaDestino = insertaTemporalPrestamo(auxiliaresDTODestino, misesion, usuariosDTO.getIdusuario(), distribucionPrestamoDTO, saiAuxiliarPrestamoDTO, "0", "0.00", " ", true, bigd, fechaTrabajo);
                                                                    //        System.out.println("insertado");
                                                                        }
                                                                        if (productosDTODestinoTemp.getTipoproducto() == 2 && esPropias == true) {
                                                                   //         System.out.println("Entro a es prestamo y esPropio");
                                                                            // Cuenta las amortizaciones adelantadas para el tema de los boletos de buenos aires
                                                                            totalAmortizacionesCubiertas(fechaTrabajo, misesion);
                                                                            xtra3 = String.format("%1$-15s", "Abono: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getaCapital().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                    + String.format("%1$-15s", "Int ordinario: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                    + String.format("%1$-15s", "Iva int. ord.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIvaIo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                    + String.format("%1$-15s", "Int moratorio: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIm().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                    + String.format("%1$-15s", "Iva int. mor.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getIvaIm().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                                            //+ String.format("%1$-15s", "Seguro hip.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getSegHip().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n")
                                                                            //+ String.format("%1$-15s", "Comision: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getComCob().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n");
                                                                            if (distribucionPrestamoDTO.getSegHip().doubleValue() > 0.0) {

                                                                                xtra3 = xtra3 + String.format("%1$-15s", "Seguro hip.: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getSegHip().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n");
                                                                            }
                                                                            //if (distribucionPrestamoDTO.getComCob().doubleValue() > 0.0) {
                                                                            //xtra3 = xtra3 + String.format("%1$-15s", "Comision: ") + "$" + String.format("%11s", formatea.format(distribucionPrestamoDTO.getComCob().setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n");
                                                                            //}
                                                                            xtra4 = xtra4 + String.format("%1$-20s", "Dias vencidos: ") + " " + String.format("%11s", saiAuxiliarPrestamoDTO.getDiasVencidos()) + "\n"
                                                                                    + String.format("%1$-20s", "Monto vencido: ") + "$" + String.format("%11s", formatea.format(saiAuxiliarPrestamoDTO.getMontoVencido().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                                        }
                                                                        System.out.println("termina destino prestamo");
                                                                  /*  } else {
                                                                        eliminaTemporal(misesion);//elimina todos los temporales que se hayan quedado sin procesar de ese socio y con el usuario de banca movil
                                                                        rdepo.setResponseCode(CodigoError.CE098.getIdError()); // Monto igual a 0
                                                                        //rdepo.setDescription(CodigoError.CE087.getMensaje());
                                                                        return rdepo;
                                                                    }
                                                                    */
                                                                    }
///////////////////////////////////////////////////////////////////////////////                                                                    
                                                                    // --- TERMINA PRESTAMO -----------------------------------------
                                                               //     System.out.println("PRegunta Si Se inserto Origen y destino");
                                                                    // Si los temporales se insertaron correctamente, los proceso y si se procesaron correctamente los elimino
                                                                    if (insertaOrigen == 1 && insertaDestino == 1) {
                                                               //         System.out.println("Entro al if");
                                                                        // ---------------------- TDD ----------------------------------
                                                                        // Activar TDD
                                                                        TablasDTO activarTDDAplicar = valorTablas(idTabla, "activar_tdd");
                                                                        if ("1".equals(activarTDDAplicar.getDato1())) {
                                                                            // Se realiza el retiro en SYC
                                                                            BigDecimal miretiro = new BigDecimal(Amount1).add(montoAdelantoInteres);
                                                                            boolean retiroTdd = tarjetaDeDebito.retiroTdd(rdepo, auxiliaresPKOrigen, Double.parseDouble(miretiro.toString()), usuario.getIdusuario());
                                                                            if (rdepo != null) {

                                                                                if (rdepo.getXtra3() != null) {

                                                                                    if (!rdepo.getXtra3().isEmpty()) {

                                                                                        if (rdepo.getXtra3().equals("1")) {

                                                                                            rdepo.setXtra3("");
                                                                                            if (!retiroTdd) {

                                                                                                return rdepo;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            // Se realiza el deposito en SYC
                                                                            boolean abonoTdd = tarjetaDeDebito.depositoTdd(rdepo, auxiliaresPKDestino, Double.parseDouble(Amount1), usuario.getIdusuario());
                                                                            if (rdepo != null) {

                                                                                if (rdepo.getXtra3() != null) {

                                                                                    if (!rdepo.getXtra3().isEmpty()) {

                                                                                        if (rdepo.getXtra3().equals("1")) {

                                                                                            if (!abonoTdd) {

                                                                                                return rdepo;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            rdepo.setXtra3("");
                                                                        }
                                                                    //    System.out.println("PRocesa insertaOrigen");
                                                                        // -------------------------------------------------------------
                                                                        // ElementosProcesar es la cantidad de insert que se hicieron este mas adelante se compara con la cantidad re registros de temporales procesados
                                                                        Integer elementosProcesar;
                                                                        elementosProcesar = insertaOrigen + insertaDestino + segurosComisionesProcesados + insertaAdelantoDeInteres;
                                                                        // Procesa los movimientos de temporal
                                                                        int elementosProcesados = saiFunciones.procesaTemporal(usuariosDTO.getIdusuario(), misesion, fechaTrabajo, usuariosDTO.getIdorigen(), tipoPoliza, conceptoPoliza, usuariosDTO.getPingreso(), condonar, esResumen);
                                                //    System.out.println("Se insertoOrigen");
                                                                        // Se actualiza el xtra3 con el nuevo saldo del origen (generalmente es un ahorro)
                                                                        auxiliaresDTOOrigen = auxiliaresService.buscarAuxiliar(auxiliaresDTOOrigen.getAuxiliaresPK()); // consulto auxiliares con el opa obtenido en un arreglo
                                                //    System.out.println("Se consulto el opa del origen");
                                                                        // Se actualiza el xtra3 con el nuevo saldo del destino
                                                //                        auxiliaresDTODestino = auxiliaresService.buscarAuxiliar(auxiliaresDTODestino.getAuxiliaresPK()); // consulto auxiliares con el opa obtenido en un arreglo
                                                //    System.out.println("Se consulto el opa del Desitono");
                                                                        // Se mostrara en el xtra3 los datos del origen y si es igual a 2 se mostraran los datos del destino
                                                                        /*if (oriDes == 2) {
                                                                        // Saldo del origen
                                                                        BigDecimal disponibleOrigen = auxiliaresDTOOrigen.getSaldo().subtract(auxiliaresDTOOrigen.getGarantia());
                                                                        xtra3 = String.format("%1$-20s", "Nuevo Saldo Total: ") + "$" + String.format("%11s", formatea.format(auxiliaresDTOOrigen.getSaldo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                + String.format("%1$-20s", "Nuevo Saldo Disponible: ") + "$" + String.format("%11s", formatea.format(disponibleOrigen.setScale(2, BigDecimal.ROUND_HALF_EVEN)));
                                                                        } else if (oriDes == 1) {
                                                                        // Saldo del destino dependiendo si es ahorro o prestamo
                                                                        if (productosDTODestinoTemp.getTipoproducto() == 0) {
                                                                            BigDecimal disponibleDestino = auxiliaresDTODestino.getSaldo().subtract(auxiliaresDTODestino.getGarantia());
                                                                            xtra3 = String.format("%1$-20s", "Comision: ") + "$" + String.format("%11s", formatea.format(new BigDecimal("0.0").setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "\n")
                                                                                    + String.format("%1$-20s", "Nuevo Saldo Total: ") + "$" + String.format("%11s", formatea.format(auxiliaresDTODestino.getSaldo().setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n"
                                                                                    + String.format("%1$-20s", "Nuevo Saldo Disponible: ") + "$" + String.format("%11s", formatea.format(disponibleDestino.setScale(2, BigDecimal.ROUND_HALF_EVEN))) + "\n";
                                                                        } else if (productosDTODestinoTemp.getTipoproducto() == 2) {
                                                                            xtra3 = xtra3 + String.format("%1$-20s", "Nuevo Saldo: ") + "$" + String.format("%11s", formatea.format(auxiliaresDTODestino.getSaldo().setScale(2, BigDecimal.ROUND_HALF_EVEN)));
                                                                        }
                                                                        }*/
                                                                        // Verifica que los elementos totales sean los procesados
                                                //    System.out.println("ElementosProcesar: " + elementosProcesar.toString());
                                                //    System.out.println("ElementosProcesados: " + elementosProcesados);
                                                                        if (elementosProcesar == elementosProcesados) {
                                                                            
                                                //    System.out.println("Entro el ElementosProcesar es igual a ElementosProcesados");
                                                                            if (abonoAdelantaInteres != null) {
                                                               //                 System.out.println("insertando AbonoAdelantoInteres");
                                                                                abonoAdelantadoInteresService.insertaAbonoAdelantoInteres(abonoAdelantaInteres);
                                                             //                   System.out.println("insertado");
                                                                            }
                                                                            if (esPropias == false) {
                                                //    System.out.println("No Es cuenta propia");
                                                                                TablasDTO tabcon = valorTablas(idTabla, "ws_transfer_third_req");
                                                                                rdepo.setConfig(tabcon.getDato2());
                                                                            } else {
                                                //    System.out.println("Es cuenta propia");
                                                                                TablasDTO tabcon = valorTablas(idTabla, "ws_transfer_own_req");
                                                                                rdepo.setConfig(tabcon.getDato2());
                                                                                if (productosDTODestinoTemp.getTipoproducto() == 2) {
                                                /*    System.out.println("Es un prestamooo");
                                                    System.out.println("idorigenp del credito: " + auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp().toString());
                                                    System.out.println("Idproducto del credito: " + auxiliaresDTODestino.getAuxiliaresPK().getIdproducto().toString());
                                                    System.out.println("IDauxiliar del credito: " + auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar());
                                                    System.out.println("Fecha: " + fechaTrabajo.toString());
                                                */                                                                                    
                                                                                    SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO1 = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo);
                                                  //  System.out.println("Se optiene la fecha del siguiente abono");
                                                                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                                                                    Date fps = formatter.parse(saiAuxiliarPrestamoDTO1.getFechaSigAbono());
                                                   // System.out.println("FechaSiguienteAbono: " + saiAuxiliarPrestamoDTO1.getFechaSigAbono());
                                                                                    SaiAuxiliarPrestamoDTO sigSaiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), fps);
                                                   // System.out.println("Datos del proximo abono se ejecuta el saiauxiliar");
                                                                                    //Double montoMaximo = maximoCubrir(auxiliaresDTODestino.getAuxiliaresPK(), fps, auxiliaresDTODestino.getSaldo());
                                                                                    BigDecimal ttlpp = (sigSaiAuxiliarPrestamoDTO.getMontoVencido().add(sigSaiAuxiliarPrestamoDTO.getMontoIoTotal().add(sigSaiAuxiliarPrestamoDTO.getMontoPorVencer().add(sigSaiAuxiliarPrestamoDTO.getMontoImTotal().add(sigSaiAuxiliarPrestamoDTO.getIvaIoTotal().add(sigSaiAuxiliarPrestamoDTO.getIvaImTotal()))))));
                                                                                    xtra4 = xtra4 + String.format("%1$-20s", "Fecha próximo abono: ") + " " + String.format("%11s", saiAuxiliarPrestamoDTO1.getFechaSigAbono() + "\n")
                                                                                            + String.format("%1$-20s", "Monto a pagar: ") + "$" + String.format("%11s", formatea.format(ttlpp.setScale(2, BigDecimal.ROUND_HALF_EVEN)));
                                                    // System.out.println("Xtra4: " + xtra4);                                
                                                                                }

                                                                            }
                                                                            // obtenemos el dato del xtra5
                                                                            TablasDTO tabxtra5 = valorTablas(idTabla, "xtra5");
                                                                            String xtra5 = tabxtra5.getDato2();
                                                                            rdepo.setXtra5(xtra5);
                                                                            // Obtiene el numero de autorizacion
                                                                            // Datos de auxiliares_d del origen despues de aplicarlo
                                                                            AuxiliaresdDTO auxiliaresDTOdatosOrigen = auxiliaresDTOtipo(auxiliaresPKOrigen, 3);  // El 3 es porque es el tipo de poliza - origen
                                                                            // Datos de auxiliares_d del destino despues de aplicarlo
                                                                            AuxiliaresdDTO auxiliaresDTOdatosDestino = auxiliaresDTOtipo(auxiliaresPKDestino, 3); // El 3 es porque es el tipo de poliza - destino
                                                                            nAo = String.valueOf(auxiliaresDTOdatosOrigen.getTransaccion());
                                                                            nAd = String.valueOf(auxiliaresDTOdatosDestino.getTransaccion());
                                                                            // Pasa el resultado al DTO
                                                                            rdepo.setResponseCode(CodigoError.CE000.getIdError());
                                                                            if (esPropias == false) {
                                                                                rdepo.setDescription("Transferencia a Cuenta de Tercero");
                                                                            } else {
                                                                                rdepo.setDescription("Transferencia a Cuenta Propia");
                                                                            }
                                                  //  System.out.println("Descripcion rdepo: " + rdepo.getDescription());
                                                                            rdepo.setIdAcctO(IdAcctO);
                                                                            rdepo.setIdAcctD(IdAcctD);
                                                                            rdepo.setIdTeller(IdTeller);
                                                                            BigDecimal mimonto = BigDecimal.ZERO;
                                                                            mimonto = mimonto.add(new BigDecimal(Amount1));
                                                                            if (montoAdelantoInteres.compareTo(BigDecimal.ZERO) == 1) {
                                                                                mimonto = mimonto.add(montoAdelantoInteres);
                                                 //   System.out.println("Adelanto de interes: " + montoAdelantoInteres.toString());
                                                                            }
                                                                            rdepo.setAmount1(mimonto.toString());
                                                                            rdepo.setDateTime(auxiliaresDTOdatosDestino.getAuxiliaresDPK().getFecha());
                                                                            rdepo.setAutorizationNumber(nAo + " - " + nAd);
                                                                            rdepo.setXtra3(xtra3);
                                                                            rdepo.setXtra4(xtra4);
                                                /*    System.out.println("Amount1: " + rdepo.getAmount1());
                                                    System.out.println("Xtra3: " + rdepo.getXtra3());
                                                    System.out.println("Xtra4 :" + rdepo.getXtra4());
                                                */
                                                                            // Elimino temporal
                                                                            eliminaTemporal(misesion);
                                                                             
                                                                            System.out.println(" aaunnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");

                                                                        }

                                                                    } else {
                                                                        rdepo.setResponseCode(CodigoError.CE075.getIdError()); // Error al validar que se insertaron los temporales
                                                                        //rdepo.setDescription(CodigoError.CE075.getMensaje());
                                                                    }
                                                                } else {
                                                                    rdepo.setResponseCode(CodigoError.CE085.getIdError()); // No puede retirar de un prestamo.
                                                                    //rdepo.setDescription(CodigoError.CE085.getMensaje());
                                                                }
                                                                // modificado por fredy 12/03/2020
                                                                //elimina todos los temporales que se hayan quedado sin procesar de ese socio y con el usuario de banca movil
                                                                eliminaTemporal(misesion);
                                                            } else {
                                                                // Valida el monto maximo que puede transferir en un menor (Si no es menor continua)
                                                            }
                                                        } else {
                                                            rdepo.setResponseCode(CodigoError.CE088.getIdError()); // Fondos insuficientes.
                                                            //rdepo.setDescription(CodigoError.CE088.getMensaje());
                                                        }
                                                    } else {
                                                        rdepo.setResponseCode(CodigoError.CE080.getIdError()); // El usuario no existe.
                                                        //rdepo.setDescription(CodigoError.CE080.getMensaje());
                                                    }
                                                } else {//grupo no permitido
                                                    /**
                                                     * se bloqueo al socio por
                                                     * no estar en un grupo de
                                                     * los que estan
                                                     * configurados
                                                     * ******************************************************************************************************************
                                                     */
                                                    rdepo.setResponseCode(CodigoError.CE073.getIdError());
                                                    //rdepo.setDescription(CodigoError.CE073.getMensaje());
                                                    /**
                                                     * ******************************************************************************************************************
                                                     */
                                                }
                                            } else {
                                                rdepo.setResponseCode(CodigoError.CE083.getIdError()); // Socio bloqueado de banca movil
                                                //rdepo.setDescription(CodigoError.CE083.getMensaje());
                                            }
                                        } else {
                                            rdepo.setResponseCode(CodigoError.CE087.getIdError()); // Monto igual a 0
                                            //rdepo.setDescription(CodigoError.CE087.getMensaje());
                                        }
                                    } else {
                                        rdepo.setResponseCode(CodigoError.CE099.getIdError()); // Producto origen no valido para transferencia
                                        //rdepo.setDescription(CodigoError.CE099.getMensaje());
                                    }
                                } else {
                                    rdepo.setResponseCode(CodigoError.CE099.getIdError()); // Producto destino no valido para transferencia
                                    //rdepo.setDescription(CodigoError.CE099.getMensaje());
                                }
                            } else {
                                rdepo.setResponseCode(CodigoError.CE092.getIdError()); // Folio incorrecto.
                                //rdepo.setDescription(CodigoError.CE092.getMensaje());
                            }
                        } else {
                            rdepo.setResponseCode(CodigoError.CE094.getIdError()); // El folio de origen no puede ser igual al de destino
                            //rdepo.setDescription(CodigoError.CE094.getMensaje());
                        }
                    } else {
                        rdepo.setResponseCode(CodigoError.CE097.getIdError()); // Usuario no activo.
                        //rdepo.setDescription(CodigoError.CE097.getMensaje());
                    }
                } else {
                    rdepo.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada.
                    //rdepo.setDescription(CodigoError.CE095.getMensaje());
                }
            } else {
                rdepo.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado.
                //rdepo.setDescription(CodigoError.CE050.getMensaje());
            }
        } catch (Exception | Error e) {
            System.out.println("Error al realizar Transferencia. " + e);
        }
        // Retorna el resultado
        
        return rdepo;
    }

    private AuxiliaresDTO auxiliaresSebuscaAuxiliarPorOGSP(int idorigen, int idgrupo, int idsocio, int idproducto) {
        return auxiliaresService.buscaAuxiliarPorOGS(idorigen, idgrupo, idsocio, idproducto);
    }

    private AuxiliaresDTO aperturaAhorro(int idorigen, int idgrupo, int idsocio, int idorigenp, int idproducto, Date fechaTrabajo, int idusuario) {
        AuxiliaresPK auxiliarpk = saiFunciones.saiAhorroCreaApertura(idorigen, idgrupo, idsocio, idorigenp, idproducto, fechaTrabajo, idusuario);
        if (auxiliarpk != null) {
            if (auxiliarpk.getIdauxiliar() != null) {
                //System.out.println("apertura diferente a null");
                List<AuxiliaresDTO> auxdtos = auxiliaresService.buscaAuxiliarPorOGS(idorigen, idgrupo, idsocio);
                for (int i = 0; i < auxdtos.size(); i++) {
                    AuxiliaresDTO auxdto = auxdtos.get(i);
                    if (auxdto.getAuxiliaresPK().getIdauxiliar().equals(auxiliarpk.getIdauxiliar())) {
                        //System.out.println("Idorigenp " + auxdto.getAuxiliaresPK().getIdorigenp());
                        //System.out.println("Idproducto " + auxdto.getAuxiliaresPK().getIdproducto());
                        //System.out.println("Idauxiliar " + auxdto.getAuxiliaresPK().getIdauxiliar());
                        //System.out.println("Idorigen " + auxdto.getIdorigen());
                        //System.out.println("Idgrupo " + auxdto.getIdgrupo());
                        //System.out.println("Idsocio " + auxdto.getIdsocio());
                        //System.out.println("Saldo " + auxdto.getSaldo());
                        if (auxdto.getAuxiliaresPK().getIdauxiliar() != null) {
                            return auxdto;
                        }
                    }
                }
            }
        }
        return null;
    }

    // LISTA CON LOS MOVIMIENTOS DE UN FOLIO
    @Override
    public List<MovimientoCuentaDTO> buscaMovimientosCuenta(String IdAcct, String IdTeller, String SessionId, String User, String Password, int tipoEntrada, String idTabla) {
        List<MovimientoCuentaDTO> ListMovimientoCuenta = new ArrayList<>(0);
        MovimientoCuentaDTO movimientoCuentaDTO = new MovimientoCuentaDTO();
        try {
            // Valida el usuario y contraseña del metodo
            if (validaciones.validaUsuarioMetodo(idTabla, User, Password)) {
                // Valida el estatus del origen
                if (estatusOrigen(idusuario(IdTeller, idTabla))) {
                    UsuariosDTO usuario = datoUsuario(IdTeller, idTabla);
                    // Valida que el usuario este activo
                    if (usuario.isActivo()) {
                        //convierto el opa de 32 caracteres en arreglo con los 3 id's
                        int[] opa = convertidor.getOPA(IdAcct); // convierto el opa de 32 digitos a opa separado dentro de un arreglo
                        AuxiliaresPK auxiliaresPK = new AuxiliaresPK(opa[0], opa[1], opa[2]);
                        AuxiliaresDTO auxiliaresDTO = auxiliaresService.buscarAuxiliar(auxiliaresPK); // consulto auxiliares con el opa
                        String IdClient = convertidor.getOGS32(auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdorigen(), auxiliaresDTO.getIdsocio());
                        // Esto es para bloquear socios en una lista negra
                        if (!socioBloqueado(IdClient, usuario.getIdusuario(), idTabla)) {
                            // Se valida el folio de origen y destino
                            if (validaciones.validaOPA(IdAcct.trim()) == true && validaciones.validaOPA(IdAcct.trim()) == true) {
                                // obtengo el maximo de movimientos que podre enviar al cliente
                                TablasDTO TablasDTOmovimientos = valorTablas(idTabla, "movimientos_de_cuentas");
                                // obtengo la configuracion de los datos que retorno al cliente
                                TablasDTO TablasDTOconfiguracion = valorTablas(idTabla, "ws_get_account_txs_req");
                                // En caso de que esten correctas las configuraciones en tablas, continua
                                if (TablasDTOmovimientos != null && TablasDTOconfiguracion != null) {
                                    if (opa[0] > 0) {
                                        //busco todos los movimientos de ese opa y aparte envio como parametro el maximo de movimientos que quiero que me retorne
                                        List<AuxiliaresdDTO> auxiliaresdDTOs = auxiliaresDService.buscaListaDeAuxiliaresD(opa[0], opa[1], opa[2], Integer.parseInt(TablasDTOmovimientos.getDato1().trim()));
                                        ProductosDTO pro = productosService.buscarProducto(opa[1]);
                                        // Si la lista de movimientos no esta vacia, continua.
                                        if (!auxiliaresdDTOs.isEmpty()) {
                                            String acctTypeProducto = getAcctTypeProducto(opa[1], idTabla);
                                            if (acctTypeProducto != null) { // si getAcctTypeProducto retorna diferente de nulo, continua
                                                for (AuxiliaresdDTO auxiliaresdDTO : auxiliaresdDTOs) {
                                                    CatTxTypeDTO idTxTypre = getIdTxTypre(auxiliaresdDTO.getCargoabono(), pro.getTipoproducto(), auxiliaresdDTO.getTipomov());
                                                    BigDecimal total = new BigDecimal(0.00);
                                                    total = total.add(auxiliaresdDTO.getMonto().add(auxiliaresdDTO.getMontoio().add(auxiliaresdDTO.getMontoim().add(auxiliaresdDTO.getMontoiva().add(auxiliaresdDTO.getMontoivaim())))));
                                                    movimientoCuentaDTO = new MovimientoCuentaDTO();
                                                    movimientoCuentaDTO.setResponseCode("000");
                                                    movimientoCuentaDTO.setIdAcct(IdAcct);
                                                    movimientoCuentaDTO.setIdAcctType(acctTypeProducto);
                                                    movimientoCuentaDTO.setIdTxType(idTxTypre.getIdTxType()); // el numero que esta en la tabla CatTxType
                                                    movimientoCuentaDTO.setTxDate(auxiliaresdDTO.getAuxiliaresDPK().getFecha());
                                                    movimientoCuentaDTO.setTxAmount(total);
                                                    movimientoCuentaDTO.setDescription(idTxTypre.getDescription());
                                                    movimientoCuentaDTO.setFee(new BigDecimal(0.00));
                                                    movimientoCuentaDTO.setDescriptionFee("No existe comision");
                                                    movimientoCuentaDTO.setConfig(TablasDTOconfiguracion.getDato2());
                                                    ListMovimientoCuenta.add(movimientoCuentaDTO);
                                                }
                                            }
                                        } else {
                                            movimientoCuentaDTO.setResponseCode(CodigoError.CE089.getIdError()); // No existe el folio
                                            //movimientoCuentaDTO.setDescription(CodigoError.CE089.getMensaje());
                                            ListMovimientoCuenta.add(movimientoCuentaDTO);
                                        }
                                    } else {
                                        movimientoCuentaDTO.setResponseCode(CodigoError.CE092.getIdError()); // Error en el folio
                                        //movimientoCuentaDTO.setDescription(CodigoError.CE092.getMensaje());
                                        ListMovimientoCuenta.add(movimientoCuentaDTO);
                                    }
                                } else {
                                    movimientoCuentaDTO.setResponseCode(CodigoError.CE093.getIdError()); // Las tablas no estan confuguradas en tablas - movimientos_de_cuentas y ws_get_account_txs_req
                                    //movimientoCuentaDTO.setDescription(CodigoError.CE093.getMensaje());
                                    ListMovimientoCuenta.add(movimientoCuentaDTO);
                                }
                            } else {
                                movimientoCuentaDTO.setResponseCode(CodigoError.CE092.getIdError()); // Folio incorrecto.
                                //movimientoCuentaDTO.setDescription(CodigoError.CE092.getMensaje());
                                ListMovimientoCuenta.add(movimientoCuentaDTO);
                            }
                        } else {
                            movimientoCuentaDTO.setResponseCode(CodigoError.CE083.getIdError()); // Socio bloqueado de banca movil
                            //movimientoCuentaDTO.setDescription(CodigoError.CE083.getMensaje());
                            ListMovimientoCuenta.add(movimientoCuentaDTO);
                        }
                    } else {
                        movimientoCuentaDTO.setResponseCode(CodigoError.CE097.getIdError()); // Usuario no activo.
                        //movimientoCuentaDTO.setDescription(CodigoError.CE097.getMensaje());
                        ListMovimientoCuenta.add(movimientoCuentaDTO);
                    }
                } else {
                    movimientoCuentaDTO.setResponseCode(CodigoError.CE095.getIdError()); // Sucursal cerrada.
                    //movimientoCuentaDTO.setDescription(CodigoError.CE095.getMensaje());
                    ListMovimientoCuenta.add(movimientoCuentaDTO);
                }
            } else {
                movimientoCuentaDTO.setResponseCode(CodigoError.CE050.getIdError()); // Uso no autorizado.
                //movimientoCuentaDTO.setDescription(CodigoError.CE050.getMensaje());
                ListMovimientoCuenta.add(movimientoCuentaDTO);
            }
        } catch (Exception e) {
            movimientoCuentaDTO.setResponseCode(CodigoError.CE096.getIdError()); // Error desconocido
            //movimientoCuentaDTO.setDescription(CodigoError.CE096.getMensaje());
            ListMovimientoCuenta.add(movimientoCuentaDTO);
            System.out.println("Error en buscarCuentasUsuario de SiscoopService: " + e.getMessage());
        }
        return ListMovimientoCuenta;
    }

    // REVISA EL ESTATUS DEL ORIGEN
    @Override
    public boolean estatusOrigen(int idorigen) {
        try {
            return origenesService.buscarOrigen(idorigen).isEstatus();
        } catch (Exception e) {
            System.out.println("Error en estatusOrigen de SiscoopService: " + e.getMessage());
        }
        return false;
    }

    // GENERA LA LISTA PARA LA TRANSFERENCIA DE CUENTAS DE TERCEROS.
    @Override
    public List<CuentasTercerosDTO> buscaCuentasTerceros(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK, String idTabla) {
        TablasPK tpk = new TablasPK(idTabla, "transferencia_cuenta_tercero");
        TablasDTO tdto = tablasService.buscaTabla(tpk);
        String[] productoDestino = tdto.getDato2().split(",");
        // destinos
        List<CuentasTercerosDTO> cuentas = cuentasTercerosService.buscaCuentasTercerosPorOGS(catalogoCuentasTercerosPK);
        List<CuentasTercerosDTO> CuentasTerceros = new ArrayList<>(0);
        for (CuentasTercerosDTO cuenta : cuentas) {
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdauxiliar());
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdgrupo());
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdorigen());
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdorigenp());
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdproducto());
            //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdsocio());
            for (String producto : productoDestino) {
                if (cuenta.getCatalogoCuentasTercerosPK().getIdproducto() == Integer.parseInt(producto.trim())) {
                    //System.out.println(cuenta.getCatalogoCuentasTercerosPK().getIdorigenp() + " " + cuenta.getCatalogoCuentasTercerosPK().getIdproducto() + " " + cuenta.getCatalogoCuentasTercerosPK().getIdauxiliar());
                    CuentasTerceros.add(cuenta);
                }
            }

        }

        return CuentasTerceros;
    }

    // OBTIENE LA LISTA DE DTO CON LAS CUENTAS DE ORIGEN Y DESTINO PARA LA TRANSFERENCIA.
    @Override
    public ProductosTrasODDTO productosOrigenDestino(String IdClient, String IdTeller, String tipoTransferencia, String idTabla) {
        ProductosTrasODDTO ptod;
        try {
            if (validaciones.validaOGS(IdClient) == true) {
                List<String[]> los = new ArrayList<>(0);
                List<String[]> lds = new ArrayList<>(0);
                int[] ogs = convertidor.getOGS(IdClient); // obtengo el ogs separado para traer todos los auxiliares del socio
                List<AuxiliaresDTO> aux = auxiliaresService.buscaAuxiliarPorOGS(ogs[0], ogs[1], ogs[2]);
                // Obtengo todos los productos origen de dato1 y destino de dato2
                TablasDTO tablasDTO = valorTablas(idTabla, tipoTransferencia);
                String[] origenValido = tablasDTO.getDato1().split(",");
                String[] destinoValido = tablasDTO.getDato2().split(",");
                // Separare los auxiliares que coincidan con los productos de origen y destino
                for (AuxiliaresDTO aux1 : aux) {
                    if (aux1.getEstatus() == 2) {
                        for (String origen : origenValido) {
                            if (aux1.getAuxiliaresPK().getIdproducto() == Integer.parseInt(origen.trim())) {
                                String[] opasOS = new String[]{convertidor.getOPA32(aux1.getAuxiliaresPK().getIdorigenp(), aux1.getAuxiliaresPK().getIdproducto(), aux1.getAuxiliaresPK().getIdauxiliar()), productosService.buscarProducto(Integer.parseInt(origen.trim())).getNombre()};
                                // SALDO TDD -- excluir si no consulta
                                if (consultarTDD(aux1, IdTeller, idTabla).getCode() != 0) {
                                    los.add(opasOS);
                                }
                                break;
                            }
                        }
                        for (String destino : destinoValido) {
                            if (aux1.getAuxiliaresPK().getIdproducto() == Integer.parseInt(destino.trim())) {
                                String[] opasDS = new String[]{convertidor.getOPA32(aux1.getAuxiliaresPK().getIdorigenp(), aux1.getAuxiliaresPK().getIdproducto(), aux1.getAuxiliaresPK().getIdauxiliar()), productosService.buscarProducto(Integer.parseInt(destino.trim())).getNombre()};
                                // SALDO TDD -- excluir si no consulta
                                if (consultarTDD(aux1, IdTeller, idTabla).getCode() != 0) {
                                    lds.add(opasDS);
                                }
                                break;
                            }
                        }
                    }
                }
                ptod = new ProductosTrasODDTO(los, lds, "000");
            } else {
                ptod = new ProductosTrasODDTO(null, null, "091");
            }
        } catch (Exception e) {
            ptod = new ProductosTrasODDTO(null, null, "096");
            System.out.println("Error en productosOrigenDestino de SiscoopService: " + e.getMessage());
        }
        return ptod;
    }

    // RETORNA LA DISTRIBUCION DEL SAI_AUXILIAR DE UN PRESTAMO.
    @Override
    public SaiAuxiliarPrestamoDTO ejecutaSaiAuxiliarPrestamo(int idorigenp, int idproducto, int idauxiliar) {
        return saiFunciones.saiAuxiliarPrestamo(idorigenp, idproducto, idauxiliar, saiFunciones.saiFechaTrabajo(idorigenp));
    }

    // RETORNA LA DISTRIBUCION DEL SAI_AUXILIAR DE UN AHORRO.
    @Override
    public SaiAuxiliarAhorroDTO ejecutaSaiAuxiliarAhorro(int idorigenp, int idproducto, int idauxiliar) {
        return saiFunciones.saiAuxiliarAhorro(idorigenp, idproducto, idauxiliar, saiFunciones.saiFechaTrabajo(idorigenp));
    }

    // RETORNA AUXILIARES
    @Override
    public AuxiliaresDTO buscaAuxiliar(AuxiliaresPK auxpk) {
        return auxiliaresService.buscarAuxiliar(auxpk);
    }

    // ESTE METODO VALIDA QUE LA HORA ACTUAL NO PASE DE LA HORA ESTABLECIDA PARA HACER EL CORTE,
    // RETORNA FALSE SI YA PASO DE LA HORA Y TRUE SI ESTA A TIEPO. BLOQUEA POR UNA HORA
    @Override
    public boolean ServicioActivo(String idTabla) {
        try {
            LocalTime h0 = LocalTime.parse("00:00");
            LocalTime h24 = LocalTime.parse("23:59");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            // Saco la hora de la base de datos
            TablasPK tablasPK = new TablasPK(idTabla, "hora_cierre");
            TablasDTO tabActivo = tablasService.buscaTabla(tablasPK);
            if (tabActivo.getDato1() != null) {
                int horaBloqueo = Integer.valueOf(tabActivo.getDato3());
                String hora1 = tabActivo.getDato1();

                // Convierte el string de la hora en date
                Calendar cal = Calendar.getInstance();
                Date date1 = formatter.parse(tabActivo.getDato1());
                // Le suma dato3 las hora a la hora que se asigno en la base
                cal.setTime(date1);
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + (horaBloqueo * 60));

                // Obtenemos en string las horas de inicio, fin y la de la base de datos
                String hora2 = formatter.format(cal.getTime());
                String horaBase = formatter.format(saiFunciones.saiFechaDB("24"));
                // Se convierten las horas de string a localtime (hh:mm)
                DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter();
                LocalTime localTime1 = LocalTime.parse(hora1, parseFormat);
                LocalTime localTime2 = LocalTime.parse(hora2, parseFormat);
                LocalTime localTimeBase = LocalTime.parse(horaBase, parseFormat);
                //prometeo: si mi hora esta despues de la hora de cierre y antes de las 12 de la noche ó 
                //si mi hora esta despues de las 0 horas y antes de la hora de inicio de actividades
                //entonces retorna false( no puede hacer peticiones ).
                //if ((localTimeBase.isAfter(00:00) && localTimeBase.isBefore(08:00)) || (localTimeBase.isAfter(22:00) && localTimeBase.isBefore(23:59))) {
                if ((localTimeBase.isAfter(h0) && localTimeBase.isBefore(localTime2)) || (localTimeBase.isAfter(localTime1) && localTimeBase.isBefore(h24))) {
                    return false;
                }
            } else {
                System.out.println("Error en fechas en la tabla: " + idTabla + "- idelemento: hora_cierre. ");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Error en ServicioActivo de SiscoopService. " + e);
            return false;
        }
        return true;
    }

    // OBTIENE LOS DATOS DEL USUARIO
    @Override
    public UsuariosDTO datoUsuario(String idTeller, String idTabla) {
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        try {
            TablasPK tpkdc = new TablasPK(idTabla, idTeller);
            // Consultamos tablas para obtener el usuario que corresponde a esa caja
            TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
            usuariosDTO = usuariosService.buscaUsuario(Integer.parseInt(tdtodc.getDato1().trim())); // consultamos usuarios
        } catch (Exception e) {
            System.out.println("Error en sacar datos del usuario." + e.getMessage());
        }
        return usuariosDTO;
    }

    // OBTIENE EL IDUSUARIO EN BASE AL IDTELLER
    @Override
    public int idusuario(String IdTeller, String idTabla) {
        Integer usuario = datoUsuario(IdTeller, idTabla).getIdorigen();
        if (usuario != null) {
            return usuario;
        } else {
            return 0;
        }
    }

    // VALIDA QUE EL PRODUCTO AL CUAL SE LE VA A RETIRAR ESTE EN LA LISTA DE LOS VALIDOS PARA RETIRAR
    private boolean productoValidoParaMovieminto(boolean esPropia, int cargoAbono, String IdAcct, String idTabla) {
        String idelemento;
        if (esPropia) {
            idelemento = "transferencia_cuenta";
        } else {
            idelemento = "transferencia_cuenta_tercero";
        }
        try {
            TablasDTO tablasDTO = valorTablas(idTabla, idelemento);
            int[] opa = convertidor.getOPA(IdAcct); // convierto el opa de 32 digitos a opa separado dentro de un arreglo
            if (cargoAbono == 0) {
                String[] listaOrigen = tablasDTO.getDato1().split(",");
                for (String origen : listaOrigen) {
                    if (Integer.parseInt(origen.trim()) == opa[1]) {
                        ProductosDTO productosDTO = productosService.buscarProducto(opa[1]);
                        if (productosDTO.getTipoproducto() != 2) {
                            return true;
                        }
                    }
                }
            } else {
                String[] listaDestino = tablasDTO.getDato2().split(",");
                for (String destino : listaDestino) {
                    if (Integer.parseInt(destino.trim()) == opa[1]) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error esta mal configurada la tabla: " + idTabla + " del elemento: " + idelemento + "\n" + e.getMessage());
        }
        return false;
    }

    // VALIDA SI EL MOVIMIENTO YA FUE PROCESADO SI ES ASI YA NO LO APLICA
    @Override
    public boolean validaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String Date, String Amount1, String Amount2) {
        try {
            // Busca movimiento en valida movimiento y si existe no se procesa el deposito
            ValidaMovimientoCajeroReceptorDTO movimientoCajeroReceptor = validaMovimientoCajeroReceptorService.buscaMovimientoCajeroReceptor(IdTeller, IdAcct, idOperation);
            if (movimientoCajeroReceptor.getValidaMovimientoCajeroReceptorPK() == null) {
                // Inserta el movimiento en la tabla de valida movimiento para posteriormente si se envia de nuevo no lo procese
                validaMovimientoCajeroReceptorService.insertaMovimientoCajeroReceptor(IdTeller, IdAcct, TxType, idOperation, Date, Amount1, Amount2);
            }
            return movimientoCajeroReceptor.isEstatus();
        } catch (Exception e) {
            return false;
        }
    }

    // ACTUALIZA EL MOVIMIENTO SI YA SE APLICO
    @Override
    public int actualizaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String Date, String Amount1, String Amount2, boolean estatus) {
       
        int actualizoMovimiento = 0;
        System.out.println("Idteller:"+IdTeller+"\n idac:"+IdAcct+"\n idoperation:"+idOperation);
        EntityManager em=AbstractFacade.getEntityManager();
        EntityTransaction et=em.getTransaction();
        
        /// Busco en la tabla valida_movimineto_cajero_receptor que no este en proceso una operacion con los mismos datos
        String sql1="SELECT count(*) FROM valida_movimiento_cajero_receptor WHERE idteller='"+IdTeller+"' AND idacct='"+IdAcct+"' AND idoperation="+idOperation;
        Query queryy=em.createNativeQuery(sql1);
        int contOp=Integer.parseInt(String.valueOf(queryy.getSingleResult()));
        //si existen operaciones con los mismos datos los elimino y actualizo los registros
        if(contOp>0){
        et.begin();
        String sql="DELETE FROM valida_movimiento_cajero_receptor WHERE idteller='"+IdTeller+"' AND idacct='"+IdAcct+"' AND idoperation="+idOperation;
        int rowafected=em.createNativeQuery(sql).executeUpdate();
        System.out.println("Registros Eliminados:"+rowafected);
        et.commit();        
        
        EntityTransaction et1=em.getTransaction();
        et1.begin();
        Query sqlly=em.createNativeQuery("INSERT INTO valida_movimiento_cajero_receptor VALUES(?,?,?,?,?,?,?,?)");
         
         try {
                sqlly.setParameter(1,IdTeller);
                sqlly.setParameter(2,IdAcct);
                sqlly.setParameter(3,TxType);
                sqlly.setParameter(4,idOperation);
                sqlly.setParameter(5,Date);
                sqlly.setParameter(6,Amount1);
                sqlly.setParameter(7,Amount2);
                sqlly.setParameter(8,estatus);
                sqlly.executeUpdate();
                et1.commit();
            } catch (Exception e) {
                System.out.println("Error de insercion de registros en valida_movimiento_cajero_receptor:"+e.getMessage());
            }  
         
         
        //actualizoMovimiento = validaMovimientoCajeroReceptorService.actualizaMovimientoCajeroReceptor(IdTeller, IdAcct, TxType, idOperation, Date, Amount1, Amount2, estatus);
        
        //Si no existen datos en temporal simplemente guardo los registros
        }else{
            EntityTransaction et1=em.getTransaction();
            et1.begin();
            Query sqlly=em.createNativeQuery("INSERT INTO valida_movimiento_cajero_receptor VALUES(?,?,?,?,?,?,?,?)");
            try {
                sqlly.setParameter(1,IdTeller);
                sqlly.setParameter(2,IdAcct);
                sqlly.setParameter(3,TxType);
                sqlly.setParameter(4,idOperation);
                sqlly.setParameter(5,Date);
                sqlly.setParameter(6,Amount1);
                sqlly.setParameter(7,Amount2);
                sqlly.setParameter(8,estatus);
                sqlly.executeUpdate();
                et1.commit();
            } catch (Exception e) {
                System.out.println("Error al insertar registros en valida_movimiento_cajero_receptor:"+e.getMessage());
            }          
           }
        return actualizoMovimiento;
    }

    
    
    

    // VALIDA SI EXISTE EL SOCIO EN UNA LISTA SOPAR
    @Override
    public boolean existeEnSoparOGS(String IdClient, int idusuario, String tipo) {
        boolean bloqueado = false;
        try {
            int[] ogs = convertidor.getOGS(IdClient);
            List<SoparDTO> sopars = soparService.buscaSoparTipo(tipo);
            for (SoparDTO sopar : sopars) {
                if (ogs[0] == sopar.getSoparPK().getIdorigen() && ogs[1] == sopar.getSoparPK().getIdgrupo() && ogs[2] == sopar.getSoparPK().getIdsocio()) {
                    bloqueado = true;
                }
                if (bloqueado) {
                    break;
                }
            }
        } catch (Exception e) {

        }
        return bloqueado;
    }

    // VALIDA SI EL SOCIO CUENTA CON EL PRODUCTO ASIGNADO
    private boolean existeSocioProducto(int idorigen, int idgrupo, int idsocio, String idTabla) {
        try {
            TablasDTO tdtodc = valorTablas(idTabla, "acceso_aplicacion");
            // Si tiene un 0 en la tabla entonces tiene acceso total sin buscar porducto relacionado a la banca
            if (Integer.parseInt(tdtodc.getDato1().trim()) == 0) {
                return true;
            } else {
                return auxiliaresService.existeAuxiliarPorOGS(idorigen, idgrupo, idsocio, Integer.parseInt(tdtodc.getDato1().trim()));
            }
        } catch (Exception e) {
            System.out.println("Error: No esta configurado el producto en la tabla " + idTabla + " el elemento acceso_aplicacion. " + e.getMessage());
            return false;
        }
    }

    // VALIDA SI EXISTE EL SOCIO EN UNA LISTA SOPAR POR EL OPA
    @Override
    public boolean existeEnSoparOPA(String IdAcct, int idusuario, String tipo) {
        int[] opa = convertidor.getOPA(IdAcct);
        AuxiliaresPK auxiliaresPK = new AuxiliaresPK(opa[0], opa[1], opa[2]);
        AuxiliaresDTO auxiliaresDTO = auxiliaresService.buscarAuxiliar(auxiliaresPK);
        if (auxiliaresDTO != null) {
            List<SoparDTO> sopars = soparService.buscaSopar(idusuario, tipo);
            for (SoparDTO sopar : sopars) {
                if (auxiliaresDTO.getIdorigen() == sopar.getSoparPK().getIdorigen() && auxiliaresDTO.getIdgrupo() == sopar.getSoparPK().getIdgrupo() && auxiliaresDTO.getIdsocio() == sopar.getSoparPK().getIdsocio()) {
                    return true;
                }
            }
        }
        return false;
    }

    // SALDO DE AUXILIARES
    @Override
    public SaldoAuxiliarDTO saldoAuxiliarDTO(int idorigenp, int idproducto, int idauxiliar, String idTeller, String idTabla) {
        ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
        SaldoAuxiliarDTO sadto = new SaldoAuxiliarDTO();
        AuxiliaresPK alspk = new AuxiliaresPK(idorigenp, idproducto, idauxiliar);
        // Consulto el usuario con el que creare la poliza
        TablasPK tpkdc = new TablasPK(idTabla, idTeller);
        TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
        UsuariosDTO usu = usuariosService.buscaUsuario(Integer.parseInt(tdtodc.getDato1().trim()));
        // Si no existe el usuario no hace nada (se queda con error 096).
        if (usu != null) {
            AuxiliaresDTO auxiliar = buscaAuxiliar(alspk);
            sadto = new SaldoAuxiliarDTO(auxiliar.getSaldo(), (auxiliar.getSaldo().subtract(auxiliar.getGarantia())));
            // Activar TDD
            TablasDTO activarTDDAplicar = valorTablas(idTabla, "activar_tdd");
            if ("1".equals(activarTDDAplicar.getDato1())) {
                // Obtenemos el saldo disponible de SYC para el origen y el destino, si manda error termina el proceso
                tarjetaDeDebito.validaSaldoTDD(rdepo, alspk, alspk, usu.getIdusuario());
                // Si el xtra2 no manda un 1 entonces no es porducto de TDD
                if (rdepo.getXtra2() != null) {
                    if (rdepo.getXtra2().equals("1")) {
                        // Si el Xtra1 tiene dato es porque consulto correctamente el saldo en SYC
                        if (rdepo.getXtra1().length() > 0) {
                            sadto.setSaldo(new BigDecimal(rdepo.getXtra1()));
                            sadto.setSaldoDisponible(new BigDecimal(rdepo.getXtra1()));
                        }
                    }
                }
            }
        }
        return sadto;
    }

    // VALIDA QUE EXISTA CONEXION CON LA TARJETA TDD
    private BalanceQueryResponseDto consultarTDD(AuxiliaresDTO auxiliaresDTO, String IdTeller, String idTabla) {
        BalanceQueryResponseDto saldoTdd = new BalanceQueryResponseDto();
        // SALDO TDD -- excluir si no consulta
        if (tarjetaDeDebito.productoTddwebservice(auxiliaresDTO.getAuxiliaresPK().getIdproducto())) {
            // ---------------------- TDD --------------------------
            // Activar TDD
            TablasDTO activarTDD = valorTablas(idTabla, "activar_tdd");
            if ("1".equals(activarTDD.getDato1())) {
                // obtiene los productos que corresponden a los productos generales 10 y 20
                TablasPK tpkdc = new TablasPK(idTabla, IdTeller);
                // Consultamos tablas para obtener el usuario que corresponde a esa caja
                TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
                UsuariosDTO usu = usuariosService.buscaUsuario(Integer.parseInt(tdtodc.getDato1().trim())); // consultamos usuarios
                SaldoTddPK saldoTddPK = new SaldoTddPK(auxiliaresDTO.getAuxiliaresPK().getIdorigenp(), auxiliaresDTO.getAuxiliaresPK().getIdproducto(), auxiliaresDTO.getAuxiliaresPK().getIdauxiliar());
                saldoTdd = tarjetaDeDebito.saldoTDD(saldoTddPK, usu.getIdusuario());
                return saldoTdd;
            } else {
                saldoTdd.setCode(0);
                return saldoTdd;
            }
        }
        saldoTdd.setCode(-1);
        return saldoTdd;
    }

    // INSERTA EN TEMPORAL EL MOVIMIENTO DE CAPTACION
    private int insertaTemporalCaptacion(AuxiliaresDTO auxDto, String sesion, int idusuario, String aCapital, String Idcuenta, String efectivo, String referencia, boolean cargoabono, BigDecimal aiva, BigDecimal ioCalc, Date fechaTrabajo) {
        try {
            return temporalService.insertaTemporal(sesion, // Sesion
                    auxDto.getAuxiliaresPK().getIdorigenp(), // Idorigenp
                    auxDto.getAuxiliaresPK().getIdproducto(), // Idproducto
                    auxDto.getAuxiliaresPK().getIdauxiliar(), // Idauxiliar
                    idusuario, // Idusuario
                    auxDto.getIdorigen(), // Idorigen
                    auxDto.getIdgrupo(), // Idgrupo
                    auxDto.getIdsocio(), // Idsocio
                    new BigDecimal(aCapital), // Acapital
                    new BigDecimal("0.00"), // IM
                    new BigDecimal("0.00"), // IMCALC
                    aiva, // A IVA (Producto servicio)
                    new BigDecimal("0.00"), // IO
                    ioCalc, // IOCALC
                    new BigDecimal("0.00"), // IVAIM
                    new BigDecimal("0.00"), // IVAIMCALC
                    Idcuenta,
                    new BigDecimal("0.00"), // IVAIO
                    new BigDecimal("0.00"), // IVAIOCALC
                    new BigDecimal(efectivo), // Efectivo
                    referencia,
                    0, // Dias vencidos
                    new BigDecimal("0.00"), // Monto vencido
                    cargoabono, // Es true cuando es abono/deposito y False cuando sea cargo/retiro
                    ++movimiento, // Mov
                    saiFunciones.saiAuxiliarOriginal(auxDto.getAuxiliaresPK().getIdorigenp(), auxDto.getAuxiliaresPK().getIdproducto(), auxDto.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo));
        } catch (Exception e) {
            System.out.println("Error en insertar temporal captacion. " + e);
            return 0;
        }
    }

    // INSERTA EN TEMPORAL EL MOVIMIENTO DE PRESTAMO
    private int insertaTemporalPrestamo(AuxiliaresDTO auxDto, String sesion, int idusuario, DistribucionPrestamoDTO SaiDisMonto, SaiAuxiliarPrestamoDTO saiAux, String Idcuenta, String amount1, String referencia, boolean cargoabono, BigDecimal Aiva, Date fechaTrabajo) {
        try {
            return temporalService.insertaTemporal(sesion, // Sesion
                    auxDto.getAuxiliaresPK().getIdorigenp(), // Idorigenp
                    auxDto.getAuxiliaresPK().getIdproducto(), // Idproducto
                    auxDto.getAuxiliaresPK().getIdauxiliar(), // Idauxiliar
                    idusuario, // Idusuario
                    auxDto.getIdorigen(), // Idorigen
                    auxDto.getIdgrupo(), // Idgrupo
                    auxDto.getIdsocio(), // Idsocio
                    SaiDisMonto.getaCapital(), // Acapital
                    SaiDisMonto.getIm(), // IM
                    saiAux.getMontoImTotal(), // IMCALC
                    Aiva,
                    SaiDisMonto.getIo(), // IO
                    saiAux.getMontoIoTotal(), //IOCACL
                    SaiDisMonto.getIvaIm(), // IVAIM
                    saiAux.getIvaImTotal(), // IVAIMCALC
                    Idcuenta,
                    SaiDisMonto.getIvaIo(), // IVAIO
                    saiAux.getIvaIoTotal(), // IVAIOCALC
                    new BigDecimal(amount1), // Efectivo
                    referencia,
                    saiAux.getDiasVencidos(), // Dias vencidos
                    saiAux.getMontoVencido(), // Monto vencido
                    cargoabono, // Es true cuando es abono/deposito y False cuando sea cargo/retiro
                    ++movimiento,// Mov
                    saiFunciones.saiAuxiliarOriginal(auxDto.getAuxiliaresPK().getIdorigenp(), auxDto.getAuxiliaresPK().getIdproducto(), auxDto.getAuxiliaresPK().getIdauxiliar(), fechaTrabajo));
        } catch (Exception e) {
            System.out.println("Error en insertar temporal prestamo. " + e.getMessage());
            return 0;
        }
    }

    /*
    De lo que me enviaste, lo que sacas del sai_auxiliar, o aplcias en la siguiente funcion :
select monto_interes_para_siguiente_fecha_de_pago(
    <idorigenp>, 
    <idproducto>, 
    <idauxiliar>, 
    date('<fecha de hoy>'), 
    (<montovencido> + <proximo abono>), 
    <tasa de descuento del auxiliar>)
y te devuelve una cadena de 4 datos separados con | :
<monto del interes>|<IVA del interes>|<monto del interes mas el iva, para evitar redondeos>|<fecha del siguiente pago>
     */
    // OBTIENE EL TOTAL A PAGAR AL DIA DE HOY DEL PRESTAMO Y OTROS DATOS
    private ArrayList<String> datosPrestamo(AuxiliaresDTO aux) {
        BigDecimal montoInteres = new BigDecimal(0);
        BigDecimal ivaMontoInteres = new BigDecimal(0);
        BigDecimal adelantoDeInteres = new BigDecimal(0);
        String fechaSigPagoIntAde = "";
        //"param";"cobrar_interes_hasta_el_sig_pago"
        ArrayList<String> ListDatosPrestamo = new ArrayList<>();
        BigDecimal montoTotal = BigDecimal.ZERO;
        //System.out.println("adelanto de interes: " + montoTotal);
        OrigenesDTO origenesdto = origenesService.buscarOrigen(aux.getIdorigen());
        // Ejecuta el sai_auxiliar
        SaiAuxiliarPrestamoDTO saiAuxiliarPrestamoDTO = saiFunciones.saiAuxiliarPrestamo(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar(), origenesdto.getFechatrabajo());
        TablasPK elpk = new TablasPK("param", "cobrar_interes_hasta_el_sig_pago");
        TablasDTO tdto = tablasService.buscaTabla(elpk);
        if (tdto != null) {
            if (tdto.getDato1() != null && tdto.getDato2() != null) {
                if (Integer.parseInt(tdto.getDato1()) == 1) {
                    if (!tdto.getDato2().isEmpty()) {
                        if (Integer.parseInt(tdto.getDato2()) >= 1) {
                            Date fechahoy = saiFunciones.saiFechaTrabajo(aux.getIdorigen());
                            BigDecimal montovencidoMasProximoAbono = saiAuxiliarPrestamoDTO.getMontoVencido().add(saiAuxiliarPrestamoDTO.getMontoPorVencer());
                            String[] montointpag = saiFunciones.monto_interes_para_siguiente_fecha_de_pago(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar(), fechahoy, montovencidoMasProximoAbono, aux.getTasaiod());
                            if (montointpag != null) {
                                if (montointpag.length > 3) {
                                    montoInteres = new BigDecimal(montointpag[0]);
                                    ivaMontoInteres = new BigDecimal(montointpag[1]);
                                    adelantoDeInteres = new BigDecimal(montointpag[2]);
                                    fechaSigPagoIntAde = montointpag[3];
                                }
                            }
                        }
                    }
                }
            }
        }
        montoTotal = montoTotal.add(saiAuxiliarPrestamoDTO.getMontoVencido().add(saiAuxiliarPrestamoDTO.getMontoIoTotal()).add(saiAuxiliarPrestamoDTO.getIvaIoTotal()).add(saiAuxiliarPrestamoDTO.getMontoImTotal()).add(saiAuxiliarPrestamoDTO.getIvaImTotal()).add(saiAuxiliarPrestamoDTO.getMontoPorVencer()).add(saiAuxiliarPrestamoDTO.getComisionNpTotal()));
        BigDecimal totalPagar = BigDecimal.ZERO;
        // compareTo de BigDecimal ... 0 = Ambos valores son iguales, 1 = El primer valor es mayor, -1 = El segundo valor es mayor

        if (saiAuxiliarPrestamoDTO.getMontoPorVencer().compareTo(BigDecimal.ZERO) == 1) {
            // Obtiene el monto seguro hipotecario y lo suma al total
            List<SaiSeguroHipotecarioDTO> prestamoHipotecarios = saiSeguroHipotecarioService.buscarPagoSeguroHipotecario(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar(), origenesdto.getFechatrabajo());
            for (SaiSeguroHipotecarioDTO prestamoHipotecario : prestamoHipotecarios) {
                totalPagar = totalPagar.add(new BigDecimal(prestamoHipotecario.getApagar())).add(new BigDecimal(prestamoHipotecario.getIvaapagar()));
            }
        }
        BigDecimal montoComizion = BigDecimal.ZERO;
        if (saiAuxiliarPrestamoDTO.getDiasVencidos() > 0) {
            // Monto comision cobranza
            SaiComisionCobranzaDTO comisionCobranza = saiComisionCobranzaService.buscaComisionCobranza(aux.getAuxiliaresPK().getIdorigenp(), aux.getAuxiliaresPK().getIdproducto(), aux.getAuxiliaresPK().getIdauxiliar());
            montoComizion = new BigDecimal(comisionCobranza.getMontoComision());
        }
        // Agregamos la fecha del siguiente abono - posicion 0
        ListDatosPrestamo.add(saiAuxiliarPrestamoDTO.getFechaSigAbono());
        // Agregamos el total a pagar - posicion 1
        montoTotal = montoComizion.add(montoTotal.add(totalPagar));
        ListDatosPrestamo.add(new String(montoTotal.toString().getBytes()));

        montoTotal = montoTotal.add(adelantoDeInteres);
        // Agregamos el total a pagar + adelanto de interes- posicion 2
        ListDatosPrestamo.add(new String(montoTotal.toString().getBytes()));

        //System.out.println("adelanto de interes: " + montoTotal);
        ListDatosPrestamo.add(montoInteres.toString());// posicion 3
        ListDatosPrestamo.add(ivaMontoInteres.toString());// posicion 4

        ListDatosPrestamo.add(adelantoDeInteres.toString());// posicion 5
        ListDatosPrestamo.add(fechaSigPagoIntAde);// posicion 6

        return ListDatosPrestamo;
    }

    // MONTO MAXIMO A CUBRIR
    private Double maximoCubrir(AuxiliaresPK auxiliaresPK, Date fechaTrabajo, BigDecimal saldo) {
        SaiAuxiliarPrestamoDTO saiAux = saiFunciones.saiAuxiliarPrestamo(auxiliaresPK.getIdorigenp(), auxiliaresPK.getIdproducto(), auxiliaresPK.getIdauxiliar(), fechaTrabajo);
        return (saldo.add(saiAux.getMontoIoTotal()).add(saiAux.getMontoImTotal()).add(saiAux.getIvaIoTotal()).add(saiAux.getIvaImTotal())).doubleValue();
    }

    // AUXILIARESDTO
    private AuxiliaresDTO listaAuxiliaresDTO(String idproducto, List<AuxiliaresDTO> lista) {
        for (AuxiliaresDTO aux : lista) {
            if (aux.getAuxiliaresPK().getIdproducto() == Integer.parseInt(idproducto.trim())) {
                return aux;
            }
        }
        return null;
    }

    // OBTIENE EL NUMERO TOTAL DE AMORTIZACIONES CUBIERTAS.
    private void totalAmortizacionesCubiertas(Date vence, String sesion) {
        List<TemporalDTO> temporal = temporalService.ListTemporal(sesion);
        for (TemporalDTO temporalDTO : temporal) {
            AmortizacionesPK ampk = new AmortizacionesPK();
            ampk.setIdorigenp(temporalDTO.getTemporalPK().getIdorigenp());
            ampk.setIdproducto(temporalDTO.getTemporalPK().getIdproducto());
            ampk.setIdauxiliar(temporalDTO.getTemporalPK().getIdauxiliar());
            List<AmortizacionesDTO> amortiacionesCubiertas = amortizacionesService.amortizacionesSorteoBuenos(ampk, vence);
            /*
            int at = 0;
            for (AmortizacionesDTO amortiacionesCubierta : amortiacionesCubiertas) {
                at = at + 1;
            }
            temporalService.actualizaTemporalReferencia(sesion, temporalDTO.getTemporalPK().getIdorigenp(), temporalDTO.getTemporalPK().getIdproducto(), temporalDTO.getTemporalPK().getIdauxiliar(), temporalDTO.getMov(), Integer.toString(at));
             */
            int amortizaciones = amortiacionesCubiertas.size();
            temporalService.actualizaTemporalReferencia(sesion, temporalDTO.getTemporalPK().getIdorigenp(), temporalDTO.getTemporalPK().getIdproducto(), temporalDTO.getTemporalPK().getIdauxiliar(), temporalDTO.getMov(), Integer.toString(amortizaciones));
        }
    }

    // ELIMINA TEMPORAL POR idusuario y sesion
    private void eliminaTemporal(String sesion) {
        System.out.println("Eliminando temporal...");
        EntityManager em=AbstractFacade.getEntityManager();
        String consulta="SELECT count(*) FROM temporal WHERE sesion='"+sesion+"'";
        System.out.println("aun");
        Query query=em.createNativeQuery(consulta);
        System.out.println("aun 1.1");
        int count=Integer.parseInt(String.valueOf(query.getSingleResult()));
        System.out.println("Registros encontrados:"+count);
        if(count>0){
              try {
               EntityTransaction et = em.getTransaction();
	       et.begin();
               String Eliminar="DELETE FROM temporal WHERE sesion='"+sesion+"'";
	       int datosEli=em.createNativeQuery(Eliminar).executeUpdate();
               System.out.println("Sesiones Eliminadas:"+datosEli);
	       et.commit();       
            } catch (Exception e) {
                
            }
        }else{
            System.out.println(" Sin registros en temporal");
        }
        
        
        
        /*if (!temporal.isEmpty()) {
            for (TemporalDTO temporalDTO : temporal) {
                temporalService.eliminaTemporal(sesion, temporalDTO.getTemporalPK().getIdorigenp(), temporalDTO.getTemporalPK().getIdproducto(), temporalDTO.getTemporalPK().getIdauxiliar());
            }
        }*/
    }

    // OBTIENE EL NUMERO DE AUTORIZACION
    private AuxiliaresdDTO auxiliaresDTOtipo(AuxiliaresPK auxiliaresPK, int idtipo) {
        AuxiliaresDPK auxiliaresDPK = new AuxiliaresDPK(auxiliaresPK.getIdorigenp(), auxiliaresPK.getIdproducto(), auxiliaresPK.getIdauxiliar());
        AuxiliaresdDTO auxiliaresdDTO = auxiliaresDService.buscarAuxiliarD(auxiliaresDPK, idtipo);
        return auxiliaresdDTO;
    }

    // LISTA DE PRODUCTOS VALIDOS TOTALES.
    private List<String> listaProductoValido(String AcctType, String idTabla) {
        List<String> listaProductos = new ArrayList<>(0);
        if ("00".equals(AcctType)) {
            List<CatAcctTypeDTO> tiposCuentas = catAcctTypeServiceLocal.tipoDeCuentaSiscoop();
            // tabla catacctype
            // idaccttype ;        descripcion
            //"00"        ;       "Todas"
            //"10"        ;       "Ahorro"
            //"20"        ;       "Créditos"
            //se exploran los tipos de cuentas
            for (CatAcctTypeDTO tipoCuenta : tiposCuentas) {
                //si el tipo es 00 continua
                if (!"00".equals(tipoCuenta.getIdaccttype())) {
                    List<CatAcctTypeDTO> productos = productosPorAcctType(tipoCuenta.getIdaccttype(), idTabla);
                    for (CatAcctTypeDTO producto : productos) {
                        listaProductos.addAll(Arrays.asList(producto.getDescription()));
                    }
                }
            }
        } else {
            List<CatAcctTypeDTO> productos = productosPorAcctType(AcctType, idTabla);
            for (CatAcctTypeDTO producto : productos) {
                listaProductos.addAll(Arrays.asList(producto.getDescription()));
            }
        }
        return listaProductos;
    }

    // LISTA DE PRODUCTOS VALIDA POR ACCTTYPE
    private List<CatAcctTypeDTO> productosPorAcctType(String AcctType, String idTabla) {
        List<CatAcctTypeDTO> productosType = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String tipo = AcctType.charAt(0) + String.valueOf(i);
            TablasPK tpk = new TablasPK(idTabla, tipo);
            TablasDTO tbsdto = tablasService.buscaTabla(tpk);
            if (tbsdto.getTablasPK() != null) {
                String[] productos = tbsdto.getDato2().split(",");
                for (String producto : productos) {
                    System.out.println(producto);
                    CatAcctTypeDTO pro = new CatAcctTypeDTO();
                    pro.setIdaccttype(tipo);
                    pro.setDescription(producto);
                    productosType.add(pro);
                }
            }
        }
        return productosType;
    }

    // OBTIENE LOS PRODUCTOS QUE CORRESPONDEN A EL TIPO DE CUENTA (ACCTTYPE)
    private String getAcctTypeProducto(int idproducto, String idTabla) {
        String tipoDeCuenta = null;
        List<CatAcctTypeDTO> tipoCuentas = catAcctTypeServiceLocal.tipoDeCuentaSiscoop();
        for (CatAcctTypeDTO tipoCuenta : tipoCuentas) {
            List<CatAcctTypeDTO> productos = productosPorAcctType(tipoCuenta.getIdaccttype(), idTabla);
            for (CatAcctTypeDTO producto : productos) {
                if (idproducto == Integer.parseInt(producto.getDescription().trim())) {
                    tipoDeCuenta = producto.getIdaccttype();
                }
            }
        }
        return tipoDeCuenta;
    }

    // SEXO DE LA PERSONA EN FORMATO "M" Y "F".
    private String getSexoPersona(Short sexo) {
        String es = "N/A";
        switch (sexo) {
            case 1:
                es = "M";
                break;
            case 2:
                es = "F";
                break;
        }
        return es;
    }

    // ESTATUS DE LA PERSONA, 0 = ACTIVO Y 1 = INACTIVO.
    private String getStatus(boolean status) {
        if (status) {
            return "0";
        } else {
            return "1";
        }
    }

    // IDENTIFICA SI ES UN CARGO-ABONO EN LOS PRESTAMOS Y DEPOSITO-RETIRO EN CAPTACION
    private CatTxTypeDTO getIdTxTypre(int cargoabono, int tipoProducto, int tipoMov) {
        CatTxTypeDTO catTxTypeDTO = new CatTxTypeDTO();
        switch (tipoProducto) {
            // Captacion
            case 0:
                String tipo = tipoMovimiento(tipoMov);
                catTxTypeDTO.setDescription(tipo);
                if (cargoabono == 0) {
                    catTxTypeDTO.setIdTxType("01");
                    if (tipo == null) {
                        catTxTypeDTO.setDescription("Retiro");
                    }
                } else {
                    catTxTypeDTO.setIdTxType("02");
                    if (tipo == null) {
                        catTxTypeDTO.setDescription("Deposito");
                    }
                }
                break;
            // Prestamo
            case 2:
                if (cargoabono == 0) {
                    catTxTypeDTO.setIdTxType("81");
                    catTxTypeDTO.setDescription("Cargo");
                } else {
                    catTxTypeDTO.setIdTxType("82");
                    catTxTypeDTO.setDescription("Abono");
                }
                break;
        }
        return catTxTypeDTO;
    }

    /*
        1 - Castigo
        2 - Quita
        3 - Condonacion
        4 - Interes
        5 - Membresia TDD
        6 - Ajuste
        7 - Ajustado
     */
    // TIPO DE MOVIMIENTO
    private String tipoMovimiento(int tipoMov) {
        if (tipoMov == 4) {
            return "Interes";
        }
        return null;
    }

    // VALIDA EL MONTO DISPONIBLE CON EL MONTO A RETIRAR
    private boolean validaFondos(BigDecimal saldo, String monto) {
        return Double.parseDouble(saldo.toString()) >= Double.parseDouble(monto);
    }

    // VALIDA QUE EL NUMERO DE CELULAR CUENTE CON 10 DIGITOS
    private String validaNumeroCelular(String numeroCelular) {
        if (numeroCelular != null) {
            if (numeroCelular.length() == 10) {
                return numeroCelular;
            }
        }
        return null;
    }

    // VALIDA QUE A ESTE PRODUCTO SE LE ENVIE MENSAJES DE SMS POR CADA MOVIMIENTO
    private boolean validaEnviaSMS(int idproducto, String idTabla) {
        TablasDTO tablasDTO = valorTablas(idTabla, "producto_sms");
        String[] productos = tablasDTO.getDato2().split(",");
        for (String producto : productos) {
            if (Integer.parseInt(producto.trim()) == idproducto) {
                return true;
            }
        }
        return false;
    }

    // BUSCA EN TABLAS
    @EJB
    private TablasFacade tablasFacade;
    @Override
    public TablasDTO valorTablas(String idtabla, String idelemento) {
       
       EntityManager entity;
        entity = tablasFacade.getEntityManager();
       // EntityManagerFactory emf=tablasFacade.getEntityManager();
        System.out.println("Buscando tabla...");
       // entity=emf.createEntityManager();
        
        System.out.println("IdTabla:"+idtabla+" , IdElemento:"+idelemento);
        TablasDTO tablasDTO = new TablasDTO();
        try {
            String consulta = " SELECT t.* "
                    + "         FROM tablas t "
                    + "         WHERE t.idtabla = ? "
                    + "           AND t.idelemento = ? ";
            Query query = entity.createNativeQuery(consulta, Tablas.class);
            query.setParameter(1,idtabla);
            query.setParameter(2, idelemento);
            Tablas tablas = (Tablas) query.getSingleResult();
            
            if (tablas != null) {
                System.out.println("Conviertiendo tabla....");
                tablasDTO = fromEntity2DTO(tablas);
                System.out.println("Tabla Convertida:"+tablasDTO);
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar la tabla en (Tablas Services): " + e.getMessage());
        }
        entity.close();
        return tablasDTO;
    
        
        
        /*
        
        
        
        try {
            TablasPK tpkcon = new TablasPK(idtabla, idelemento);
            // obtenemos la configuracion que utilizara el cliente para mostrar los datos
return tablasService.buscaTabla(tpkcon);
        } catch (Exception e) {
            System.out.println("No existe la tabla: " + idtabla + " con el elemento: " + idelemento + " - " + e.getMessage());
            return new TablasDTO();
        }*/
    
    }

    TablasDTO fromEntity2DTO(Tablas pro) {
        TablasDTO dto = new TablasDTO();
        try {
            BeanUtils.copyProperties(dto, pro);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de TablasService");
        }
        return dto;
    
}


    
    // VALIDA EL MONTO MAXIMO Y LAS UDIS PARA LOS MENORES Y/O JUVENILES
    private boolean restringirSaldoUDIS(ResponseTransferenciaACuentaDTO rdepo, AuxiliaresDTO auxiliaresDTODestino, String Amount1, String idTabla) {
        // Folio de destino
        boolean esMenor = false;
        BigDecimal Amount = new BigDecimal(Amount1);
        TablasDTO tablasDTOgrupo = valorTablas(idTabla, "grupo_menores");
        String[] grupoMenores = tablasDTOgrupo.getDato2().split(","); // grupo de menores
        for (String grupoMenor : grupoMenores) {
            if (Integer.parseInt(grupoMenor.trim()) == auxiliaresDTODestino.getIdgrupo()) {
                esMenor = true;
            }
        }
        if (esMenor) {
            // Validamos que las UIS sean igual o menor
            // Maximo de UDIS permitido
            TablasDTO tablasDTOudis = valorTablas(idTabla, "udis_maximo_menores");
            BigDecimal valorUDIS = new BigDecimal(tablasService.buscaValorUDIS().getDato1());
            if (valorUDIS.doubleValue() > 0) {
                BigDecimal valroMaximoUDIS = valorUDIS.multiply(new BigDecimal(tablasDTOudis.getDato1()));
                if (Amount.doubleValue() > valroMaximoUDIS.doubleValue()) {
                    rdepo.setResponseCode(CodigoError.CE077.getIdError()); // El monto sobrepasa el valor de udis configurado
                    rdepo.setDescription(CodigoError.CE077.getMensaje());
                    return false;
                }
                TablasDTO tablasDTOmontoMaximoMenores = valorTablas(idTabla, "saldo_maximo_menores");
                BigDecimal saldo = auxiliaresDService.saldoAuxiliaresD(1, auxiliaresDTODestino.getAuxiliaresPK().getIdorigenp(), auxiliaresDTODestino.getAuxiliaresPK().getIdproducto(), auxiliaresDTODestino.getAuxiliaresPK().getIdauxiliar(), 1);
                BigDecimal saldoTotal = saldo.add(Amount);
                // Si el monto total a depositar mas el monto depositado es mayor al monto permitido envia error
                if (saldoTotal.doubleValue() > Double.valueOf(tablasDTOmontoMaximoMenores.getDato1())) {
                    rdepo.setResponseCode(CodigoError.CE078.getIdError()); // El monto sobrepasa el limite menusal permitido.
                    rdepo.setDescription(CodigoError.CE078.getMensaje());
                    return false;
                }
            } else {
                rdepo.setResponseCode(CodigoError.CE079.getIdError()); // No esta configurada la tabla udis_maximo_menores
                rdepo.setDescription(CodigoError.CE079.getMensaje());
                return false;
            }
        }
        return true;
    }

    private boolean estaEnGrupoPermitido(String IdAcctD, String idTabla) {
        int[] opaDestino = convertidor.getOPA(IdAcctD);
        AuxiliaresPK auxiliaresPKDestino = new AuxiliaresPK(opaDestino[0], opaDestino[1], opaDestino[2]);
        AuxiliaresDTO auxiliaresDTODestino = auxiliaresService.buscarAuxiliar(auxiliaresPKDestino);
        TablasPK tpkdc = new TablasPK(idTabla, "grupos_banca_movil");
        TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
        String[] grupos = tdtodc.getDato2().trim().split(",");
        for (int i = 0; i < grupos.length; i++) {
            int grupo = Integer.parseInt(grupos[i].replace(" ", ""));
            if (grupo == auxiliaresDTODestino.getIdgrupo()) {
                return true;
            }
        }
        return false;
    }

    private boolean socioBloqueado(String IdClient, int idusuario, String idTabla) {
        boolean bloqueado = false;
        try {
            TablasDTO tablasSopar = valorTablas(idTabla, "sopar");
            String[] listasSopar = tablasSopar.getDato2().split(",");
            for (String sopar : listasSopar) {
                bloqueado = existeEnSoparOGS(IdClient, idusuario, sopar.trim());
                if (bloqueado) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de sopar. " + e);
        }
        return bloqueado;
    }

    /*
     VALIDAR MONTOS MINIMO Y MAXIMO (BANCAMOVIL)
     socio:              %s-%s-%s        String
     opa:                %s-%s-%s        String
     cargoabono:         %i              Integer
     monto:              %f              Double
     saldo:              %f              Double
     idproducto:         %s              String
     modulo:             %i              Integer
     *-- EStos datos restantes son para prestamos --*
     plazo:              %i              Integer
     montosolicitado:    %s              String
     montoautorizado:    %s              String
     montoprestado:      %s              String
     */
    private List<String> minimoMaximo(String socio, String opa, int cargoabono, double monto, double saldo, String idproducto, int modulo) {
        //------------
        socio = "030102-10-179828";
        opa = "030102-34502-00000029";
        cargoabono = 1;
        monto = 50.00;
        saldo = 18046.51;
        idproducto = "34502";
        modulo = 1;
        //------------
        List<String> minMax = new ArrayList<>();
        String datos = "socio:" + socio + "|opa:" + opa + "|cargoabono:" + cargoabono + "|monto:" + monto + "|saldo:" + saldo + "|idproducto:" + idproducto + "|modulo:" + modulo;
        minMax.add(saiFunciones.sai_limite_de_saldo_minimo(Integer.parseInt(idproducto.trim()), datos));
        minMax.add(saiFunciones.sai_limite_de_saldo_maximo(Integer.parseInt(idproducto.trim()), datos));
        return minMax;
    }

    // -------------------------------------------------------------------------
    // ----------------------------- SPEI --------------------------------------
    // -------------------------------------------------------------------------
    // REALIZA LA TRANSFERENCIA.
    @Override
    public ResponseTransferenciaACuentaDTO realizarTransferenciaSPEI(String idAcctO, String idAcctD, String idTeller, String amount1, int tipoPoliza, String conceptoPoliza, int oriDes, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla) {
        movimiento = 0;
        // Voy preparando el response con los datos que tengo disponibles
        ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
        rdepo.setResponseCode(CodigoError.SPEI057.getIdError());
        rdepo.setDescription(CodigoError.SPEI057.getMensaje());
        try {
            // Se generan los folios para el destino
            int[] opaDestino = convertidor.getOPA(idAcctD);
            AuxiliaresPK auxpkDestino = new AuxiliaresPK(opaDestino[0], opaDestino[1], opaDestino[2]);
            AuxiliaresDTO auxdtoDestino = auxiliaresService.buscarAuxiliar(auxpkDestino);
            ProductosDTO pdsD = productosService.buscarProducto(auxdtoDestino.getAuxiliaresPK().getIdproducto());
            nomDestino = pdsD.getNombre();
            PersonasPK perPKDestino = new PersonasPK(auxdtoDestino.getIdorigen(), auxdtoDestino.getIdgrupo(), auxdtoDestino.getIdsocio());
            PersonasDTO perDestino = personasService.buscarPersona(perPKDestino);
            // Numero celular destino
            celDestino = validaNumeroCelular(perDestino.getCelular());
            // Se obtiene la fecha de trabajo.
            Date fechaTrabajo = saiFunciones.saiFechaTrabajo(auxdtoDestino.getIdorigen()); // consulto origenes para sacar la fecha de trabajo
            // Consulto el usuario con el que creare la poliza
            TablasPK tpkdc = new TablasPK(idTabla, idTeller);
            TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
            UsuariosDTO usu = usuariosService.buscaUsuario(Integer.parseInt(tdtodc.getDato1().trim()));
            // El origen es la cuenta ------------------------------------------
            String cuenta = idAcctO;
            AuxiliaresPK auxpkOrigen = new AuxiliaresPK(0, 0, 0);
            AuxiliaresDTO auxdtoOrigen = new AuxiliaresDTO();
            auxdtoOrigen.setAuxiliaresPK(auxpkOrigen);
            auxdtoOrigen.setIdorigen(auxdtoDestino.getIdorigen());
            auxdtoOrigen.setIdgrupo(auxdtoDestino.getIdgrupo());
            auxdtoOrigen.setIdsocio(auxdtoDestino.getIdsocio());
            // -----------------------------------------------------------------
            // Si no existe el usuario no hace nada (se queda con error 096).
            if (usu != null) {
                // Elimino todos los temporales que sean de este usuario concatenado al socio
                String misesion = "W" + usu.getIdusuario() + "-" + convertidor.getOGSsesion(auxdtoDestino.getIdorigen(), auxdtoDestino.getIdgrupo(), auxdtoDestino.getIdsocio());
                eliminaTemporal(misesion);
                Integer insertaOrigen;
                Integer insertaDestino;
                // Que tipo de producto es el destino
                ProductosDTO productoDestino = productosService.buscarProducto(auxdtoDestino.getAuxiliaresPK().getIdproducto()); // Consulto productos
                // Valida que el producto al cual se va a retirar no sea un prestamo
                if (productoDestino.getTipoproducto() != 2) {
                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva, montoio - CUENTA CONTABLE
                    insertaOrigen = insertaTemporalCaptacion(auxdtoOrigen, misesion, usu.getIdusuario(), amount1, cuenta, "0.00", " ", false, bigd, bigd, fechaTrabajo);
                    // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                    SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTODestino = ejecutaSaiAuxiliarAhorro(auxdtoDestino.getAuxiliaresPK().getIdorigenp(), auxdtoDestino.getAuxiliaresPK().getIdproducto(), auxdtoDestino.getAuxiliaresPK().getIdauxiliar());
                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva, montoio - TDD
                    insertaDestino = insertaTemporalCaptacion(auxdtoDestino, misesion, usu.getIdusuario(), amount1, "0", "0.00", " ", true, bigd, saiAuxiliarAhorroDTODestino.getMontoIo(), fechaTrabajo);
                    // Si los temporales se insertaron correctamente, los proceso y si se procesaron correctamente los elimino
                    if (insertaOrigen == 1 && insertaDestino == 1) {
                        // ---------------------- TDD ----------------------------------
                        // Activar TDD
                        TablasDTO activarTDD = valorTablas(idTabla, "activar_tdd");
                        if ("1".equals(activarTDD.getDato1())) {
                            // Se realiza el deposito en SYC
                            boolean abonoTdd = tarjetaDeDebito.depositoTdd(rdepo, auxpkDestino, Double.parseDouble(amount1), usu.getIdusuario());
                            if (rdepo.getXtra3().equals("1")) {
                                if (!abonoTdd) {
                                    return rdepo;
                                }
                            }
                            rdepo.setXtra3("");
                        }
                        // -------------------------------------------------------------
                        // ElementosProcesar es la cantidad de insert que se hicieron este mas adelante se compara con la cantidad re registros de temporales procesados
                        Integer elementosProcesar;
                        elementosProcesar = insertaOrigen + insertaDestino;
                        // Procesa los movimientos de temporal
                        int elementosProcesados = saiFunciones.procesaTemporal(usu.getIdusuario(), misesion, fechaTrabajo, usu.getIdorigen(), tipoPoliza, conceptoPoliza, usu.getPingreso(), condonar, esResumen);
                        // Verifica que los elementos totales sean los procesados
                        if (elementosProcesar == elementosProcesados) {
                            try {
                                TablasPK tpkcon = new TablasPK(idTabla, "ws_transfer_own_req");
                                // obtenemos la configuracion que utilizara el cliente para mostrar los datos
                                TablasDTO tabcon = tablasService.buscaTabla(tpkcon);
                                rdepo.setConfig(tabcon.getDato2());
                                // Return de datos
                                TablasPK tpkxtra5 = new TablasPK(idTabla, "xtra5");
                                // obtenemos el dato del xtra5
                                TablasDTO tabxtra5 = tablasService.buscaTabla(tpkxtra5);
                                String xtra5 = tabxtra5.getDato2();
                                rdepo.setXtra5(xtra5);
                            } catch (Exception e) {
                                System.out.println("Error al obtener la configuracion de la tabla: " + idTabla + " del elemento ws_transfer_own_req. " + e.getMessage());
                            }
                            // Obtiene el numero de autorizacion
                            nAd = String.valueOf(auxiliaresDTOtipo(auxpkDestino, 3).getTransaccion()); // El 3 es porque es el tipo de poliza - destino
                            // Pasa el resultado al DTO
                            rdepo.setResponseCode(CodigoError.CE000.getIdError());
                            rdepo.setDescription(CodigoError.CE000.getMensaje());
                            // Elimino temporal
                            eliminaTemporal(misesion);
                            // Comision --------------------------------------------------------
                            TablasDTO comision = valorTablas(idTabla, "monto_comision");
                            BigDecimal montoComision = new BigDecimal(comision.getDato1());
                            TablasDTO productoComision = valorTablas(idTabla, "producto_comision");
                            ProductosDTO productoServicio = productosService.buscarProducto(Integer.parseInt(productoComision.getDato1().trim()));
                            BigDecimal ivaComision = productoServicio.getIva().divide(new BigDecimal(100));
                            BigDecimal aiva = (montoComision.multiply(ivaComision)).setScale(2, BigDecimal.ROUND_CEILING);
                            // Eliminar pingreso
                            saiFunciones.eliminaPingreso(usu.getIdusuario());
                            // Ahora el origen es el destino
                            conceptoPoliza = "Comision SPEI";
                            // idAcctD, producto comision, monto comision, iva, idteller, tipo poliza, concepto poliza
                            realizarTransferenciaComision(idAcctD, Integer.parseInt(productoComision.getDato1().trim()), montoComision.toString(), aiva, idTeller, 3, conceptoPoliza, condonar, esResumen, idTabla); // Tipo poliza 3 porque es de diario la comision
                            // -----------------------------------------------------------------
                        }
                    }
                } else {
                    rdepo.setResponseCode(CodigoError.SPEI057.getIdError());
                    rdepo.setDescription(CodigoError.SPEI057.getMensaje());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en realizarTransferenciaSPEI de SaicopService: " + e.getMessage());
        }
        // Retorna el resultado
        return rdepo;
    }

    // REALIZA LA TRANSFERENCIA SERVICIO.
    private ResponseTransferenciaACuentaDTO realizarTransferenciaComision(String idAcctO, Integer idproducto, String monto, BigDecimal aiva, String idTeller, int tipoPoliza, String conceptoPoliza, boolean condonar, boolean esResumen, String idTabla) {
        movimiento = 0;
        // Voy preparando el response con los datos que tengo disponibles
        ResponseTransferenciaACuentaDTO rdepo = new ResponseTransferenciaACuentaDTO();
        rdepo.setResponseCode(CodigoError.CE096.getIdError());
        rdepo.setDescription(CodigoError.CE096.getMensaje());
        try {
            // Se generan los folios para el origen
            int[] opaOrigen = convertidor.getOPA(idAcctO);
            AuxiliaresPK auxpkOrigen = new AuxiliaresPK(opaOrigen[0], opaOrigen[1], opaOrigen[2]);
            AuxiliaresDTO auxdtoOrigen = auxiliaresService.buscarAuxiliar(auxpkOrigen);
            // El producto de la comision --------------------------------------
            AuxiliaresPK auxpkComision = new AuxiliaresPK(0, idproducto, 0);
            AuxiliaresDTO auxdtoComision = new AuxiliaresDTO();
            auxdtoComision.setAuxiliaresPK(auxpkComision);
            auxdtoComision.setIdorigen(auxdtoOrigen.getIdorigen());
            auxdtoComision.setIdgrupo(auxdtoOrigen.getIdgrupo());
            auxdtoComision.setIdsocio(auxdtoOrigen.getIdsocio());
            // -----------------------------------------------------------------
            // Se obtiene la fecha de trabajo.
            Date fechaTrabajo = saiFunciones.saiFechaTrabajo(auxdtoOrigen.getIdorigen()); // consulto origenes para sacar la fecha de trabajo
            // Consulto el usuario con el que creare la poliza
            TablasPK tpkdc = new TablasPK(idTabla, idTeller);
            TablasDTO tdtodc = tablasService.buscaTabla(tpkdc);
            UsuariosDTO usu = usuariosService.buscaUsuario(Integer.parseInt(tdtodc.getDato1().trim()));
            // Si no existe el usuario no hace nada (se queda con error 096).
            if (usu != null) {
                // Elimino todos los temporales que sean de este usuario concatenado al socio
                String misesion = "W" + usu.getIdusuario() + "-" + convertidor.getOGSsesion(auxdtoOrigen.getIdorigen(), auxdtoOrigen.getIdgrupo(), auxdtoOrigen.getIdsocio());
                eliminaTemporal(misesion);
                Integer insertaOrigen;
                Integer insertaDestino;
                // Que tipo de producto es el origen
                ProductosDTO productoOrigen = productosService.buscarProducto(auxdtoOrigen.getAuxiliaresPK().getIdproducto()); // Consulto productos
                // Valida que el origen al cual se va a retirar no sea un prestamo
                if (productoOrigen.getTipoproducto() != 2) {
                    BigDecimal comisionIva = new BigDecimal(monto).add(aiva);
                    // Obtenemos informacion del ahorro con la funcion SaiAuxiliarAhorroDTO
                    SaiAuxiliarAhorroDTO saiAuxiliarAhorroDTOOrigen = ejecutaSaiAuxiliarAhorro(auxdtoOrigen.getAuxiliaresPK().getIdorigenp(), auxdtoOrigen.getAuxiliaresPK().getIdproducto(), auxdtoOrigen.getAuxiliaresPK().getIdauxiliar());
                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                    insertaOrigen = insertaTemporalCaptacion(auxdtoOrigen, misesion, usu.getIdusuario(), comisionIva.toString(), "0", "0.00", " ", false, bigd, saiAuxiliarAhorroDTOOrigen.getMontoIo(), fechaTrabajo);
                    // Inserta el temporal AuxiliaresDTO, sesion, idusuario, aCapital, idcuenta, efectivo, cargoabono, aiva
                    insertaDestino = insertaTemporalCaptacion(auxdtoComision, misesion, usu.getIdusuario(), monto, "0", "0.00", " ", true, aiva, bigd, fechaTrabajo);
                    // Si los temporales se insertaron correctamente, los proceso y si se procesaron correctamente los elimino
                    if (insertaOrigen == 1 && insertaDestino == 1) {
                        // ---------------------- TDD ----------------------------------
                        // Activar TDD
                        TablasDTO activarTDD = valorTablas(idTabla, "activar_tdd");
                        if ("1".equals(activarTDD.getDato1())) {
                            // Se realiza el retiro en SYC
                            boolean retiroTdd = tarjetaDeDebito.retiroTdd(rdepo, auxpkOrigen, Double.parseDouble(comisionIva.toString()), usu.getIdusuario());
                            if (rdepo.getXtra3().equals("1")) {
                                rdepo.setXtra3("");
                                if (!retiroTdd) {
                                    return rdepo;
                                }
                            }
                            rdepo.setXtra3("");
                        }
                        // -------------------------------------------------------------
                        // ElementosProcesar es la cantidad de insert que se hicieron este mas adelante se compara con la cantidad re registros de temporales procesados
                        Integer elementosProcesar;
                        elementosProcesar = insertaOrigen + insertaDestino;
                        // Procesa los movimientos de temporal
                        int elementosProcesados = saiFunciones.procesaTemporal(usu.getIdusuario(), misesion, fechaTrabajo, usu.getIdorigen(), tipoPoliza, conceptoPoliza, usu.getPingreso(), condonar, esResumen);
                        // Verifica que los elementos totales sean los procesados
                        if (elementosProcesar == elementosProcesados) {
                            // Pasa el resultado al DTO
                            rdepo.setResponseCode(CodigoError.CE000.getIdError());
                            rdepo.setDescription(CodigoError.CE000.getMensaje());
                            // Elimino temporal
                            eliminaTemporal(misesion);
                        }
                    }
                } else {
                    rdepo.setResponseCode(CodigoError.CE085.getIdError());
                    rdepo.setDescription(CodigoError.CE085.getMensaje());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en realizarTransferenciaComision de SisccopService: " + e.getMessage());
        }
        // Retorna el resultado
        return rdepo;
    }

}
