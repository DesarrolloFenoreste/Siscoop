/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.AuxiliaresDPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "auxiliaresD")
public class AuxiliaresdDTO implements Serializable {

    protected AuxiliaresDPK auxiliaresDPK;
    private Short cargoabono;
    private BigDecimal monto;
    private BigDecimal montoio;
    private BigDecimal montoim;
    private BigDecimal montoiva;
    private Integer idorigenc;
    private String periodo;
    private Short idtipo;
    private Integer idpoliza;
    private Short tipomov;
    private BigDecimal saldoec;
    private Integer transaccion;
    private BigDecimal montoivaim;
    private BigDecimal efectivo;
    private int diasvencidos;
    private BigDecimal montovencido;
    private Integer ticket;
    private BigDecimal montoidnc;
    private BigDecimal montoieco;
    private BigDecimal montoidncm;
    private BigDecimal montoiecom;

    public AuxiliaresdDTO() {
    }

    public AuxiliaresDPK getAuxiliaresDPK() {
        return auxiliaresDPK;
    }

    public void setAuxiliaresDPK(AuxiliaresDPK auxiliaresDPK) {
        this.auxiliaresDPK = auxiliaresDPK;
    }

    public Short getCargoabono() {
        return cargoabono;
    }

    public void setCargoabono(Short cargoabono) {
        this.cargoabono = cargoabono;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getMontoio() {
        return montoio;
    }

    public void setMontoio(BigDecimal montoio) {
        this.montoio = montoio;
    }

    public BigDecimal getMontoim() {
        return montoim;
    }

    public void setMontoim(BigDecimal montoim) {
        this.montoim = montoim;
    }

    public BigDecimal getMontoiva() {
        return montoiva;
    }

    public void setMontoiva(BigDecimal montoiva) {
        this.montoiva = montoiva;
    }

    public Integer getIdorigenc() {
        return idorigenc;
    }

    public void setIdorigenc(Integer idorigenc) {
        this.idorigenc = idorigenc;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Short getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Short idtipo) {
        this.idtipo = idtipo;
    }

    public Integer getIdpoliza() {
        return idpoliza;
    }

    public void setIdpoliza(Integer idpoliza) {
        this.idpoliza = idpoliza;
    }

    public Short getTipomov() {
        return tipomov;
    }

    public void setTipomov(Short tipomov) {
        this.tipomov = tipomov;
    }

    public BigDecimal getSaldoec() {
        return saldoec;
    }

    public void setSaldoec(BigDecimal saldoec) {
        this.saldoec = saldoec;
    }

    public Integer getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public BigDecimal getMontoivaim() {
        return montoivaim;
    }

    public void setMontoivaim(BigDecimal montoivaim) {
        this.montoivaim = montoivaim;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public int getDiasvencidos() {
        return diasvencidos;
    }

    public void setDiasvencidos(int diasvencidos) {
        this.diasvencidos = diasvencidos;
    }

    public BigDecimal getMontovencido() {
        return montovencido;
    }

    public void setMontovencido(BigDecimal montovencido) {
        this.montovencido = montovencido;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public BigDecimal getMontoidnc() {
        return montoidnc;
    }

    public void setMontoidnc(BigDecimal montoidnc) {
        this.montoidnc = montoidnc;
    }

    public BigDecimal getMontoieco() {
        return montoieco;
    }

    public void setMontoieco(BigDecimal montoieco) {
        this.montoieco = montoieco;
    }

    public BigDecimal getMontoidncm() {
        return montoidncm;
    }

    public void setMontoidncm(BigDecimal montoidncm) {
        this.montoidncm = montoidncm;
    }

    public BigDecimal getMontoiecom() {
        return montoiecom;
    }

    public void setMontoiecom(BigDecimal montoiecom) {
        this.montoiecom = montoiecom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.auxiliaresDPK);
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
        final AuxiliaresdDTO other = (AuxiliaresdDTO) obj;
        return Objects.equals(this.auxiliaresDPK, other.auxiliaresDPK);
    }

    @Override
    public String toString() {
        return "AuxiliaresdDTO{" + "auxiliaresDPK=" + auxiliaresDPK + ", cargoabono=" + cargoabono + ", monto=" + monto + ", montoio=" + montoio + ", montoim=" + montoim + ", montoiva=" + montoiva + ", idorigenc=" + idorigenc + ", periodo=" + periodo + ", idtipo=" + idtipo + ", idpoliza=" + idpoliza + ", tipomov=" + tipomov + ", saldoec=" + saldoec + ", transaccion=" + transaccion + ", montoivaim=" + montoivaim + ", efectivo=" + efectivo + ", diasvencidos=" + diasvencidos + ", montovencido=" + montovencido + ", ticket=" + ticket + ", montoidnc=" + montoidnc + ", montoieco=" + montoieco + ", montoidncm=" + montoidncm + ", montoiecom=" + montoiecom + '}';
    }
    
}
