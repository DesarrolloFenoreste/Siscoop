/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.SPEI;

import java.io.Serializable;

/**
 *
 * @author gerardo
 */
public class CuentaToOpaDTO implements Serializable {

    private int idorigenp;
    private int idproducto;
    private int idauxiliar;
    private String cuenta;
    private boolean asignada;
    private boolean activa;
    private boolean bloqueada;
    

    public CuentaToOpaDTO() {
    }

    public CuentaToOpaDTO(int idorigenp, int idproducto, int idauxiliar, String cuenta, boolean activa) {
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
        this.cuenta = cuenta;
        this.activa = activa;
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

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
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
        int hash = 5;
        hash = 89 * hash + this.idorigenp;
        hash = 89 * hash + this.idproducto;
        hash = 89 * hash + this.idauxiliar;
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
        final CuentaToOpaDTO other = (CuentaToOpaDTO) obj;
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
        return "CuentaToOpaDTO{" + "idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", cuenta=" + cuenta + ", asignada=" + asignada + ", activa=" + activa + ", bloqueada=" + bloqueada + '}';
    }
    
}
