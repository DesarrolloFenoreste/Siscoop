/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "catalogo_cuentas_terceros")
@XmlRootElement
public class CatalogoCuentasTerceros implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CatalogoCuentasTercerosPK catalogoCuentasTercerosPK;
    @Column(name = "aliascuenta")
    private String aliascuenta;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "email")
    private String email;
    @Column(name = "estatus")
    private boolean estatus;
    @Column(name = "montolimite")
    private BigDecimal montolimite;

    public CatalogoCuentasTerceros() {
    }

    public CatalogoCuentasTerceros(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK) {
        this.catalogoCuentasTercerosPK = catalogoCuentasTercerosPK;
    }

    public CatalogoCuentasTerceros(CatalogoCuentasTercerosPK catalogoCuentasTercerosPK, String aliascuenta, Date fecha, boolean estatus, BigDecimal montolimite) {
        this.catalogoCuentasTercerosPK = catalogoCuentasTercerosPK;
        this.aliascuenta = aliascuenta;
        this.fecha = fecha;
        this.estatus = estatus;
        this.montolimite = montolimite;
    }

    public CatalogoCuentasTerceros(int idorigen, int idgrupo, int idsocio, int idorigenp, int idproducto, int idauxiliar) {
        this.catalogoCuentasTercerosPK = new CatalogoCuentasTercerosPK(idorigen, idgrupo, idsocio, idorigenp, idproducto, idauxiliar);
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

    public boolean getEstatus() {
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
        int hash = 0;
        hash += (catalogoCuentasTercerosPK != null ? catalogoCuentasTercerosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoCuentasTerceros)) {
            return false;
        }
        CatalogoCuentasTerceros other = (CatalogoCuentasTerceros) object;
        return !((this.catalogoCuentasTercerosPK == null && other.catalogoCuentasTercerosPK != null) || (this.catalogoCuentasTercerosPK != null && !this.catalogoCuentasTercerosPK.equals(other.catalogoCuentasTercerosPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.CatalogoCuentasTerceros[ catalogoCuentasTercerosPK=" + catalogoCuentasTercerosPK + " ]";
    }

}
