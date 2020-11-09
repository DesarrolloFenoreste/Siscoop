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
@Table(name = "ws_siscoop_clabe_interbancaria_h")
@XmlRootElement
public class WsSiscoopClabeInterbancariaH implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;


    public WsSiscoopClabeInterbancariaH() {
    }

    public WsSiscoopClabeInterbancariaH(WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK) {
        this.wsSiscoopClabeInterbancariaHPK = wsSiscoopClabeInterbancariaHPK;
    }

    public WsSiscoopClabeInterbancariaH(WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK, Date fechaHora) {
        this.wsSiscoopClabeInterbancariaHPK = wsSiscoopClabeInterbancariaHPK;
        this.fechaHora = fechaHora;
    }

    public WsSiscoopClabeInterbancariaH(int idorigenp, int idproducto, int idauxiliar, String clabe) {
        this.wsSiscoopClabeInterbancariaHPK = new WsSiscoopClabeInterbancariaHPK(idorigenp, idproducto, idauxiliar, clabe);
    }

    public WsSiscoopClabeInterbancariaHPK getWsSiscoopClabeInterbancariaHPK() {
        return wsSiscoopClabeInterbancariaHPK;
    }

    public void setWsSiscoopClabeInterbancariaHPK(WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK) {
        this.wsSiscoopClabeInterbancariaHPK = wsSiscoopClabeInterbancariaHPK;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wsSiscoopClabeInterbancariaHPK != null ? wsSiscoopClabeInterbancariaHPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsSiscoopClabeInterbancariaH)) {
            return false;
        }
        WsSiscoopClabeInterbancariaH other = (WsSiscoopClabeInterbancariaH) object;
        return !((this.wsSiscoopClabeInterbancariaHPK == null && other.wsSiscoopClabeInterbancariaHPK != null) || (this.wsSiscoopClabeInterbancariaHPK != null && !this.wsSiscoopClabeInterbancariaHPK.equals(other.wsSiscoopClabeInterbancariaHPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancariaH[ wsSiscoopClabeInterbancariaHPK=" + wsSiscoopClabeInterbancariaHPK + " ]";
    }
    
}
