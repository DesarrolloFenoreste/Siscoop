/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.TemporalDTO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author prometeo
 */
@LocalBean
public interface TemporalServiceLocal {

    List<TemporalDTO> ListTemporal(String sesion);

    int insertaTemporal(String sesion, int idorigenp, int idproducto, int idauxiliar, int idusuario, int idorigen, int idgrupo, int idsocio, BigDecimal acapital, BigDecimal Im, BigDecimal ImCalc, BigDecimal Aiva, BigDecimal Io, BigDecimal IoCalc, BigDecimal IvaIm, BigDecimal IvaImCalc, String Idcuenta, BigDecimal IvaIo, BigDecimal IvaIoCalc, BigDecimal efectivo,String referencia, int diasVencidos, BigDecimal montoVencido, boolean esEntrada, int mov, String saiaux);

    int eliminaTemporal(String sesion, int idorigenp, int idproducto, int idauxiliar);

    void actualizaTemporalReferencia(String sesion, int idorigenp, int idproducto, int idauxiliar, int mov, String referencia);

}
