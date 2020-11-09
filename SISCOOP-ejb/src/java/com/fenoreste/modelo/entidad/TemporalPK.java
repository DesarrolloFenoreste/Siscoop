/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Embeddable
public class TemporalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sesion", nullable = false, length = 20)
    private String sesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idorigenp", nullable = false)
    private int idorigenp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto", nullable = false)
    private int idproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idauxiliar", nullable = false)
    private int idauxiliar;

    public TemporalPK() {
    }

    public TemporalPK(String sesion, int idorigenp, int idproducto, int idauxiliar) {
        this.sesion = sesion;
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public int getIdorigenp() {
        return idorigenp;
    }

    public void setIdorigenp(int idorigenp) {
        this.idorigenp = idorigenp;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdauxiliar() {
        return idauxiliar;
    }

    public void setIdauxiliar(int idauxiliar) {
        this.idauxiliar = idauxiliar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sesion != null ? sesion.hashCode() : 0);
        hash += (int) idorigenp;
        hash += (int) idproducto;
        hash += (int) idauxiliar;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemporalPK)) {
            return false;
        }
        TemporalPK other = (TemporalPK) object;
        if ((this.sesion == null && other.sesion != null) || (this.sesion != null && !this.sesion.equals(other.sesion))) {
            return false;
        }
        if (this.idorigenp != other.idorigenp) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        return this.idauxiliar == other.idauxiliar;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.TemporalPK[ sesion=" + sesion + ", idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + " ]";
    }

}
