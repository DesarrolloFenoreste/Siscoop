/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "referenciasp")
@XmlRootElement
public class Referenciasp implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ReferenciaspPK referenciaspPK;
    @Column(name = "referencia")
    private String referencia;

    public Referenciasp() {
    }

    public Referenciasp(ReferenciaspPK referenciaspPK) {
        this.referenciaspPK = referenciaspPK;
    }

    public ReferenciaspPK getReferenciaspPK() {
        return referenciaspPK;
    }

    public void setReferenciaspPK(ReferenciaspPK referenciaspPK) {
        this.referenciaspPK = referenciaspPK;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referenciaspPK != null ? referenciaspPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referenciasp)) {
            return false;
        }
        Referenciasp other = (Referenciasp) object;
        return !((this.referenciaspPK == null && other.referenciaspPK != null) || (this.referenciaspPK != null && !this.referenciaspPK.equals(other.referenciaspPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Referenciasp[ referenciaspPK=" + referenciaspPK + " ]";
    }

}
