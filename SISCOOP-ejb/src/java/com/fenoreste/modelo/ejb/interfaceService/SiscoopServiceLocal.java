package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.AuxiliaresDTO;
import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.dto.UsuariosDTO;
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
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 */
@LocalBean
public interface SiscoopServiceLocal {

    ProductoDTO buscaProducto(String IdAcct, String AcctType, String AcctStatus, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla);

    DatoClienteDTO buscaCliente(String IdClient, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla);

    DatoClienteDTO buscaClienteBancaMovil(String IdClient, String TxType, String SessionId, String User, String Password, int tipoEntrada, String idTabla);

    List<CuentaUsuarioDTO> buscarCuentasUsuario(String IdClient, String AcctType, String IdChannel, String IdTeller, String SessionId, String User, String Password, int tipoEntrada, String idTabla);

    ResponseDepositoACuentaDTO depositoACuenta(String IdTeller, String IdAcct, String TxType, Integer IdOperation, String Date, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla);

    ResponseTransferenciaACuentaDTO transferenciaACuentaPropia(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla);

    ResponseTransferenciaACuentaDTO transferenciaACuentaDeTerceros(String IdTeller, String TxType, String IdAcctO, String IdAcctD, Date Date, String IdOperation, String Amount1, String Amount2, String Reference, String Concept, String SessionId, String User, String Password, int tipoPoliza, String conceptoPoliza, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla);

    SaiAuxiliarPrestamoDTO ejecutaSaiAuxiliarPrestamo(int idorigenp, int idproducto, int idauxiliar);

    SaiAuxiliarAhorroDTO ejecutaSaiAuxiliarAhorro(int idorigenp, int idproducto, int idauxiliar);

    AuxiliaresDTO buscaAuxiliar(AuxiliaresPK auxpk);

    List<CuentasTercerosDTO> buscaCuentasTerceros(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK, String idTabla);

    List<MovimientoCuentaDTO> buscaMovimientosCuenta(String IdAcct, String IdTeller, String SessionId, String User, String Password, int tipoEntrada, String idTabla);

    ProductosTrasODDTO productosOrigenDestino(String idClient, String IdTeller, String tipoTransferencia, String idTabla);
    
    boolean estatusOrigen(int idorigen);

    boolean ServicioActivo(String idTabla);

    UsuariosDTO datoUsuario(String idTeller, String idTabla);

    int idusuario(String IdTeller, String idTabla);

    boolean validaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String Date, String Amount1, String Amount2);

    int actualizaMovimientoCajeroReceptor(String IdTeller, String IdAcct, String TxType, Integer idOperation, String Date, String Amount1, String Amount2, boolean estatus);

    boolean existeEnSoparOGS(String IdClient, int idusuario, String tipo);

    boolean existeEnSoparOPA(String IdAcct, int idusuario, String tipo);

    SaldoAuxiliarDTO saldoAuxiliarDTO(int idorigenp, int idproducto, int idauxiliar, String idTeller, String idTabla);

    ResponseTransferenciaACuentaDTO realizarTransferenciaSPEI(String idAcctO, String idAcctD, String idTeller, String amount1, int tipoPoliza, String conceptoPoliza, int oriDes, int tipoEntrada, boolean condonar, boolean esResumen, String idTabla);
    
    public TablasDTO valorTablas(String idTabla, String idelemento);
    
}
