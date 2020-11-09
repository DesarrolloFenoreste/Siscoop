/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.AmortizacionesPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "amortizaciones")
public class AmortizacionesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected AmortizacionesPK amortizacionesPK;
    private Date vence;
    private BigDecimal abono;
    private BigDecimal io;
    private BigDecimal abonopag;
    private BigDecimal iopag;
    private Boolean bonificado;
    private Boolean pagovariable;
    private Boolean todopag;
    private Boolean atiempo;
    private BigDecimal bonificacion;
    private BigDecimal anualidad;
    private Integer diasvencidos;

    public AmortizacionesDTO() {
    }

    public AmortizacionesPK getAmortizacionesPK() {
        return amortizacionesPK;
    }

    public void setAmortizacionesPK(AmortizacionesPK amortizacionesPK) {
        this.amortizacionesPK = amortizacionesPK;
    }

    public Date getVence() {
        return vence;
    }

    public void setVence(Date vence) {
        this.vence = vence;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public BigDecimal getIo() {
        return io;
    }

    public void setIo(BigDecimal io) {
        this.io = io;
    }

    public BigDecimal getAbonopag() {
        return abonopag;
    }

    public void setAbonopag(BigDecimal abonopag) {
        this.abonopag = abonopag;
    }

    public BigDecimal getIopag() {
        return iopag;
    }

    public void setIopag(BigDecimal iopag) {
        this.iopag = iopag;
    }

    public Boolean getBonificado() {
        return bonificado;
    }

    public void setBonificado(Boolean bonificado) {
        this.bonificado = bonificado;
    }

    public Boolean getPagovariable() {
        return pagovariable;
    }

    public void setPagovariable(Boolean pagovariable) {
        this.pagovariable = pagovariable;
    }

    public Boolean getTodopag() {
        return todopag;
    }

    public void setTodopag(Boolean todopag) {
        this.todopag = todopag;
    }

    public Boolean getAtiempo() {
        return atiempo;
    }

    public void setAtiempo(Boolean atiempo) {
        this.atiempo = atiempo;
    }

    public BigDecimal getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(BigDecimal bonificacion) {
        this.bonificacion = bonificacion;
    }

    public BigDecimal getAnualidad() {
        return anualidad;
    }

    public void setAnualidad(BigDecimal anualidad) {
        this.anualidad = anualidad;
    }

    public Integer getDiasvencidos() {
        return diasvencidos;
    }

    public void setDiasvencidos(Integer diasvencidos) {
        this.diasvencidos = diasvencidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.amortizacionesPK);
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
        final AmortizacionesDTO other = (AmortizacionesDTO) obj;
        return Objects.equals(this.amortizacionesPK, other.amortizacionesPK);
    }

    @Override
    public String toString() {
        return "AmortizacionesDTO{" + "amortizacionesPK=" + amortizacionesPK + ", vence=" + vence + ", abono=" + abono + ", io=" + io + ", abonopag=" + abonopag + ", iopag=" + iopag + ", bonificado=" + bonificado + ", pagovariable=" + pagovariable + ", todopag=" + todopag + ", atiempo=" + atiempo + ", bonificacion=" + bonificacion + ", anualidad=" + anualidad + ", diasvencidos=" + diasvencidos + '}';
    }

}
