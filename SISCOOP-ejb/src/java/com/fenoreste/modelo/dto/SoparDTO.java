/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.SoparPK;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class SoparDTO implements Serializable {
    
    protected SoparPK soparPK;
    private String departamento;
    private String puesto;

    public SoparDTO() {
    }

    public SoparPK getSoparPK() {
        return soparPK;
    }

    public void setSoparPK(SoparPK soparPK) {
        this.soparPK = soparPK;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.soparPK);
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
        final SoparDTO other = (SoparDTO) obj;
        return Objects.equals(this.soparPK, other.soparPK);
    }

    @Override
    public String toString() {
        return "SoparDTO{" + "soparPK=" + soparPK + ", departamento=" + departamento + ", puesto=" + puesto + '}';
    }
    
}
