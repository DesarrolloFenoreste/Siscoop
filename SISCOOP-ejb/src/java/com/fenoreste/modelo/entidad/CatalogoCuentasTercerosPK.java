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
public class CatalogoCuentasTercerosPK implements Serializable {

    @Column(name = "idorigen")
    private int idorigen;
    @Column(name = "idgrupo")
    private int idgrupo;
    @Column(name = "idsocio")
    private int idsocio;
    @Column(name = "idorigenp")
    private int idorigenp;
    @Column(name = "idproducto")
    private int idproducto;
    @Column(name = "idauxiliar")
    private int idauxiliar;

    public CatalogoCuentasTercerosPK() {
    }

    public CatalogoCuentasTercerosPK(int idorigen, int idgrupo, int idsocio, int idorigenp, int idproducto, int idauxiliar) {
        this.idorigen = idorigen;
        this.idgrupo = idgrupo;
        this.idsocio = idsocio;
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
    }

    public int getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(int idorigen) {
        this.idorigen = idorigen;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public int getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(int idsocio) {
        this.idsocio = idsocio;
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
        hash += (int) idorigen;
        hash += (int) idgrupo;
        hash += (int) idsocio;
        hash += (int) idorigenp;
        hash += (int) idproducto;
        hash += (int) idauxiliar;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoCuentasTercerosPK)) {
            return false;
        }
        CatalogoCuentasTercerosPK other = (CatalogoCuentasTercerosPK) object;
        if (this.idorigen != other.idorigen) {
            return false;
        }
        if (this.idgrupo != other.idgrupo) {
            return false;
        }
        if (this.idsocio != other.idsocio) {
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
        return "com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK[ idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + " ]";
    }

}
