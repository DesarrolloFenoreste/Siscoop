/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "saldo_tdd")
@XmlRootElement
public class SaldoTdd implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected SaldoTddPK saldoTddPK;
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public SaldoTdd() {
    }

    public SaldoTdd(SaldoTddPK saldoTddPK) {
        this.saldoTddPK = saldoTddPK;
    }

    public SaldoTdd(int idorigenp, int idproducto, int idauxiliar) {
        this.saldoTddPK = new SaldoTddPK(idorigenp, idproducto, idauxiliar);
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
        int hash = 0;
        hash += (saldoTddPK != null ? saldoTddPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaldoTdd)) {
            return false;
        }
        SaldoTdd other = (SaldoTdd) object;
        return !((this.saldoTddPK == null && other.saldoTddPK != null) || (this.saldoTddPK != null && !this.saldoTddPK.equals(other.saldoTddPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SaldoTdd[ saldoTddPK=" + saldoTddPK + " ]";
    }

}
