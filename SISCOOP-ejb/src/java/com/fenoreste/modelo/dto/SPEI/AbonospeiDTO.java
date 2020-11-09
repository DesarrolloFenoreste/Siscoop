/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.SPEI;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gerardo
 */
public class AbonospeiDTO implements Serializable {

    private String clave;
    private String fechaoperacion;
    private String institucionordenante;
    private String institucionbeneficiaria;
    private String claverastreo;
    private BigDecimal monto;
    private String nombreordenante;
    private String tipocuentaordenante;
    private String cuentaordenante;
    private String rfccurpordenante;
    private String nombrebeneficiario;
    private String tipocuentabeneficiario;
    private String cuentabeneficiario;
    private String rfccurpbeneficiario;
    private String conceptopago;
    private String referencianumerica;
    private String empresa;
    private String idteller;
    private String sessionid;
    private boolean aplicado;
    private String responsecode;
    private Date fecha;
    private Date fechaRespuesta;

    public AbonospeiDTO() {
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFechaoperacion() {
        return fechaoperacion;
    }

    public void setFechaoperacion(String fechaoperacion) {
        this.fechaoperacion = fechaoperacion;
    }

    public String getInstitucionordenante() {
        return institucionordenante;
    }

    public void setInstitucionordenante(String institucionordenante) {
        this.institucionordenante = institucionordenante;
    }

    public String getInstitucionbeneficiaria() {
        return institucionbeneficiaria;
    }

    public void setInstitucionbeneficiaria(String institucionbeneficiaria) {
        this.institucionbeneficiaria = institucionbeneficiaria;
    }

    public String getClaverastreo() {
        return claverastreo;
    }

    public void setClaverastreo(String claverastreo) {
        this.claverastreo = claverastreo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getNombreordenante() {
        return nombreordenante;
    }

    public void setNombreordenante(String nombreordenante) {
        this.nombreordenante = nombreordenante;
    }

    public String getTipocuentaordenante() {
        return tipocuentaordenante;
    }

    public void setTipocuentaordenante(String tipocuentaordenante) {
        this.tipocuentaordenante = tipocuentaordenante;
    }

    public String getCuentaordenante() {
        return cuentaordenante;
    }

    public void setCuentaordenante(String cuentaordenante) {
        this.cuentaordenante = cuentaordenante;
    }

    public String getRfccurpordenante() {
        return rfccurpordenante;
    }

    public void setRfccurpordenante(String rfccurpordenante) {
        this.rfccurpordenante = rfccurpordenante;
    }

    public String getNombrebeneficiario() {
        return nombrebeneficiario;
    }

    public void setNombrebeneficiario(String nombrebeneficiario) {
        this.nombrebeneficiario = nombrebeneficiario;
    }

    public String getTipocuentabeneficiario() {
        return tipocuentabeneficiario;
    }

    public void setTipocuentabeneficiario(String tipocuentabeneficiario) {
        this.tipocuentabeneficiario = tipocuentabeneficiario;
    }

    public String getCuentabeneficiario() {
        return cuentabeneficiario;
    }

    public void setCuentabeneficiario(String cuentabeneficiario) {
        this.cuentabeneficiario = cuentabeneficiario;
    }

    public String getRfccurpbeneficiario() {
        return rfccurpbeneficiario;
    }

    public void setRfccurpbeneficiario(String rfccurpbeneficiario) {
        this.rfccurpbeneficiario = rfccurpbeneficiario;
    }

    public String getConceptopago() {
        return conceptopago;
    }

    public void setConceptopago(String conceptopago) {
        this.conceptopago = conceptopago;
    }

    public String getReferencianumerica() {
        return referencianumerica;
    }

    public void setReferencianumerica(String referencianumerica) {
        this.referencianumerica = referencianumerica;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getIdteller() {
        return idteller;
    }

    public void setIdteller(String idteller) {
        this.idteller = idteller;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public boolean isAplicado() {
        return aplicado;
    }

    public void setAplicado(boolean aplicado) {
        this.aplicado = aplicado;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.clave);
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
        final AbonospeiDTO other = (AbonospeiDTO) obj;
        return Objects.equals(this.clave, other.clave);
    }

    @Override
    public String toString() {
        return "AbonospeiDTO{" + "clave=" + clave + ", fechaoperacion=" + fechaoperacion + ", institucionordenante=" + institucionordenante + ", institucionbeneficiaria=" + institucionbeneficiaria + ", claverastreo=" + claverastreo + ", monto=" + monto + ", nombreordenante=" + nombreordenante + ", tipocuentaordenante=" + tipocuentaordenante + ", cuentaordenante=" + cuentaordenante + ", rfccurpordenante=" + rfccurpordenante + ", nombrebeneficiario=" + nombrebeneficiario + ", tipocuentabeneficiario=" + tipocuentabeneficiario + ", cuentabeneficiario=" + cuentabeneficiario + ", rfccurpbeneficiario=" + rfccurpbeneficiario + ", conceptopago=" + conceptopago + ", referencianumerica=" + referencianumerica + ", empresa=" + empresa + ", idteller=" + idteller + ", sessionid=" + sessionid + ", aplicado=" + aplicado + ", responsecode=" + responsecode + ", fecha=" + fecha + '}';
    }

}
