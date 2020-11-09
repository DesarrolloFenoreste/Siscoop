/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Embeddable
public class ReferenciaspPK implements Serializable {

    @Column(name = "idorigenp")
    private int idorigenp;
    @Column(name = "idproducto")
    private int idproducto;
    @Column(name = "idauxiliar")
    private int idauxiliar;
    @Column(name = "tiporeferencia")
    private int tiporeferencia;
    @Column(name = "idorigenpr")
    private int idorigenpr;
    @Column(name = "idproductor")
    private int idproductor;
    @Column(name = "idauxiliarr")
    private int idauxiliarr;

    public ReferenciaspPK() {
    }

    public ReferenciaspPK(int idorigenp, int idproducto, int idauxiliar, short tiporeferencia) {
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
        this.tiporeferencia = tiporeferencia;
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

    public int getTiporeferencia() {
        return tiporeferencia;
    }

    public void setTiporeferencia(int tiporeferencia) {
        this.tiporeferencia = tiporeferencia;
    }

    public int getIdorigenpr() {
        return idorigenpr;
    }

    public void setIdorigenpr(int idorigenpr) {
        this.idorigenpr = idorigenpr;
    }

    public int getIdproductor() {
        return idproductor;
    }

    public void setIdproductor(int idproductor) {
        this.idproductor = idproductor;
    }

    public int getIdauxiliarr() {
        return idauxiliarr;
    }

    public void setIdauxiliarr(int idauxiliarr) {
        this.idauxiliarr = idauxiliarr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idorigenp;
        hash += (int) idproducto;
        hash += (int) idauxiliar;
        hash += (int) tiporeferencia;
        hash += (int) idorigenpr;
        hash += (int) idproductor;
        hash += (int) idauxiliarr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenciaspPK)) {
            return false;
        }
        ReferenciaspPK other = (ReferenciaspPK) object;
        if (this.idorigenp != other.idorigenp) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        if (this.idauxiliar != other.idauxiliar) {
            return false;
        }
        if (this.tiporeferencia != other.tiporeferencia) {
            return false;
        }
        if (this.idorigenpr != other.idorigenpr) {
            return false;
        }
        if (this.idproductor != other.idproductor) {
            return false;
        }
        return this.idauxiliarr == other.idauxiliarr;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.ReferenciaspPK[ idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", tiporeferencia=" + tiporeferencia + ", idorigenpr=" + idorigenpr + ", idproductor=" + idproductor + ", idauxiliarr=" + idauxiliarr + " ]";
    }

}
