/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "CatTxTypeDTO")
public class CatTxTypeDTO implements Serializable {

    private String IdTxType;
    private String Description;

    public String getIdTxType() {
        return IdTxType;
    }

    public void setIdTxType(String IdTxType) {
        this.IdTxType = IdTxType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.IdTxType);
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
        final CatTxTypeDTO other = (CatTxTypeDTO) obj;
        return Objects.equals(this.IdTxType, other.IdTxType);
    }

    @Override
    public String toString() {
        return "CatTxType{" + "IdTxType=" + IdTxType + ", Description=" + Description + '}';
    }

}
