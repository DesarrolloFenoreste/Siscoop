/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "productos")
public class ProductosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idproducto;
    private String nombre;
    private String cuentaaplica;
    private String cuentavencida;
    private String cuentaintord;
    private String cuentaidnc;
    private String cuentaidncv;
    private String cuentaidncres;
    private String cuentaoiod;
    private String cuentaintmor;
    private String cuentaiva;
    private String cuentarc;
    private String cuentari;
    private Integer tipoproducto;
    private Short tiporetiro;
    private Short tipocalculo;
    private BigDecimal tasaio;
    private BigDecimal tasaiod;
    private BigDecimal tasaim;
    private BigDecimal iva;
    private BigDecimal ivaim;
    private short garantias;
    private short avales;
    private Boolean reqsocio;
    private int tipoamortizacion;
    private short maxeventos;
    private short maxdv;
    private String saldominimo;
    private String saldomaximo;
    private String cuentageprcc;
    private String cuentageprci;
    private String cuentaeprcc;
    private String cuentaeprci;
    private String cuentaoimd;
    private String cuentaoima;
    private String cuentaoioa;
    private String cuentaivaim;
    private String cuentaivaidncvig;
    private String cuentaivaidncven;
    private String cuentaivappidnc;
    private String cuentaintordv;
    private Integer plazomax;
    private Integer tipofinalidad;
    private short activo;
    private Short pagodiafijo;
    private short cantAperturas;
    private Integer productoPadre;
    private BigDecimal tasasp;
    private BigDecimal ivasp;
    private String cuentasp;
    private String cuentaivasp;
    private short toleranciaIm;
    private short toleranciaComNoPago;
    private BigDecimal montoComNoPago;
    private BigDecimal comisionApertura;
    private String rangoEdad;
    private String evaluaAperturas;
    private String cuentaintmorv;
    private String cuentaidncmres;
    private String cuentaidncm;
    private String cuentaidncmv;
    private String cuentageprcim;
    private String cuentaeprcim;

    public ProductosDTO() {
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
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idproducto);
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
        final ProductosDTO other = (ProductosDTO) obj;
        return Objects.equals(this.idproducto, other.idproducto);
    }

    @Override
    public String toString() {
        return "ProductosDTO{" + "idproducto=" + idproducto + ", nombre=" + nombre + ", cuentaaplica=" + cuentaaplica + ", cuentavencida=" + cuentavencida + ", cuentaintord=" + cuentaintord + ", cuentaidnc=" + cuentaidnc + ", cuentaidncv=" + cuentaidncv + ", cuentaidncres=" + cuentaidncres + ", cuentaoiod=" + cuentaoiod + ", cuentaintmor=" + cuentaintmor + ", cuentaiva=" + cuentaiva + ", cuentarc=" + cuentarc + ", cuentari=" + cuentari + ", tipoproducto=" + tipoproducto + ", tiporetiro=" + tiporetiro + ", tipocalculo=" + tipocalculo + ", tasaio=" + tasaio + ", tasaiod=" + tasaiod + ", tasaim=" + tasaim + ", iva=" + iva + ", ivaim=" + ivaim + ", garantias=" + garantias + ", avales=" + avales + ", reqsocio=" + reqsocio + ", tipoamortizacion=" + tipoamortizacion + ", maxeventos=" + maxeventos + ", maxdv=" + maxdv + ", saldominimo=" + saldominimo + ", saldomaximo=" + saldomaximo + ", cuentageprcc=" + cuentageprcc + ", cuentageprci=" + cuentageprci + ", cuentaeprcc=" + cuentaeprcc + ", cuentaeprci=" + cuentaeprci + ", cuentaoimd=" + cuentaoimd + ", cuentaoima=" + cuentaoima + ", cuentaoioa=" + cuentaoioa + ", cuentaivaim=" + cuentaivaim + ", cuentaivaidncvig=" + cuentaivaidncvig + ", cuentaivaidncven=" + cuentaivaidncven + ", cuentaivappidnc=" + cuentaivappidnc + ", cuentaintordv=" + cuentaintordv + ", plazomax=" + plazomax + ", tipofinalidad=" + tipofinalidad + ", activo=" + activo + ", pagodiafijo=" + pagodiafijo + ", cantAperturas=" + cantAperturas + ", productoPadre=" + productoPadre + ", tasasp=" + tasasp + ", ivasp=" + ivasp + ", cuentasp=" + cuentasp + ", cuentaivasp=" + cuentaivasp + ", toleranciaIm=" + toleranciaIm + ", toleranciaComNoPago=" + toleranciaComNoPago + ", montoComNoPago=" + montoComNoPago + ", comisionApertura=" + comisionApertura + ", rangoEdad=" + rangoEdad + ", evaluaAperturas=" + evaluaAperturas + ", cuentaintmorv=" + cuentaintmorv + ", cuentaidncmres=" + cuentaidncmres + ", cuentaidncm=" + cuentaidncm + ", cuentaidncmv=" + cuentaidncmv + ", cuentageprcim=" + cuentageprcim + ", cuentaeprcim=" + cuentaeprcim + '}';
    }

}
