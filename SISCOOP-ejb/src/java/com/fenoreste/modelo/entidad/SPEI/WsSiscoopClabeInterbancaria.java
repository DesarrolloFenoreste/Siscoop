/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad.SPEI;

import java.io.Serializable;
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
@Table(name = "ws_siscoop_clabe_interbancaria")
@XmlRootElement
public class WsSiscoopClabeInterbancaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK;
    @Column(name = "clabe")
    private String clabe;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Column(name = "asignada")
    private boolean asignada;
    @Column(name = "activa")
    private boolean activa;
    @Column(name = "bloqueada")
    private boolean bloqueada;

    public WsSiscoopClabeInterbancaria() {
    }

    public WsSiscoopClabeInterbancaria(WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK) {
        this.wsSiscoopClabeInterbancariaPK = wsSiscoopClabeInterbancariaPK;
    }

    public WsSiscoopClabeInterbancaria(WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK, String clabe, Date fechaHora, boolean asignada, boolean activa, boolean bloqueada) {
        this.wsSiscoopClabeInterbancariaPK = wsSiscoopClabeInterbancariaPK;
        this.clabe = clabe;
        this.fechaHora = fechaHora;
        this.asignada = asignada;
        this.activa = activa;
        this.bloqueada = bloqueada;
    }

    public WsSiscoopClabeInterbancaria(int idorigenp, int idproducto, int idauxiliar) {
        this.wsSiscoopClabeInterbancariaPK = new WsSiscoopClabeInterbancariaPK(idorigenp, idproducto, idauxiliar);
    }

    public WsSiscoopClabeInterbancariaPK getWsSiscoopClabeInterbancariaPK() {
        return wsSiscoopClabeInterbancariaPK;
    }

    public void setWsSiscoopClabeInterbancariaPK(WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK) {
        this.wsSiscoopClabeInterbancariaPK = wsSiscoopClabeInterbancariaPK;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean getAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wsSiscoopClabeInterbancariaPK != null ? wsSiscoopClabeInterbancariaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsSiscoopClabeInterbancaria)) {
            return false;
        }
        WsSiscoopClabeInterbancaria other = (WsSiscoopClabeInterbancaria) object;
        return !((this.wsSiscoopClabeInterbancariaPK == null && other.wsSiscoopClabeInterbancariaPK != null) || (this.wsSiscoopClabeInterbancariaPK != null && !this.wsSiscoopClabeInterbancariaPK.equals(other.wsSiscoopClabeInterbancariaPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancaria[ wsSiscoopClabeInterbancariaPK=" + wsSiscoopClabeInterbancariaPK + " ]";
    }

}
