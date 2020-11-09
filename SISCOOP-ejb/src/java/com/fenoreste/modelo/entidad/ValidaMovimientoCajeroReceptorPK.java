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
public class ValidaMovimientoCajeroReceptorPK implements Serializable {

    @Column(name = "idteller")
    private String idteller;
    @Column(name = "idacct")
    private String idacct;
    @Column(name = "idoperation")
    private int idoperation;

    public ValidaMovimientoCajeroReceptorPK() {
    }

    public ValidaMovimientoCajeroReceptorPK(String idteller, String idacct, int idoperation) {
        this.idteller = idteller;
        this.idacct = idacct;
        this.idoperation = idoperation;
    }

    public String getIdteller() {
        return idteller;
    }

    public void setIdteller(String idteller) {
        this.idteller = idteller;
    }

    public String getIdacct() {
        return idacct;
    }

    public void setIdacct(String idacct) {
        this.idacct = idacct;
    }

    public int getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(int idoperation) {
        this.idoperation = idoperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idteller != null ? idteller.hashCode() : 0);
        hash += (idacct != null ? idacct.hashCode() : 0);
        hash += (int) idoperation;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidaMovimientoCajeroReceptorPK)) {
            return false;
        }
        ValidaMovimientoCajeroReceptorPK other = (ValidaMovimientoCajeroReceptorPK) object;
        if ((this.idteller == null && other.idteller != null) || (this.idteller != null && !this.idteller.equals(other.idteller))) {
            return false;
        }
        if ((this.idacct == null && other.idacct != null) || (this.idacct != null && !this.idacct.equals(other.idacct))) {
            return false;
        }
        return this.idoperation == other.idoperation;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.ValidaMovimientoCajeroReceptorPK[ idteller=" + idteller + ", idacct=" + idacct + ", idoperation=" + idoperation + " ]";
    }

}
