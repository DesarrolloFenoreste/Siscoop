/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.Municipios;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class EstadosDTO implements Serializable {

    private Integer idestado;
    private String nombre;
    private List<Municipios> municipiosList;

    public EstadosDTO() {
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idestado);
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
        final EstadosDTO other = (EstadosDTO) obj;
        return Objects.equals(this.idestado, other.idestado);
    }

    @Override
    public String toString() {
        return "EstadosDTO{" + "idestado=" + idestado + ", nombre=" + nombre + ", municipiosList=" + municipiosList + '}';
    }
    
}
