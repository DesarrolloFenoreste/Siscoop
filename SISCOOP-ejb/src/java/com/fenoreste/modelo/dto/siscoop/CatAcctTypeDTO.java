/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "CatAcctTypeDTO")
public class CatAcctTypeDTO {

    private String idaccttype;
    private String description;

    public CatAcctTypeDTO() {
    }

    public String getIdaccttype() {
        return idaccttype;
    }

    public void setIdaccttype(String idaccttype) {
        this.idaccttype = idaccttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idaccttype);
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
        final CatAcctTypeDTO other = (CatAcctTypeDTO) obj;
        return Objects.equals(this.idaccttype, other.idaccttype);
    }

    @Override
    public String toString() {
        return "CatAcctTypeDTO{" + "idaccttype=" + idaccttype + ", description=" + description + '}';
    }

}
