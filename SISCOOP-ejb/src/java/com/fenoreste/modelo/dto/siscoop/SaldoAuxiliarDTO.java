/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "saldoauxilairdto")
public class SaldoAuxiliarDTO implements Serializable {

    private BigDecimal saldo;
    private BigDecimal saldoDisponible;

    public SaldoAuxiliarDTO() {
    }

    public SaldoAuxiliarDTO(BigDecimal saldo, BigDecimal saldoDisponible) {
        this.saldo = saldo;
        this.saldoDisponible = saldoDisponible;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.saldo);
        hash = 37 * hash + Objects.hashCode(this.saldoDisponible);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaldoAuxiliarDTO other = (SaldoAuxiliarDTO) obj;
        if (!Objects.equals(this.saldo, other.saldo)) {
            return false;
        }
        return Objects.equals(this.saldoDisponible, other.saldoDisponible);
    }

    @Override
    public String toString() {
        return "SaldoAuxiliarDTO{" + "saldo=" + saldo + ", saldoDisponible=" + saldoDisponible + '}';
    }

}
