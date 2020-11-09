/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@Entity
@Table(name = "abono_adelantado_interes")
@XmlRootElement
public class AbonoAdelantadoInteres implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AbonoAdelantadoInteresPK abonoAdelantadoInteresPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "interes", precision = 12, scale = 2)
    private BigDecimal interes;
    @Column(name = "iva_interes", precision = 12, scale = 2)
    private BigDecimal ivaInteres;
    @Column(name = "interes_ap", precision = 12, scale = 2)
    private BigDecimal interesAp;
    @Column(name = "iva_interes_ap", precision = 12, scale = 2)
    private BigDecimal ivaInteresAp;
    @Column(name = "aplicado")
    private Boolean aplicado;
    @Column(name = "estatus_ap")
    private Integer estatusAp;

    public AbonoAdelantadoInteres() {
    }

    public AbonoAdelantadoInteres(AbonoAdelantadoInteresPK abonoAdelantadoInteresPK) {
        this.abonoAdelantadoInteresPK = abonoAdelantadoInteresPK;
    }

    public AbonoAdelantadoInteres(int idorigenpP, int idproductoP, int idauxiliarP, int idorigenpA, int idproductoA, int idauxiliarA, Date fechaMov, Date fecha) {
        this.abonoAdelantadoInteresPK = new AbonoAdelantadoInteresPK(idorigenpP, idproductoP, idauxiliarP, idorigenpA, idproductoA, idauxiliarA, fechaMov, fecha);
    }

    public AbonoAdelantadoInteresPK getAbonoAdelantadoInteresPK() {
        return abonoAdelantadoInteresPK;
    }

    public void setAbonoAdelantadoInteresPK(AbonoAdelantadoInteresPK abonoAdelantadoInteresPK) {
        this.abonoAdelantadoInteresPK = abonoAdelantadoInteresPK;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getIvaInteres() {
        return ivaInteres;
    }

    public void setIvaInteres(BigDecimal ivaInteres) {
        this.ivaInteres = ivaInteres;
    }

    public BigDecimal getInteresAp() {
        return interesAp;
    }

    public void setInteresAp(BigDecimal interesAp) {
        this.interesAp = interesAp;
    }

    public BigDecimal getIvaInteresAp() {
        return ivaInteresAp;
    }

    public void setIvaInteresAp(BigDecimal ivaInteresAp) {
        this.ivaInteresAp = ivaInteresAp;
    }

    public Boolean getAplicado() {
        return aplicado;
    }

    public void setAplicado(Boolean aplicado) {
        this.aplicado = aplicado;
    }

    public Integer getEstatusAp() {
        return estatusAp;
    }

    public void setEstatusAp(Integer estatusAp) {
        this.estatusAp = estatusAp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abonoAdelantadoInteresPK != null ? abonoAdelantadoInteresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoAdelantadoInteres)) {
            return false;
        }
        AbonoAdelantadoInteres other = (AbonoAdelantadoInteres) object;
        if ((this.abonoAdelantadoInteresPK == null && other.abonoAdelantadoInteresPK != null) || (this.abonoAdelantadoInteresPK != null && !this.abonoAdelantadoInteresPK.equals(other.abonoAdelantadoInteresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.AbonoAdelantadoInteres[ abonoAdelantadoInteresPK=" + abonoAdelantadoInteresPK + " ]";
    }
    
}
