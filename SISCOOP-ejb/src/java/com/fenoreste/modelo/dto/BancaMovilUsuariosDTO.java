/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "BancaMovilUsuariosDTO")
public class BancaMovilUsuariosDTO implements Serializable {

    private int idorigen;
    private int idgrupo;
    private int idsocio;
    private String aliasUsuario;
    private int idorigenp;
    private int idproducto;
    private int idauxiliar;
    private boolean estatus;

    public BancaMovilUsuariosDTO() {
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

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.aliasUsuario);
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
        final BancaMovilUsuariosDTO other = (BancaMovilUsuariosDTO) obj;
        return Objects.equals(this.aliasUsuario, other.aliasUsuario);
    }

    @Override
    public String toString() {
        return "BancaMovilUsuariosDTO{" + "idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", aliasUsuario=" + aliasUsuario + ", idorigenp=" + idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", estatus=" + estatus + '}';
    }

}
