/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad.SPEI;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gerardo
 */
@Embeddable
public class WsSiscoopClabeInterbancariaHPK implements Serializable {

    @Column(name = "idorigenp", nullable = false)
    private int idorigenp;
    @Column(name = "idproducto", nullable = false)
    private int idproducto;
    @Column(name = "idauxiliar", nullable = false)
    private int idauxiliar;
    @Column(name = "clabe")
    private String clabe;

    public WsSiscoopClabeInterbancariaHPK() {
    }

    public WsSiscoopClabeInterbancariaHPK(int idorigenp, int idproducto, int idauxiliar, String clabe) {
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
        this.clabe = clabe;
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

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idorigenp;
        hash += (int) idproducto;
        hash += (int) idauxiliar;
        hash += (clabe != null ? clabe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsSiscoopClabeInterbancariaHPK)) {
            return false;
        }
        WsSiscoopClabeInterbancariaHPK other = (WsSiscoopClabeInterbancariaHPK) object;
        if (this.idorigenp != other.idorigenp) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        if (this.idauxiliar != other.idauxiliar) {
            return false;
        }
        return !((this.clabe == null && other.clabe != null) || (this.clabe != null && !this.clabe.equals(other.clabe)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancariaHPK[ idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", clabe=" + clabe + " ]";
    }
    
}
