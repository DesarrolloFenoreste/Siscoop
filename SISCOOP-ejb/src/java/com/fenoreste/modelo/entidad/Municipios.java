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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "municipios")
@XmlRootElement
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio")
    private Integer idmunicipio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "poblacion")
    private Integer poblacion;
    @Column(name = "localidad_siti")
    private Integer localidadSiti;
    @Column(name = "de_cp")
    private String deCp;
    @Column(name = "a_cp")
    private String aCp;
    @Column(name = "idestado")
    private Integer idestado;

    public Municipios() {
    }

    public Municipios(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Municipios(Integer idmunicipio, String nombre) {
        this.idmunicipio = idmunicipio;
        this.nombre = nombre;
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

    public String getACp() {
        return aCp;
    }

    public void setACp(String aCp) {
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
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        return !((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Municipios[ idmunicipio=" + idmunicipio + " ]";
    }

}
