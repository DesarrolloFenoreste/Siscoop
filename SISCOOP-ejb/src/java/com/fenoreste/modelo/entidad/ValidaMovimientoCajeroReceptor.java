/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "valida_movimiento_cajero_receptor")
@XmlRootElement
public class ValidaMovimientoCajeroReceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorPK;
    @Column(name = "txtype")
    private String txtype;
    @Column(name = "datetime")
    private String datetime;
    @Column(name = "amount1")
    private String amount1;
    @Column(name = "amount2")
    private String amount2;
    @Column(name = "estatus")
    private boolean estatus;

    public ValidaMovimientoCajeroReceptor() {
    }

    public ValidaMovimientoCajeroReceptor(ValidaMovimientoCajeroReceptorPK validaMovimientoCajeroReceptorPK) {
        this.validaMovimientoCajeroReceptorPK = validaMovimientoCajeroReceptorPK;
    }

    public ValidaMovimientoCajeroReceptor(String idteller, String idacct, int idoperation) {
        this.validaMovimientoCajeroReceptorPK = new ValidaMovimientoCajeroReceptorPK(idteller, idacct, idoperation);
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

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (validaMovimientoCajeroReceptorPK != null ? validaMovimientoCajeroReceptorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidaMovimientoCajeroReceptor)) {
            return false;
        }
        ValidaMovimientoCajeroReceptor other = (ValidaMovimientoCajeroReceptor) object;
        return !((this.validaMovimientoCajeroReceptorPK == null && other.validaMovimientoCajeroReceptorPK != null) || (this.validaMovimientoCajeroReceptorPK != null && !this.validaMovimientoCajeroReceptorPK.equals(other.validaMovimientoCajeroReceptorPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.ValidaMovimientoCajeroReceptor[ validaMovimientoCajeroReceptorPK=" + validaMovimientoCajeroReceptorPK + " ]";
    }

}
