/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.tdd;

import com.fenoreste.modelo.entidad.WsSiscoopResultadoFinalBancamovilPK;
import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "WsSiscoopResultadoFinalBancamovil")
public class WsSiscoopResultadoFinalBancamovilDTO implements Serializable {

    protected WsSiscoopResultadoFinalBancamovilPK wsSiscoopResultadoFinalBancamovilPK;
    private int idusuario;
    private String envio;
    private String respuesta;

    public WsSiscoopResultadoFinalBancamovilDTO() {
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.wsSiscoopResultadoFinalBancamovilPK);
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
        final WsSiscoopResultadoFinalBancamovilDTO other = (WsSiscoopResultadoFinalBancamovilDTO) obj;
        return Objects.equals(this.wsSiscoopResultadoFinalBancamovilPK, other.wsSiscoopResultadoFinalBancamovilPK);
    }

    @Override
    public String toString() {
        return "WsSiscoopResultadoFinalBancamovilDTO{" + "wsSiscoopResultadoFinalBancamovilPK=" + wsSiscoopResultadoFinalBancamovilPK + ", idusuario=" + idusuario + ", envio=" + envio + ", respuesta=" + respuesta + '}';
    }

}
