/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "sopar")
@XmlRootElement
public class Sopar implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected SoparPK soparPK;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "puesto")
    private String puesto;

    public Sopar() {
    }

    public Sopar(SoparPK soparPK) {
        this.soparPK = soparPK;
    }

    public Sopar(int idorigen, int idgrupo, int idsocio, int idusuario, String tipo) {
        this.soparPK = new SoparPK(idorigen, idgrupo, idsocio, idusuario, tipo);
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
        int hash = 0;
        hash += (soparPK != null ? soparPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sopar)) {
            return false;
        }
        Sopar other = (Sopar) object;
        return !((this.soparPK == null && other.soparPK != null) || (this.soparPK != null && !this.soparPK.equals(other.soparPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Sopar[ soparPK=" + soparPK + " ]";
    }

}
