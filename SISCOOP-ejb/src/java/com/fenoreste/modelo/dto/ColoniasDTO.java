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
public class ColoniasDTO implements Serializable {

    private Integer idcolonia;
    private String nombre;
    private Integer idmunicipio;
    private String codigopostal;

    public ColoniasDTO() {
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idcolonia);
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
        final ColoniasDTO other = (ColoniasDTO) obj;
        return Objects.equals(this.idcolonia, other.idcolonia);
    }

    @Override
    public String toString() {
        return "ColoniasDTO{" + "idcolonia=" + idcolonia + ", nombre=" + nombre + ", idmunicipio=" + idmunicipio + ", codigopostal=" + codigopostal + '}';
    }
    
}
