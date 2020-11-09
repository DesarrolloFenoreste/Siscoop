/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.tdd;

import com.fenoreste.modelo.entidad.SaldoTddPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "saldoTdd")
public class SaldoTddDTO implements Serializable {

    protected SaldoTddPK saldoTddPK;
    private BigDecimal saldo;
    private Date fecha;

    public SaldoTddDTO() {
    }

    public SaldoTddPK getSaldoTddPK() {
        return saldoTddPK;
    }

    public void setSaldoTddPK(SaldoTddPK saldoTddPK) {
        this.saldoTddPK = saldoTddPK;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.saldoTddPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaldoTddDTO other = (SaldoTddDTO) obj;
        return Objects.equals(this.saldoTddPK, other.saldoTddPK);
    }

    @Override
    public String toString() {
        return "SaldoTddDTO{" + "saldoTddPK=" + saldoTddPK + ", saldo=" + saldo + ", fecha=" + fecha + '}';
    }

}
