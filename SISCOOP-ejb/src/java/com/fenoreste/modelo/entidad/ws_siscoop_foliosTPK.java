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
 * @author wilmer
 */
@Cacheable(false)
@Embeddable
public class ws_siscoop_foliosTPK implements Serializable {

    @Column(name = "idtarjeta")
    private String idtarjeta;

    public ws_siscoop_foliosTPK(){
    }  

    public ws_siscoop_foliosTPK(String idtarjeta) {
        this.idtarjeta = idtarjeta;
    }

    public String getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta(String idtarjeta) {
        this.idtarjeta = idtarjeta;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarjeta != null ? idtarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ws_siscoop_foliosTPK)) {
            return false;
        }
        ws_siscoop_foliosTPK other = (ws_siscoop_foliosTPK) object;
        if ((this.idtarjeta == null && other.idtarjeta != null) || (this.idtarjeta != null && !this.idtarjeta.equals(other.idtarjeta))) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.ws_siscoop_foliosTPK[ idtarjeta=" + idtarjeta + " ]";
    }
}
