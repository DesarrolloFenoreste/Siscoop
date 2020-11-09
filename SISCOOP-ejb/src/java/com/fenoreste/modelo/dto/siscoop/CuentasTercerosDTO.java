/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import com.fenoreste.modelo.entidad.CatalogoCuentasTercerosPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "CuentasTercerosDTO")
public class CuentasTercerosDTO implements Serializable {

    protected CatalogoCuentasTercerosPK catalogoCuentasTercerosPK = null;
    private String aliascuenta;
    private Date fecha;
    private String rfc;
    private String email;
    private boolean estatus;
    private BigDecimal montolimite;

    public CuentasTercerosDTO() {
    }

    public CatalogoCuentasTercerosPK getCatalogoCuentasTercerosPK() {
        return catalogoCuentasTercerosPK;
    }

    public void setCatalogoCuentasTercerosPK(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK) {
        this.catalogoCuentasTercerosPK = catalogoCuentasTercerosPK;
    }

    public String getAliascuenta() {
        return aliascuenta;
    }

    public void setAliascuenta(String aliascuenta) {
        this.aliascuenta = aliascuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getMontolimite() {
        return montolimite;
    }

    public void setMontolimite(BigDecimal montolimite) {
        this.montolimite = montolimite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.catalogoCuentasTercerosPK);
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
        final CuentasTercerosDTO other = (CuentasTercerosDTO) obj;
        return Objects.equals(this.catalogoCuentasTercerosPK, other.catalogoCuentasTercerosPK);
    }

    @Override
    public String toString() {
        return aliascuenta;
    }

}
