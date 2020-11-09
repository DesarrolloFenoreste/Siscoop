/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "ws_siscoop_resultado_final_bancamovil")
@XmlRootElement
public class WsSiscoopResultadoFinalBancamovil implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario", nullable = false)
    private int idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "envio", nullable = false, length = 2147483647)
    private String envio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "respuesta", nullable = false, length = 2147483647)
    private String respuesta;

    public WsSiscoopResultadoFinalBancamovil() {
    }

    public WsSiscoopResultadoFinalBancamovil(WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK) {
        this.wsSiscoopResultadoFinalBancamovilPK = wsSiscoopResultadoFinalBancamovilPK;
    }

    public WsSiscoopResultadoFinalBancamovil(WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK, int idusuario, String envio, String respuesta) {
        this.wsSiscoopResultadoFinalBancamovilPK = wsSiscoopResultadoFinalBancamovilPK;
        this.idusuario = idusuario;
        this.envio = envio;
        this.respuesta = respuesta;
    }

    public WsSiscoopResultadoFinalBancamovil(String opa, Date fecha, String metodo) {
        this.wsSiscoopResultadoFinalBancamovilPK = new WsSiscoopResultadoFinalBancamovilPK(opa, fecha, metodo);
    }

    public WsSiscoopResultadoFinalBancamovilPK getWsSiscoopResultadoFinalBancamovilPK() {
        return wsSiscoopResultadoFinalBancamovilPK;
    }

    public void setWsSiscoopResultadoFinalBancamovilPK(WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK) {
        this.wsSiscoopResultadoFinalBancamovilPK = wsSiscoopResultadoFinalBancamovilPK;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wsSiscoopResultadoFinalBancamovilPK != null ? wsSiscoopResultadoFinalBancamovilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsSiscoopResultadoFinalBancamovil)) {
            return false;
        }
        WsSiscoopResultadoFinalBancamovil other = (WsSiscoopResultadoFinalBancamovil) object;
        return !((this.wsSiscoopResultadoFinalBancamovilPK == null && other.wsSiscoopResultadoFinalBancamovilPK != null) || (this.wsSiscoopResultadoFinalBancamovilPK != null && !this.wsSiscoopResultadoFinalBancamovilPK.equals(other.wsSiscoopResultadoFinalBancamovilPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.WsSiscoopResultadoFinalBancamovil[ wsSiscoopResultadoFinalBancamovilPK=" + wsSiscoopResultadoFinalBancamovilPK + " ]";
    }

}
