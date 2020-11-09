/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class MunicipiosDTO implements Serializable {

    private Integer idmunicipio;
    private String nombre;
    private Integer poblacion;
    private Integer localidadSiti;
    private String deCp;
    private String aCp;
    private Integer idestado;

    public MunicipiosDTO() {
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Integer poblacion) {
        this.poblacion = poblacion;
    }

    public Integer getLocalidadSiti() {
        return localidadSiti;
    }

    public void setLocalidadSiti(Integer localidadSiti) {
        this.localidadSiti = localidadSiti;
    }

    public String getDeCp() {
        return deCp;
    }

    public void setDeCp(String deCp) {
        this.deCp = deCp;
    }

    public String getaCp() {
        return aCp;
    }

    public void setaCp(String aCp) {
        this.aCp = aCp;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idmunicipio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MunicipiosDTO other = (MunicipiosDTO) obj;
        return Objects.equals(this.idmunicipio, other.idmunicipio);
    }

    @Override
    public String toString() {
        return "MunicipiosDTO{" + "idmunicipio=" + idmunicipio + ", nombre=" + nombre + ", poblacion=" + poblacion + ", localidadSiti=" + localidadSiti + ", deCp=" + deCp + ", aCp=" + aCp + ", idestado=" + idestado + '}';
    }
    
    
}
