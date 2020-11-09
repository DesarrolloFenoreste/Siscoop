/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.ReferenciaspPK;
import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "referenciasp")
public class ReferenciaspDTO implements Serializable {

    protected ReferenciaspPK referenciaspPK;
    private String referencia;

    public ReferenciaspDTO() {
    }

    public ReferenciaspPK getReferenciaspPK() {
        return referenciaspPK;
    }

    public void setReferenciaspPK(ReferenciaspPK referenciaspPK) {
        this.referenciaspPK = referenciaspPK;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.referenciaspPK);
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
        final ReferenciaspDTO other = (ReferenciaspDTO) obj;
        return Objects.equals(this.referenciaspPK, other.referenciaspPK);
    }

    @Override
    public String toString() {
        return "ReferenciaspDTO{" + "referenciaspPK=" + referenciaspPK + ", referencia=" + referencia + '}';
    }

}
