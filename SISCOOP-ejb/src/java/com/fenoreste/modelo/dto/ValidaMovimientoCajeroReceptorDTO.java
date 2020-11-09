/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.ValidaMovimientoCajeroReceptorPK;
import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "validaMovimientoCajeroReceptorDTO")
public class ValidaMovimientoCajeroReceptorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorPK;
    private String txtype;
    private String datetime;
    private String amount1;
    private String amount2;
    private boolean estatus;

    public ValidaMovimientoCajeroReceptorDTO() {
    }

    public ValidaMovimientoCajeroReceptorPK getValidaMovimientoCajeroReceptorPK() {
        return validaMovimientoCajeroReceptorPK;
    }

    public void setValidaMovimientoCajeroReceptorPK(ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorPK) {
        this.validaMovimientoCajeroReceptorPK = validaMovimientoCajeroReceptorPK;
    }

    public String getTxtype() {
        return txtype;
    }

    public void setTxtype(String txtype) {
        this.txtype = txtype;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.validaMovimientoCajeroReceptorPK);
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
        final ValidaMovimientoCajeroReceptorDTO other = (ValidaMovimientoCajeroReceptorDTO) obj;
        return Objects.equals(this.validaMovimientoCajeroReceptorPK, other.validaMovimientoCajeroReceptorPK);
    }

    @Override
    public String toString() {
        return "ValidaMovimientoCajeroReceptorDTO{" + "validaMovimientoCajeroReceptorPK=" + validaMovimientoCajeroReceptorPK + ", txtype=" + txtype + ", datetime=" + datetime + ", amount1=" + amount1 + ", amount2=" + amount2 + ", estatus=" + estatus + '}';
    }

}
