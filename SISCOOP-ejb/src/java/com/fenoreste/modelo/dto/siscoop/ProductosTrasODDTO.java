/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "productostrasod")
public class ProductosTrasODDTO implements Serializable {

    List<String[]> origenes;
    List<String[]> destinos;
    String responseCode;

    public ProductosTrasODDTO() {
    }

    public ProductosTrasODDTO(List<String[]> origenes, List<String[]> destinos, String responseCode) {
        this.origenes = origenes;
        this.destinos = destinos;
        this.responseCode = responseCode;
    }

    public ProductosTrasODDTO(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<String[]> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(List<String[]> origenes) {
        this.origenes = origenes;
    }

    public List<String[]> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<String[]> destinos) {
        this.destinos = destinos;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.origenes);
        hash = 67 * hash + Objects.hashCode(this.destinos);
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
        final ProductosTrasODDTO other = (ProductosTrasODDTO) obj;
        if (!Objects.equals(this.origenes, other.origenes)) {
            return false;
        }
        return Objects.equals(this.destinos, other.destinos);
    }
    
    @Override
    public String toString() {
        return "ProductosTrasOD{" + "origenes=" + origenes + ", destinos=" + destinos + ", responseCode=" + responseCode + '}';
    }
    
}
