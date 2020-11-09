/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad.SPEI;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "abonospei")
@XmlRootElement
public class AbonoSPEI implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "clave")
    private String clave;
    @Column(name = "fechaoperacion")
    private String fechaoperacion;
    @Column(name = "institucionordenante")
    private String institucionordenante;
    @Column(name = "institucionbeneficiaria")
    private String institucionbeneficiaria;
    @Column(name = "claverastreo")
    private String claverastreo;
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "nombreordenante")
    private String nombreordenante;
    @Column(name = "tipocuentaordenante")
    private String tipocuentaordenante;
    @Column(name = "cuentaordenante")
    private String cuentaordenante;
    @Column(name = "rfccurpordenante")
    private String rfccurpordenante;
    @Column(name = "nombrebeneficiario")
    private String nombrebeneficiario;
    @Column(name = "tipocuentabeneficiario")
    private String tipocuentabeneficiario;
    @Column(name = "cuentabeneficiario")
    private String cuentabeneficiario;
    @Column(name = "rfccurpbeneficiario")
    private String rfccurpbeneficiario;
    @Column(name = "conceptopago")
    private String conceptopago;
    @Column(name = "referencianumerica")
    private String referencianumerica;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "idteller")
    private String idteller;
    @Column(name = "sessionid")
    private String sessionid;
    @Column(name = "aplicado")
    private boolean aplicado;
    @Column(name = "responsecode")
    private String responsecode;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "fecharespuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

    public AbonoSPEI() {
    }

    public AbonoSPEI(String clave) {
        this.clave = clave;
    }

    public AbonoSPEI(String clave, String fechaoperacion, String institucionordenante, String institucionbeneficiaria, String claverastreo, BigDecimal monto, String nombrebeneficiario, String tipocuentabeneficiario, String cuentabeneficiario, String rfccurpbeneficiario, String conceptopago, String referencianumerica, String empresa, String idteller, String sessionid, boolean aplicado, String responsecode, Date fecha, Date fechaRespuesta) {
        this.clave = clave;
        this.fechaoperacion = fechaoperacion;
        this.institucionordenante = institucionordenante;
        this.institucionbeneficiaria = institucionbeneficiaria;
        this.claverastreo = claverastreo;
        this.monto = monto;
        this.nombrebeneficiario = nombrebeneficiario;
        this.tipocuentabeneficiario = tipocuentabeneficiario;
        this.cuentabeneficiario = cuentabeneficiario;
        this.rfccurpbeneficiario = rfccurpbeneficiario;
        this.conceptopago = conceptopago;
        this.referencianumerica = referencianumerica;
        this.empresa = empresa;
        this.idteller = idteller;
        this.sessionid = sessionid;
        this.aplicado = aplicado;
        this.responsecode = responsecode;
        this.fecha = fecha;
        this.fechaRespuesta = fechaRespuesta;
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

    public boolean getAplicado() {
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
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoSPEI)) {
            return false;
        }
        AbonoSPEI other = (AbonoSPEI) object;
        return !((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.SPEI.Abonospei[ clave=" + clave + " ]";
    }

}
