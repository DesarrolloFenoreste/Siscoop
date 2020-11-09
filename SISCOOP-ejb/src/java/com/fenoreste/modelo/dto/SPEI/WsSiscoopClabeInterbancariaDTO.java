/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.SPEI;

import com.fenoreste.modelo.entidad.SPEI.WsSiscoopClabeInterbancariaPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class WsSiscoopClabeInterbancariaDTO implements Serializable {

    protected WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK;
    private String clabe;
    private Date fechaHora;
    private boolean asignada;
    private boolean activa;
    private boolean bloqueada;

    public WsSiscoopClabeInterbancariaDTO() {
    }

    public WsSiscoopClabeInterbancariaDTO(WsSiscoopClabeInterbancariaPK wsSiscoopClabeInterbancariaPK, String clabe, Date fechaHora, boolean asignada, boolean activa, boolean bloqueada) {
        this.wsSiscoopClabeInterbancariaPK = wsSiscoopClabeInterbancariaPK;
        this.clabe = clabe;
        this.fechaHora = fechaHora;
        this.asignada = asignada;
        this.activa = activa;
        this.bloqueada = bloqueada;
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

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.wsSiscoopClabeInterbancariaPK);
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
        final WsSiscoopClabeInterbancariaDTO other = (WsSiscoopClabeInterbancariaDTO) obj;
        return Objects.equals(this.wsSiscoopClabeInterbancariaPK, other.wsSiscoopClabeInterbancariaPK);
    }

    @Override
    public String toString() {
        return "WsSiscoopClabeInterbancariaDTO{" + "wsSiscoopClabeInterbancariaPK=" + wsSiscoopClabeInterbancariaPK + ", clabe=" + clabe + ", fechaHora=" + fechaHora + ", asignada=" + asignada + ", activa=" + activa + ", bloqueada=" + bloqueada + '}';
    }
    
}
