/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "cataccttype")
@XmlRootElement
public class Cataccttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idaccttype")
    private String idaccttype;
    @Column(name = "description")
    private String description;

    public Cataccttype() {
    }

    public Cataccttype(String idaccttype) {
        this.idaccttype = idaccttype;
    }

    public Cataccttype(String idaccttype, String description) {
        this.idaccttype = idaccttype;
        this.description = description;
    }

    public String getIdaccttype() {
        return idaccttype;
    }

    public void setIdaccttype(String idaccttype) {
        this.idaccttype = idaccttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaccttype != null ? idaccttype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cataccttype)) {
            return false;
        }
        Cataccttype other = (Cataccttype) object;
        return !((this.idaccttype == null && other.idaccttype != null) || (this.idaccttype != null && !this.idaccttype.equals(other.idaccttype)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Cataccttype[ idaccttype=" + idaccttype + " ]";
    }

}
