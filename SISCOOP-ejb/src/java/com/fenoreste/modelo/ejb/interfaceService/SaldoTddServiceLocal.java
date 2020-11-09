/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceService;

import com.fenoreste.modelo.dto.tdd.SaldoTddDTO;
import com.fenoreste.modelo.entidad.SaldoTddPK;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author gerardo
 */
@Local
public interface SaldoTddServiceLocal {

    SaldoTddDTO buscaSaldoTDD(SaldoTddPK opa);

    int insertaSaldoTdd(SaldoTddPK opa, BigDecimal saldo, Date fecha);

    int actualizaSaldoTdd(SaldoTddPK opa, BigDecimal saldo, Date fecha);

}
