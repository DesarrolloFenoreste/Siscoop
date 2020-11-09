/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Embeddable
public class WsSiscoopResultadoFinalBancamovilPK implements Serializable {

    @Column(name = "opa")
    private String opa;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "metodo")
    private String metodo;

    public WsSiscoopResultadoFinalBancamovilPK() {
    }

    public WsSiscoopResultadoFinalBancamovilPK(String opa, Date fecha, String metodo) {
        this.opa = opa;
        this.fecha = fecha;
        this.metodo = metodo;
    }

    public String getOpa() {
        return opa;
    }

    public void setOpa(String opa) {
        this.opa = opa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opa != null ? opa.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (metodo != null ? metodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsSiscoopResultadoFinalBancamovilPK)) {
            return false;
        }
        WsSiscoopResultadoFinalBancamovilPK other = (WsSiscoopResultadoFinalBancamovilPK) object;
        if ((this.opa == null && other.opa != null) || (this.opa != null && !this.opa.equals(other.opa))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return !((this.metodo == null && other.metodo != null) || (this.metodo != null && !this.metodo.equals(other.metodo)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.WsSiscoopResultadoFinalBancamovilPK[ opa=" + opa + ", fecha=" + fecha + ", metodo=" + metodo + " ]";
    }

}
