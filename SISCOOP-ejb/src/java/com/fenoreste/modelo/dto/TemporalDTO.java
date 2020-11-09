/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;
import com.fenoreste.modelo.entidad.TemporalPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "temporal")
public class TemporalDTO implements Serializable {

    protected TemporalPK temporalPK;
    private Integer idusuario;
    private Integer idorigen;
    private Integer idgrupo;
    private Integer idsocio;
    private Boolean esentrada;
    private BigDecimal acapital;
    private BigDecimal ioPag;
    private BigDecimal ioCal;
    private BigDecimal imPag;
    private BigDecimal imCal;
    private BigDecimal aiva;
    private BigDecimal saldodiacum;
    private BigDecimal abonifio;
    private String idcuenta;
    private Boolean aplicado;
    private BigDecimal ivaioPag;
    private BigDecimal ivaioCal;
    private BigDecimal ivaimPag;
    private BigDecimal ivaimCal;
    private Integer mov;
    private int tipomov;
    private BigDecimal efectivo;
    private String referencia;
    private BigDecimal cpnpPag;
    private BigDecimal cpnpCal;
    private int diasvencidos;
    private BigDecimal montovencido;
    private Integer idorigena;
    private Boolean huellaValida;
    private String conceptoMov;
    private String feNomArchivo;
    private String feXml;
    private String saiAux;

    public TemporalDTO() {
    }

    public TemporalPK getTemporalPK() {
        return temporalPK;
    }

    public void setTemporalPK(TemporalPK temporalPK) {
        this.temporalPK = temporalPK;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(Integer idorigen) {
        this.idorigen = idorigen;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public Boolean getEsentrada() {
        return esentrada;
    }

    public void setEsentrada(Boolean esentrada) {
        this.esentrada = esentrada;
    }

    public BigDecimal getAcapital() {
        return acapital;
    }

    public void setAcapital(BigDecimal acapital) {
        this.acapital = acapital;
    }

    public BigDecimal getIoPag() {
        return ioPag;
    }

    public void setIoPag(BigDecimal ioPag) {
        this.ioPag = ioPag;
    }

    public BigDecimal getIoCal() {
        return ioCal;
    }

    public void setIoCal(BigDecimal ioCal) {
        this.ioCal = ioCal;
    }

    public BigDecimal getImPag() {
        return imPag;
    }

    public void setImPag(BigDecimal imPag) {
        this.imPag = imPag;
    }

    public BigDecimal getImCal() {
        return imCal;
    }

    public void setImCal(BigDecimal imCal) {
        this.imCal = imCal;
    }

    public BigDecimal getAiva() {
        return aiva;
    }

    public void setAiva(BigDecimal aiva) {
        this.aiva = aiva;
    }

    public BigDecimal getSaldodiacum() {
        return saldodiacum;
    }

    public void setSaldodiacum(BigDecimal saldodiacum) {
        this.saldodiacum = saldodiacum;
    }

    public BigDecimal getAbonifio() {
        return abonifio;
    }

    public void setAbonifio(BigDecimal abonifio) {
        this.abonifio = abonifio;
    }

    public String getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(String idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Boolean getAplicado() {
        return aplicado;
    }

    public void setAplicado(Boolean aplicado) {
        this.aplicado = aplicado;
    }

    public BigDecimal getIvaioPag() {
        return ivaioPag;
    }

    public void setIvaioPag(BigDecimal ivaioPag) {
        this.ivaioPag = ivaioPag;
    }

    public BigDecimal getIvaioCal() {
        return ivaioCal;
    }

    public void setIvaioCal(BigDecimal ivaioCal) {
        this.ivaioCal = ivaioCal;
    }

    public BigDecimal getIvaimPag() {
        return ivaimPag;
    }

    public void setIvaimPag(BigDecimal ivaimPag) {
        this.ivaimPag = ivaimPag;
    }

    public BigDecimal getIvaimCal() {
        return ivaimCal;
    }

    public void setIvaimCal(BigDecimal ivaimCal) {
        this.ivaimCal = ivaimCal;
    }

    public Integer getMov() {
        return mov;
    }

    public void setMov(Integer mov) {
        this.mov = mov;
    }

    public int getTipomov() {
        return tipomov;
    }

    public void setTipomov(int tipomov) {
        this.tipomov = tipomov;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getCpnpPag() {
        return cpnpPag;
    }

    public void setCpnpPag(BigDecimal cpnpPag) {
        this.cpnpPag = cpnpPag;
    }

    public BigDecimal getCpnpCal() {
        return cpnpCal;
    }

    public void setCpnpCal(BigDecimal cpnpCal) {
        this.cpnpCal = cpnpCal;
    }

    public int getDiasvencidos() {
        return diasvencidos;
    }

    public void setDiasvencidos(int diasvencidos) {
        this.diasvencidos = diasvencidos;
    }

    public BigDecimal getMontovencido() {
        return montovencido;
    }

    public void setMontovencido(BigDecimal montovencido) {
        this.montovencido = montovencido;
    }

    public Integer getIdorigena() {
        return idorigena;
    }

    public void setIdorigena(Integer idorigena) {
        this.idorigena = idorigena;
    }

    public Boolean getHuellaValida() {
        return huellaValida;
    }

    public void setHuellaValida(Boolean huellaValida) {
        this.huellaValida = huellaValida;
    }

    public String getConceptoMov() {
        return conceptoMov;
    }

    public void setConceptoMov(String conceptoMov) {
        this.conceptoMov = conceptoMov;
    }

    public String getFeNomArchivo() {
        return feNomArchivo;
    }

    public void setFeNomArchivo(String feNomArchivo) {
        this.feNomArchivo = feNomArchivo;
    }

    public String getFeXml() {
        return feXml;
    }

    public void setFeXml(String feXml) {
        this.feXml = feXml;
    }

    public String getSaiAux() {
        return saiAux;
    }

    public void setSaiAux(String saiAux) {
        this.saiAux = saiAux;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.temporalPK);
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
        final TemporalDTO other = (TemporalDTO) obj;
        return Objects.equals(this.temporalPK, other.temporalPK);
    }

    @Override
    public String toString() {
        return "TemporalDTO{" + "temporalPK=" + temporalPK + ", idusuario=" + idusuario + ", idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", esentrada=" + esentrada + ", acapital=" + acapital + ", ioPag=" + ioPag + ", ioCal=" + ioCal + ", imPag=" + imPag + ", imCal=" + imCal + ", aiva=" + aiva + ", saldodiacum=" + saldodiacum + ", abonifio=" + abonifio + ", idcuenta=" + idcuenta + ", aplicado=" + aplicado + ", ivaioPag=" + ivaioPag + ", ivaioCal=" + ivaioCal + ", ivaimPag=" + ivaimPag + ", ivaimCal=" + ivaimCal + ", mov=" + mov + ", tipomov=" + tipomov + ", efectivo=" + efectivo + ", referencia=" + referencia + ", cpnpPag=" + cpnpPag + ", cpnpCal=" + cpnpCal + ", diasvencidos=" + diasvencidos + ", montovencido=" + montovencido + ", idorigena=" + idorigena + ", huellaValida=" + huellaValida + ", conceptoMov=" + conceptoMov + ", feNomArchivo=" + feNomArchivo + ", feXml=" + feXml + ", saiAux=" + saiAux + '}';
    }
    
}
