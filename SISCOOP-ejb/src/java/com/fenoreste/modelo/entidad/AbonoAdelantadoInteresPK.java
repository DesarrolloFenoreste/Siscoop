/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author prometeo
 */
@Embeddable
public class AbonoAdelantadoInteresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idorigenp_p", nullable = false)
    private int idorigenpP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto_p", nullable = false)
    private int idproductoP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idauxiliar_p", nullable = false)
    private int idauxiliarP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idorigenp_a", nullable = false)
    private int idorigenpA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto_a", nullable = false)
    private int idproductoA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idauxiliar_a", nullable = false)
    private int idauxiliarA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_mov", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public AbonoAdelantadoInteresPK() {
    }

    public AbonoAdelantadoInteresPK(int idorigenpP, int idproductoP, int idauxiliarP, int idorigenpA, int idproductoA, int idauxiliarA, Date fechaMov, Date fecha) {
        this.idorigenpP = idorigenpP;
        this.idproductoP = idproductoP;
        this.idauxiliarP = idauxiliarP;
        this.idorigenpA = idorigenpA;
        this.idproductoA = idproductoA;
        this.idauxiliarA = idauxiliarA;
        this.fechaMov = fechaMov;
        this.fecha = fecha;
    }

    public int getIdorigenpP() {
        return idorigenpP;
    }

    public void setIdorigenpP(int idorigenpP) {
        this.idorigenpP = idorigenpP;
    }

    public int getIdproductoP() {
        return idproductoP;
    }

    public void setIdproductoP(int idproductoP) {
        this.idproductoP = idproductoP;
    }

    public int getIdauxiliarP() {
        return idauxiliarP;
    }

    public void setIdauxiliarP(int idauxiliarP) {
        this.idauxiliarP = idauxiliarP;
    }

    public int getIdorigenpA() {
        return idorigenpA;
    }

    public void setIdorigenpA(int idorigenpA) {
        this.idorigenpA = idorigenpA;
    }

    public int getIdproductoA() {
        return idproductoA;
    }

    public void setIdproductoA(int idproductoA) {
        this.idproductoA = idproductoA;
    }

    public int getIdauxiliarA() {
        return idauxiliarA;
    }

    public void setIdauxiliarA(int idauxiliarA) {
        this.idauxiliarA = idauxiliarA;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
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
        hash += (int) idorigenpP;
        hash += (int) idproductoP;
        hash += (int) idauxiliarP;
        hash += (int) idorigenpA;
        hash += (int) idproductoA;
        hash += (int) idauxiliarA;
        hash += (fechaMov != null ? fechaMov.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoAdelantadoInteresPK)) {
            return false;
        }
        AbonoAdelantadoInteresPK other = (AbonoAdelantadoInteresPK) object;
        if (this.idorigenpP != other.idorigenpP) {
            return false;
        }
        if (this.idproductoP != other.idproductoP) {
            return false;
        }
        if (this.idauxiliarP != other.idauxiliarP) {
            return false;
        }
        if (this.idorigenpA != other.idorigenpA) {
            return false;
        }
        if (this.idproductoA != other.idproductoA) {
            return false;
        }
        if (this.idauxiliarA != other.idauxiliarA) {
            return false;
        }
        if ((this.fechaMov == null && other.fechaMov != null) || (this.fechaMov != null && !this.fechaMov.equals(other.fechaMov))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.AbonoAdelantadoInteresPK[ idorigenpP=" + idorigenpP + ", idproductoP=" + idproductoP + ", idauxiliarP=" + idauxiliarP + ", idorigenpA=" + idorigenpA + ", idproductoA=" + idproductoA + ", idauxiliarA=" + idauxiliarA + ", fechaMov=" + fechaMov + ", fecha=" + fecha + " ]";
    }
    
}
