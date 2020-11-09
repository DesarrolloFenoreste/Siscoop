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
public class SoparPK implements Serializable {

    @Column(name = "idorigen")
    private int idorigen;
    @Column(name = "idgrupo")
    private int idgrupo;
    @Column(name = "idsocio")
    private int idsocio;
    @Column(name = "idusuario")
    private int idusuario;
    @Column(name = "tipo")
    private String tipo;

    public SoparPK() {
    }

    public SoparPK(int idorigen, int idgrupo, int idsocio, int idusuario, String tipo) {
        this.idorigen = idorigen;
        this.idgrupo = idgrupo;
        this.idsocio = idsocio;
        this.idusuario = idusuario;
        this.tipo = tipo;
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

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idorigen;
        hash += (int) idgrupo;
        hash += (int) idsocio;
        hash += (int) idusuario;
        hash += (tipo != null ? tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoparPK)) {
            return false;
        }
        SoparPK other = (SoparPK) object;
        if (this.idorigen != other.idorigen) {
            return false;
        }
        if (this.idgrupo != other.idgrupo) {
            return false;
        }
        if (this.idsocio != other.idsocio) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return !((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SoparPK[ idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", idusuario=" + idusuario + ", tipo=" + tipo + " ]";
    }

}
