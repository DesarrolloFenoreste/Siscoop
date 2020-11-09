/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosExternos.utilidades;

import com.fenoreste.modelo.dto.SPEI.AbonospeiDTO;
import com.fenoreste.modelo.dto.SPEI.CuentaToOpaDTO;
import com.fenoreste.modelo.dto.SPEI.WsSiscoopClabeInterbancariaDTO;
import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.dto.UsuariosDTO;
import com.fenoreste.modelo.dto.tdd.WsSiscoopFoliosTarjetasDTO;
import com.fenoreste.modelo.ejb.interfaceService.AbonoSPEIServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.SiscoopServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopClabeInterbancariaServiceLocal;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopFoliosTarjetasServiceLocal;
import com.fenoreste.modelo.entidad.TablasPK;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gerardo
 */
@Stateless
public class SPEI {

    @EJB
    private AbonoSPEIServiceLocal abonoSPEIService;
    @EJB
    private WsSiscoopFoliosTarjetasServiceLocal wsSiscoopFoliosTarjetasService;
    @EJB
    private WsSiscoopClabeInterbancariaServiceLocal wsSiscoopClabeInterbancariaService;
    @EJB
    private SiscoopServiceLocal siscoop;
    @EJB
    private TablasServiceLocal tablasService;

    // OBTENEMOS LOS DATOS DEL USUARIO
    public UsuariosDTO usuario(String IdTeller, String idTabla) {
        try {
            return siscoop.datoUsuario(IdTeller, idTabla);
        } catch (Exception e) {
            System.out.println("Error en consultar usuario SPEI. " + e.getMessage());
            return new UsuariosDTO();
        }
    }

    // EN BASE AL TIPO OBTIENE EL OPA DE LA CUENTA, CELULAR O CLABE
    public CuentaToOpaDTO opaSPEI(String TipoCuentaBeneficiario, String CuentaBeneficiario) {
        switch (Integer.parseInt(TipoCuentaBeneficiario)) {
            case 3:
                // CuentaBeneficiario = Tarjeta TDD
                return tarjetaTDD(CuentaBeneficiario);
            case 10:
                return null;
            case 40:
                // CuentaBeneficiario = CLABE
                return clabeInterbancaria(CuentaBeneficiario);
            default:
                return null;
        }
    }

    // OBTIENE LOS DATOS DE LA TARJETA DDD
    private CuentaToOpaDTO tarjetaTDD(String CuentaBeneficiario) {
        // CuentaBeneficiario = Tarjeta TDD
        WsSiscoopFoliosTarjetasDTO tarjetaTDD = wsSiscoopFoliosTarjetasService.buscaTarjetaTDD(CuentaBeneficiario);
        if (tarjetaTDD.getWsSiscoopFoliosTarjetasPK() != null) {
            CuentaToOpaDTO cuentaToOpaDTO = new CuentaToOpaDTO();
            cuentaToOpaDTO.setIdorigenp(tarjetaTDD.getWsSiscoopFoliosTarjetasPK().getIdorigenp());
            cuentaToOpaDTO.setIdproducto(tarjetaTDD.getWsSiscoopFoliosTarjetasPK().getIdproducto());
            cuentaToOpaDTO.setIdauxiliar(tarjetaTDD.getWsSiscoopFoliosTarjetasPK().getIdauxiliar());
            cuentaToOpaDTO.setCuenta(tarjetaTDD.getWsSiscoopFoliosTarjetasPK().getIdtarjeta());
            cuentaToOpaDTO.setAsignada(tarjetaTDD.getAsignada());
            cuentaToOpaDTO.setActiva(tarjetaTDD.getActiva());
            cuentaToOpaDTO.setBloqueada(tarjetaTDD.getBloqueada());
            return cuentaToOpaDTO;
        }
        return null;
    }

    // OBTIENE LOS DATOS DE LA CLABE INTERBANCARIA
    private CuentaToOpaDTO clabeInterbancaria(String CuentaBeneficiario) {
        // CuentaBeneficiario = CLABE interbancaria
        WsSiscoopClabeInterbancariaDTO clabe = wsSiscoopClabeInterbancariaService.buscaClabeInterbancaria(CuentaBeneficiario);
        if (clabe.getWsSiscoopClabeInterbancariaPK() != null) {
            CuentaToOpaDTO cuentaToOpaDTO = new CuentaToOpaDTO();
            cuentaToOpaDTO.setIdorigenp(clabe.getWsSiscoopClabeInterbancariaPK().getIdorigenp());
            cuentaToOpaDTO.setIdproducto(clabe.getWsSiscoopClabeInterbancariaPK().getIdproducto());
            cuentaToOpaDTO.setIdauxiliar(clabe.getWsSiscoopClabeInterbancariaPK().getIdauxiliar());
            cuentaToOpaDTO.setCuenta(clabe.getClabe());
            cuentaToOpaDTO.setAsignada(clabe.isAsignada());
            cuentaToOpaDTO.setActiva(clabe.isActiva());
            cuentaToOpaDTO.setBloqueada(clabe.isBloqueada());
            return cuentaToOpaDTO;
        }
        return null;
    }

    // OBTENEMOS LA CUENTA CONTABLE PARA SPEI
    public String cuentaContableSPEI(String idTabla) {
        try {
            TablasPK tpkcon = new TablasPK(idTabla, "cuenta_contable");
            // obtenemos la configuracion que utilizara el cliente para mostrar los datos
            TablasDTO tabcon = tablasService.buscaTabla(tpkcon);
            return tabcon.getDato1();
        } catch (Exception e) {
            System.out.println("Error en consultar cuenta contable SPEI. " + e.getMessage());
            return null;
        }
    }

    //--------------------------------------------------------------------------
    // INSERTA EN EL LOG EL MOVIMIENTO QUE SE APLICARA
    public int insertaAbonoSPEI(String Clave, String FechaOperacion, String InstitucionOrdenante, String InstitucionBeneficiaria, String ClaveRastreo, String Monto, String NombreOrdenante, String TipoCuentaOrdenante, String CuentaOrdenante, String RfcCurpOrdenante, String NombreBeneficiario, String TipoCuentaBeneficiario, String CuentaBeneficiario, String RfcCurpBeneficiario, String ConceptoPago, String ReferenciaNumerica, String Empresa, String IdTeller, String SessionId) {
        try {
            return abonoSPEIService.insertaAbonoSPEI(Clave, FechaOperacion, InstitucionOrdenante, InstitucionBeneficiaria, ClaveRastreo, Monto, NombreOrdenante, TipoCuentaOrdenante, CuentaOrdenante, RfcCurpOrdenante, NombreBeneficiario, TipoCuentaBeneficiario, CuentaBeneficiario, RfcCurpBeneficiario, ConceptoPago, ReferenciaNumerica, Empresa, IdTeller, SessionId);
        } catch (Exception e) {
            System.out.println("Error en insertar: " + e.getMessage());
            return 0;
        }

    }

    // ACTUALIZA EN EL LOG SI SE APLICO EL MOVIMIENTO
    public int actualizaAbonoSPEI(String Clave, String ResponseCode, boolean Aplicado) {
        try {
            return abonoSPEIService.actualizaAbonoSPEI(Clave, ResponseCode, Aplicado);
        } catch (Exception e) {
            System.out.println("Error en atualizar: " + e.getMessage());
            return 0;
        }
    }

    // BUSCA ABONO SPEI
    public AbonospeiDTO buscaAbonoSPEI(String Clave) {
        try {
            return abonoSPEIService.buscaMovimientoSPEI(Clave);
        } catch (Exception e) {
            System.out.println("Error en buscar: " + e.getMessage());
            return new AbonospeiDTO();
        }
    }

    // MONTO DIARIO ACUMULADO SPEI
    public BigDecimal montoDiarioSPEI(String Clave) {
        try {
            return abonoSPEIService.montoDiarioSPEI(Clave);
        } catch (Exception e) {
            System.out.println("Error en buscar: " + e.getMessage());
            return new BigDecimal(0.00);
        }
    }
    
}
