/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.SPEI;

import com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancariaHPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class WsSiscoopClabeInterbancariaHDTO implements Serializable {

    protected WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK;
    private Date fechaHora;

    public WsSiscoopClabeInterbancariaHDTO() {
    }

    public WsSiscoopClabeInterbancariaHDTO(WsSiscoopClabeInterbancariaHPK wsSiscoopClabeInterbancariaHPK, Date fechaHora) {
        this.wsSiscoopClabeInterbancariaHPK = wsSiscoopClabeInterbancariaHPK;
        this.fechaHora = fechaHora;
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
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.wsSiscoopClabeInterbancariaHPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WsSiscoopClabeInterbancariaHDTO other = (WsSiscoopClabeInterbancariaHDTO) obj;
        return Objects.equals(this.wsSiscoopClabeInterbancariaHPK, other.wsSiscoopClabeInterbancariaHPK);
    }

    @Override
    public String toString() {
        return "WsSiscoopClabeInterbancariaHDTO{" + "wsSiscoopClabeInterbancariaHPK=" + wsSiscoopClabeInterbancariaHPK + ", fechaHora=" + fechaHora + '}';
    }

}
