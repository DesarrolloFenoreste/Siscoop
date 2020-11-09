/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.entidad;

import java.io.Serializable;
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
@Table(name = "banca_movil_usuarios")
@XmlRootElement
public class BancaMovilUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "idorigen")
    private int idorigen;
    @Column(name = "idgrupo")
    private int idgrupo;
    @Column(name = "idsocio")
    private int idsocio;
    @Id
    @Column(name = "alias_usuario")
    private String aliasUsuario;
    @Column(name = "idorigenp")
    private int idorigenp;
    @Column(name = "idproducto")
    private int idproducto;
    @Column(name = "idauxiliar")
    private int idauxiliar;
    @Column(name = "estatus")
    private boolean estatus;

    public BancaMovilUsuarios() {
    }

    public BancaMovilUsuarios(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public BancaMovilUsuarios(String aliasUsuario, int idorigen, int idgrupo, int idsocio, int idorigenp, int idproducto, int idauxiliar, boolean estatus) {
        this.aliasUsuario = aliasUsuario;
        this.idorigen = idorigen;
        this.idgrupo = idgrupo;
        this.idsocio = idsocio;
        this.idorigenp = idorigenp;
        this.idproducto = idproducto;
        this.idauxiliar = idauxiliar;
        this.estatus = estatus;
    }

    public int getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(int idorigen) {
        this.idorigen = idorigen;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public int getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(int idsocio) {
        this.idsocio = idsocio;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public int getIdorigenp() {
        return idorigenp;
    }

    public void setIdorigenp(int idorigenp) {
        this.idorigenp = idorigenp;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdauxiliar() {
        return idauxiliar;
    }

    public void setIdauxiliar(int idauxiliar) {
        this.idauxiliar = idauxiliar;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aliasUsuario != null ? aliasUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancaMovilUsuarios)) {
            return false;
        }
        BancaMovilUsuarios other = (BancaMovilUsuarios) object;
        return !((this.aliasUsuario == null && other.aliasUsuario != null) || (this.aliasUsuario != null && !this.aliasUsuario.equals(other.aliasUsuario)));
    }

    @Override
    public String toString() {
        return "com.fenoreste.modelo.entidad.BancaMovilUsuarios[ aliasUsuario=" + aliasUsuario + " ]";
    }
    
}
