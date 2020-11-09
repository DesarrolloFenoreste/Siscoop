/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "productos")
@XmlRootElement
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuentaaplica")
    private String cuentaaplica;
    @Column(name = "cuentavencida")
    private String cuentavencida;
    @Column(name = "cuentaintord")
    private String cuentaintord;
    @Column(name = "cuentaidnc")
    private String cuentaidnc;
    @Column(name = "cuentaidncv")
    private String cuentaidncv;
    @Column(name = "cuentaidncres")
    private String cuentaidncres;
    @Column(name = "cuentaoiod")
    private String cuentaoiod;
    @Column(name = "cuentaintmor")
    private String cuentaintmor;
    @Column(name = "cuentaiva")
    private String cuentaiva;
    @Column(name = "cuentarc")
    private String cuentarc;
    @Column(name = "cuentari")
    private String cuentari;
    @Column(name = "tipoproducto")
    private Integer tipoproducto;
    @Column(name = "tiporetiro")
    private Short tiporetiro;
    @Column(name = "tipocalculo")
    private Short tipocalculo;
    @Column(name = "tasaio")
    private BigDecimal tasaio;
    @Column(name = "tasaiod")
    private BigDecimal tasaiod;
    @Column(name = "tasaim")
    private BigDecimal tasaim;
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "ivaim")
    private BigDecimal ivaim;
    @Column(name = "garantias")
    private short garantias;
    @Column(name = "avales")
    private short avales;
    @Column(name = "reqsocio")
    private Boolean reqsocio;
    @Column(name = "tipoamortizacion")
    private int tipoamortizacion;
    @Column(name = "maxeventos")
    private short maxeventos;
    @Column(name = "maxdv")
    private short maxdv;
    @Column(name = "saldominimo")
    private String saldominimo;
    @Column(name = "saldomaximo")
    private String saldomaximo;
    @Column(name = "cuentageprcc")
    private String cuentageprcc;
    @Column(name = "cuentageprci")
    private String cuentageprci;
    @Column(name = "cuentaeprcc")
    private String cuentaeprcc;
    @Column(name = "cuentaeprci")
    private String cuentaeprci;
    @Column(name = "cuentaoimd")
    private String cuentaoimd;
    @Column(name = "cuentaoima")
    private String cuentaoima;
    @Column(name = "cuentaoioa")
    private String cuentaoioa;
    @Column(name = "cuentaivaim")
    private String cuentaivaim;
    @Column(name = "cuentaivaidncvig")
    private String cuentaivaidncvig;
    @Column(name = "cuentaivaidncven")
    private String cuentaivaidncven;
    @Column(name = "cuentaivappidnc")
    private String cuentaivappidnc;
    @Column(name = "cuentaintordv")
    private String cuentaintordv;
    @Column(name = "plazomax")
    private Integer plazomax;
    @Column(name = "tipofinalidad")
    private Integer tipofinalidad;
    @Column(name = "activo")
    private short activo;
    @Column(name = "pagodiafijo")
    private Short pagodiafijo;
    @Column(name = "cant_aperturas")
    private short cantAperturas;
    @Column(name = "producto_padre")
    private Integer productoPadre;
    @Column(name = "tasasp")
    private BigDecimal tasasp;
    @Column(name = "ivasp")
    private BigDecimal ivasp;
    @Column(name = "cuentasp")
    private String cuentasp;
    @Column(name = "cuentaivasp")
    private String cuentaivasp;
    @Column(name = "tolerancia_im")
    private short toleranciaIm;
    @Column(name = "tolerancia_com_no_pago")
    private short toleranciaComNoPago;
    @Column(name = "monto_com_no_pago")
    private BigDecimal montoComNoPago;
    @Column(name = "comision_apertura")
    private BigDecimal comisionApertura;
    @Column(name = "rango_edad")
    private String rangoEdad;
    @Column(name = "evalua_aperturas")
    private String evaluaAperturas;
    @Column(name = "cuentaintmorv")
    private String cuentaintmorv;
    @Column(name = "cuentaidncmres")
    private String cuentaidncmres;
    @Column(name = "cuentaidncm")
    private String cuentaidncm;
    @Column(name = "cuentaidncmv")
    private String cuentaidncmv;
    @Column(name = "cuentageprcim")
    private String cuentageprcim;
    @Column(name = "cuentaeprcim")
    private String cuentaeprcim;

    public Productos() {
    }

    public Productos(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuentaaplica() {
        return cuentaaplica;
    }

    public void setCuentaaplica(String cuentaaplica) {
        this.cuentaaplica = cuentaaplica;
    }

    public String getCuentavencida() {
        return cuentavencida;
    }

    public void setCuentavencida(String cuentavencida) {
        this.cuentavencida = cuentavencida;
    }

    public String getCuentaintord() {
        return cuentaintord;
    }

    public void setCuentaintord(String cuentaintord) {
        this.cuentaintord = cuentaintord;
    }

    public String getCuentaidnc() {
        return cuentaidnc;
    }

    public void setCuentaidnc(String cuentaidnc) {
        this.cuentaidnc = cuentaidnc;
    }

    public String getCuentaidncv() {
        return cuentaidncv;
    }

    public void setCuentaidncv(String cuentaidncv) {
        this.cuentaidncv = cuentaidncv;
    }

    public String getCuentaidncres() {
        return cuentaidncres;
    }

    public void setCuentaidncres(String cuentaidncres) {
        this.cuentaidncres = cuentaidncres;
    }

    public String getCuentaoiod() {
        return cuentaoiod;
    }

    public void setCuentaoiod(String cuentaoiod) {
        this.cuentaoiod = cuentaoiod;
    }

    public String getCuentaintmor() {
        return cuentaintmor;
    }

    public void setCuentaintmor(String cuentaintmor) {
        this.cuentaintmor = cuentaintmor;
    }

    public String getCuentaiva() {
        return cuentaiva;
    }

    public void setCuentaiva(String cuentaiva) {
        this.cuentaiva = cuentaiva;
    }

    public String getCuentarc() {
        return cuentarc;
    }

    public void setCuentarc(String cuentarc) {
        this.cuentarc = cuentarc;
    }

    public String getCuentari() {
        return cuentari;
    }

    public void setCuentari(String cuentari) {
        this.cuentari = cuentari;
    }

    public Integer getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(Integer tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public Short getTiporetiro() {
        return tiporetiro;
    }

    public void setTiporetiro(Short tiporetiro) {
        this.tiporetiro = tiporetiro;
    }

    public Short getTipocalculo() {
        return tipocalculo;
    }

    public void setTipocalculo(Short tipocalculo) {
        this.tipocalculo = tipocalculo;
    }

    public BigDecimal getTasaio() {
        return tasaio;
    }

    public void setTasaio(BigDecimal tasaio) {
        this.tasaio = tasaio;
    }

    public BigDecimal getTasaiod() {
        return tasaiod;
    }

    public void setTasaiod(BigDecimal tasaiod) {
        this.tasaiod = tasaiod;
    }

    public BigDecimal getTasaim() {
        return tasaim;
    }

    public void setTasaim(BigDecimal tasaim) {
        this.tasaim = tasaim;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getIvaim() {
        return ivaim;
    }

    public void setIvaim(BigDecimal ivaim) {
        this.ivaim = ivaim;
    }

    public short getGarantias() {
        return garantias;
    }

    public void setGarantias(short garantias) {
        this.garantias = garantias;
    }

    public short getAvales() {
        return avales;
    }

    public void setAvales(short avales) {
        this.avales = avales;
    }

    public Boolean getReqsocio() {
        return reqsocio;
    }

    public void setReqsocio(Boolean reqsocio) {
        this.reqsocio = reqsocio;
    }

    public int getTipoamortizacion() {
        return tipoamortizacion;
    }

    public void setTipoamortizacion(int tipoamortizacion) {
        this.tipoamortizacion = tipoamortizacion;
    }

    public short getMaxeventos() {
        return maxeventos;
    }

    public void setMaxeventos(short maxeventos) {
        this.maxeventos = maxeventos;
    }

    public short getMaxdv() {
        return maxdv;
    }

    public void setMaxdv(short maxdv) {
        this.maxdv = maxdv;
    }

    public String getSaldominimo() {
        return saldominimo;
    }

    public void setSaldominimo(String saldominimo) {
        this.saldominimo = saldominimo;
    }

    public String getSaldomaximo() {
        return saldomaximo;
    }

    public void setSaldomaximo(String saldomaximo) {
        this.saldomaximo = saldomaximo;
    }

    public String getCuentageprcc() {
        return cuentageprcc;
    }

    public void setCuentageprcc(String cuentageprcc) {
        this.cuentageprcc = cuentageprcc;
    }

    public String getCuentageprci() {
        return cuentageprci;
    }

    public void setCuentageprci(String cuentageprci) {
        this.cuentageprci = cuentageprci;
    }

    public String getCuentaeprcc() {
        return cuentaeprcc;
    }

    public void setCuentaeprcc(String cuentaeprcc) {
        this.cuentaeprcc = cuentaeprcc;
    }

    public String getCuentaeprci() {
        return cuentaeprci;
    }

    public void setCuentaeprci(String cuentaeprci) {
        this.cuentaeprci = cuentaeprci;
    }

    public String getCuentaoimd() {
        return cuentaoimd;
    }

    public void setCuentaoimd(String cuentaoimd) {
        this.cuentaoimd = cuentaoimd;
    }

    public String getCuentaoima() {
        return cuentaoima;
    }

    public void setCuentaoima(String cuentaoima) {
        this.cuentaoima = cuentaoima;
    }

    public String getCuentaoioa() {
        return cuentaoioa;
    }

    public void setCuentaoioa(String cuentaoioa) {
        this.cuentaoioa = cuentaoioa;
    }

    public String getCuentaivaim() {
        return cuentaivaim;
    }

    public void setCuentaivaim(String cuentaivaim) {
        this.cuentaivaim = cuentaivaim;
    }

    public String getCuentaivaidncvig() {
        return cuentaivaidncvig;
    }

    public void setCuentaivaidncvig(String cuentaivaidncvig) {
        this.cuentaivaidncvig = cuentaivaidncvig;
    }

    public String getCuentaivaidncven() {
        return cuentaivaidncven;
    }

    public void setCuentaivaidncven(String cuentaivaidncven) {
        this.cuentaivaidncven = cuentaivaidncven;
    }

    public String getCuentaivappidnc() {
        return cuentaivappidnc;
    }

    public void setCuentaivappidnc(String cuentaivappidnc) {
        this.cuentaivappidnc = cuentaivappidnc;
    }

    public String getCuentaintordv() {
        return cuentaintordv;
    }

    public void setCuentaintordv(String cuentaintordv) {
        this.cuentaintordv = cuentaintordv;
    }

    public Integer getPlazomax() {
        return plazomax;
    }

    public void setPlazomax(Integer plazomax) {
        this.plazomax = plazomax;
    }

    public Integer getTipofinalidad() {
        return tipofinalidad;
    }

    public void setTipofinalidad(Integer tipofinalidad) {
        this.tipofinalidad = tipofinalidad;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Short getPagodiafijo() {
        return pagodiafijo;
    }

    public void setPagodiafijo(Short pagodiafijo) {
        this.pagodiafijo = pagodiafijo;
    }

    public short getCantAperturas() {
        return cantAperturas;
    }

    public void setCantAperturas(short cantAperturas) {
        this.cantAperturas = cantAperturas;
    }

    public Integer getProductoPadre() {
        return productoPadre;
    }

    public void setProductoPadre(Integer productoPadre) {
        this.productoPadre = productoPadre;
    }

    public BigDecimal getTasasp() {
        return tasasp;
    }

    public void setTasasp(BigDecimal tasasp) {
        this.tasasp = tasasp;
    }

    public BigDecimal getIvasp() {
        return ivasp;
    }

    public void setIvasp(BigDecimal ivasp) {
        this.ivasp = ivasp;
    }

    public String getCuentasp() {
        return cuentasp;
    }

    public void setCuentasp(String cuentasp) {
        this.cuentasp = cuentasp;
    }

    public String getCuentaivasp() {
        return cuentaivasp;
    }

    public void setCuentaivasp(String cuentaivasp) {
        this.cuentaivasp = cuentaivasp;
    }

    public short getToleranciaIm() {
        return toleranciaIm;
    }

    public void setToleranciaIm(short toleranciaIm) {
        this.toleranciaIm = toleranciaIm;
    }

    public short getToleranciaComNoPago() {
        return toleranciaComNoPago;
    }

    public void setToleranciaComNoPago(short toleranciaComNoPago) {
        this.toleranciaComNoPago = toleranciaComNoPago;
    }

    public BigDecimal getMontoComNoPago() {
        return montoComNoPago;
    }

    public void setMontoComNoPago(BigDecimal montoComNoPago) {
        this.montoComNoPago = montoComNoPago;
    }

    public BigDecimal getComisionApertura() {
        return comisionApertura;
    }

    public void setComisionApertura(BigDecimal comisionApertura) {
        this.comisionApertura = comisionApertura;
    }

    public String getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(String rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public String getEvaluaAperturas() {
        return evaluaAperturas;
    }

    public void setEvaluaAperturas(String evaluaAperturas) {
        this.evaluaAperturas = evaluaAperturas;
    }

    public String getCuentaintmorv() {
        return cuentaintmorv;
    }

    public void setCuentaintmorv(String cuentaintmorv) {
        this.cuentaintmorv = cuentaintmorv;
    }

    public String getCuentaidncmres() {
        return cuentaidncmres;
    }

    public void setCuentaidncmres(String cuentaidncmres) {
        this.cuentaidncmres = cuentaidncmres;
    }

    public String getCuentaidncm() {
        return cuentaidncm;
    }

    public void setCuentaidncm(String cuentaidncm) {
        this.cuentaidncm = cuentaidncm;
    }

    public String getCuentaidncmv() {
        return cuentaidncmv;
    }

    public void setCuentaidncmv(String cuentaidncmv) {
        this.cuentaidncmv = cuentaidncmv;
    }

    public String getCuentageprcim() {
        return cuentageprcim;
    }

    public void setCuentageprcim(String cuentageprcim) {
        this.cuentageprcim = cuentageprcim;
    }

    public String getCuentaeprcim() {
        return cuentaeprcim;
    }

    public void setCuentaeprcim(String cuentaeprcim) {
        this.cuentaeprcim = cuentaeprcim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        return !((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Productos[ idproducto=" + idproducto + " ]";
    }

}
