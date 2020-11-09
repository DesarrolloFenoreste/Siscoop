/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "crpagoserviciodto")
public class CrPagoServicioDTO implements Serializable {

    private String socio;
    private String referencia;
    private BigDecimal monto;
    private Date fechaLimite;
    private Date fechaPago;
    private String numCajero;
    private String idservice;

    public CrPagoServicioDTO() {
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNumCajero() {
        return numCajero;
    }

    public void setNumCajero(String numCajero) {
        this.numCajero = numCajero;
    }

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) {
        this.idservice = idservice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.referencia);
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
        final CrPagoServicioDTO other = (CrPagoServicioDTO) obj;
        return Objects.equals(this.referencia, other.referencia);
    }

    @Override
    public String toString() {
        return "CrPagoServicioDTO{" + "socio=" + socio + ", referencia=" + referencia + ", monto=" + monto + ", fechaLimite=" + fechaLimite + ", fechaPago=" + fechaPago + ", numCajero=" + numCajero + ", idservice=" + idservice + '}';
    }

}
