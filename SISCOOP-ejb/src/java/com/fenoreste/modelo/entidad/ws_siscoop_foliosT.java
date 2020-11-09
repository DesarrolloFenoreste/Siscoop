/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilmer
 */
@Cacheable(false)
@Entity
@Table(name = "ws_siscoop_folios_tarjetas")
@XmlRootElement
public class ws_siscoop_foliosT implements Serializable{

    private static final long serialVersionUID = 1L;   
    @Column(name = "idorigenp")
    private String idorigenp;
    @Column(name = "idproducto")
    private String idproducto;
    @Column(name = "idauxiliar")
    private String idauxiliar;
    @EmbeddedId
    protected ws_siscoop_foliosTPK foliosPK;  
    private String idtarjeta;
    @Column(name = "fecha_hora")
    private String fecha_hora;
    @Column(name = "asignada")
    private String asignada;
    @Column(name = "activa")
    private String activa;
    @Column(name="bloqueada")
    private String bloqueada;
    
    public ws_siscoop_foliosT(){    
    }
    
    public ws_siscoop_foliosT(ws_siscoop_foliosTPK foliosTPK) {
        this.foliosPK = foliosTPK;
    }

    public ws_siscoop_foliosT(String idTarjeta) {
        this.foliosPK = new ws_siscoop_foliosTPK(idTarjeta);
    }
       

    public String getIdorigenp() {
        return idorigenp;
    }

    public void setIdorigenp(String idorigenp) {
        this.idorigenp = idorigenp;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getIdauxiliar() {
        return idauxiliar;
    }

    public void setIdauxiliar(String idauxiliar) {
        this.idauxiliar = idauxiliar;
    }

    public ws_siscoop_foliosTPK getFoliosPK() {
        return foliosPK;
    }

    public void setFoliosPK(ws_siscoop_foliosTPK foliosPK) {
        this.foliosPK = foliosPK;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getAsignada() {
        return asignada;
    }

    public void setAsignada(String asignada) {
        this.asignada = asignada;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(String bloqueada) {
        this.bloqueada = bloqueada;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foliosPK != null ? foliosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablas)) {
            return false;
        }
        ws_siscoop_foliosT other = (ws_siscoop_foliosT) object;
        return !((this.foliosPK == null && other.foliosPK != null) || (this.foliosPK != null && !this.foliosPK.equals(other.foliosPK)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.ws_siscoop_foliosT[ foliosPK=" +foliosPK + " ]";
    }

}
