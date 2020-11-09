/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@XmlRootElement
public class SaiSeguroHipotecario implements Serializable {

    @EmbeddedId
    protected SaiSeguroHipotecarioPK saiSeguroHipotecarioPK;
    @Column(name = "tasa_iva")
    private double tasa_iva;
    @Column(name = "seguro")
    private double seguro;
    @Column(name = "ivaseguro")
    private double ivaseguro;
    @Column(name = "pagado")
    private double pagado;
    @Column(name = "ivapagado")
    private double ivapagado;
    @Column(name = "apagar")
    private double apagar;
    @Column(name = "ivaapagar")
    private double ivaapagar;

    public SaiSeguroHipotecario() {
    }

    public SaiSeguroHipotecario(SaiSeguroHipotecarioPK saiSeguroHipotecarioPK) {
        this.saiSeguroHipotecarioPK = saiSeguroHipotecarioPK;
    }

    public SaiSeguroHipotecarioPK getSaiSeguroHipotecarioPK() {
        return saiSeguroHipotecarioPK;
    }

    public void setSaiSeguroHipotecarioPK(SaiSeguroHipotecarioPK saiSeguroHipotecarioPK) {
        this.saiSeguroHipotecarioPK = saiSeguroHipotecarioPK;
    }

    public double getTasa_iva() {
        return tasa_iva;
    }

    public void setTasa_iva(double tasa_iva) {
        this.tasa_iva = tasa_iva;
    }

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public double getIvaseguro() {
        return ivaseguro;
    }

    public void setIvaseguro(double ivaseguro) {
        this.ivaseguro = ivaseguro;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getIvapagado() {
        return ivapagado;
    }

    public void setIvapagado(double ivapagado) {
        this.ivapagado = ivapagado;
    }

    public double getApagar() {
        return apagar;
    }

    public void setApagar(double apagar) {
        this.apagar = apagar;
    }

    public double getIvaapagar() {
        return ivaapagar;
    }

    public void setIvaapagar(double ivaapagar) {
        this.ivaapagar = ivaapagar;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.saiSeguroHipotecarioPK);
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
        final SaiSeguroHipotecario other = (SaiSeguroHipotecario) obj;
        return Objects.equals(this.saiSeguroHipotecarioPK, other.saiSeguroHipotecarioPK);
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SaiSeguroHipotecario[ saiSeguroHipotecarioPK=" + saiSeguroHipotecarioPK + " ]";
    }

}
