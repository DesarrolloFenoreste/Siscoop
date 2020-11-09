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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@Cacheable(false)
@Entity
@Table(name = "temporal")
@XmlRootElement
public class Temporal implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected TemporalPK temporalPK;
    @Column(name = "idusuario")
    private Integer idusuario;
    @Column(name = "idorigen")
    private Integer idorigen;
    @Column(name = "idgrupo")
    private Integer idgrupo;
    @Column(name = "idsocio")
    private Integer idsocio;
    @Column(name = "esentrada")
    private Boolean esentrada;
    @Column(name = "acapital")
    private BigDecimal acapital;
    @Column(name = "io_pag")
    private BigDecimal ioPag;
    @Column(name = "io_cal")
    private BigDecimal ioCal;
    @Column(name = "im_pag")
    private BigDecimal imPag;
    @Column(name = "im_cal")
    private BigDecimal imCal;
    @Column(name = "aiva")
    private BigDecimal aiva;
    @Column(name = "saldodiacum")
    private BigDecimal saldodiacum;
    @Column(name = "abonifio")
    private BigDecimal abonifio;
    @Column(name = "idcuenta")
    private String idcuenta;
    @Column(name = "aplicado")
    private Boolean aplicado;
    @Column(name = "ivaio_pag")
    private BigDecimal ivaioPag;
    @Column(name = "ivaio_cal")
    private BigDecimal ivaioCal;
    @Column(name = "ivaim_pag")
    private BigDecimal ivaimPag;
    @Column(name = "ivaim_cal")
    private BigDecimal ivaimCal;
    @Column(name = "mov")
    private Integer mov;
    @Column(name = "tipomov")
    private int tipomov;
    @Column(name = "efectivo")
    private BigDecimal efectivo;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "cpnp_pag")
    private BigDecimal cpnpPag;
    @Column(name = "cpnp_cal")
    private BigDecimal cpnpCal;
    @Column(name = "diasvencidos")
    private int diasvencidos;
    @Column(name = "montovencido")
    private BigDecimal montovencido;
    @Column(name = "idorigena")
    private Integer idorigena;
    @Column(name = "huella_valida")
    private Boolean huellaValida;
    @Column(name = "concepto_mov")
    private String conceptoMov;
    @Column(name = "fe_nom_archivo")
    private String feNomArchivo;
    @Column(name = "fe_xml")
    private String feXml;
    @Column(name = "sai_aux")
    private String saiAux;

    public Temporal() {
    }

    public Temporal(TemporalPK temporalPK) {
        this.temporalPK = temporalPK;
    }

    public Temporal(TemporalPK temporalPK, int tipomov, BigDecimal cpnpPag, BigDecimal cpnpCal, int diasvencidos, BigDecimal montovencido) {
        this.temporalPK = temporalPK;
        this.tipomov = tipomov;
        this.cpnpPag = cpnpPag;
        this.cpnpCal = cpnpCal;
        this.diasvencidos = diasvencidos;
        this.montovencido = montovencido;
    }

    public Temporal(String sesion, int idorigenp, int idproducto, int idauxiliar) {
        this.temporalPK = new TemporalPK(sesion, idorigenp, idproducto, idauxiliar);
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
        int hash = 0;
        hash += (temporalPK != null ? temporalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temporal)) {
            return false;
        }
        Temporal other = (Temporal) object;
        return !((this.temporalPK == null && other.temporalPK != null) || (this.temporalPK != null && !this.temporalPK.equals(other.temporalPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.Temporal[ temporalPK=" + temporalPK + " ]";
    }

}
