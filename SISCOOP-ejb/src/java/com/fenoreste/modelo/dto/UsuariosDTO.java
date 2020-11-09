/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "usuariosdto")
public class UsuariosDTO implements Serializable {

    private Integer idusuario;
    private String login;
    private String nombre;
    private boolean activo;
    private Integer idorigenpDd;
    private Integer idproductoDd;
    private Integer idauxiliarDd;
    private Integer idorigenpAd;
    private Integer idproductoAd;
    private Integer idauxiliarAd;
    private String pingreso;
    private String pegreso;
    private String pdiario;
    private Integer idorigen;
    private Integer pIdorigen;
    private Integer pIdgrupo;
    private Integer pIdsocio;
    private Date fechalimite;
    //private List<Integer> procpid;
    private String impticket;
    private String passwd;
    //private List<String> ipLocal;
    private Integer ticket;

    public UsuariosDTO() {
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getIdorigenpDd() {
        return idorigenpDd;
    }

    public void setIdorigenpDd(Integer idorigenpDd) {
        this.idorigenpDd = idorigenpDd;
    }

    public Integer getIdproductoDd() {
        return idproductoDd;
    }

    public void setIdproductoDd(Integer idproductoDd) {
        this.idproductoDd = idproductoDd;
    }

    public Integer getIdauxiliarDd() {
        return idauxiliarDd;
    }

    public void setIdauxiliarDd(Integer idauxiliarDd) {
        this.idauxiliarDd = idauxiliarDd;
    }

    public Integer getIdorigenpAd() {
        return idorigenpAd;
    }

    public void setIdorigenpAd(Integer idorigenpAd) {
        this.idorigenpAd = idorigenpAd;
    }

    public Integer getIdproductoAd() {
        return idproductoAd;
    }

    public void setIdproductoAd(Integer idproductoAd) {
        this.idproductoAd = idproductoAd;
    }

    public Integer getIdauxiliarAd() {
        return idauxiliarAd;
    }

    public void setIdauxiliarAd(Integer idauxiliarAd) {
        this.idauxiliarAd = idauxiliarAd;
    }

    public String getPingreso() {
        return pingreso;
    }

    public void setPingreso(String pingreso) {
        this.pingreso = pingreso;
    }

    public String getPegreso() {
        return pegreso;
    }

    public void setPegreso(String pegreso) {
        this.pegreso = pegreso;
    }

    public String getPdiario() {
        return pdiario;
    }

    public void setPdiario(String pdiario) {
        this.pdiario = pdiario;
    }

    public Integer getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(Integer idorigen) {
        this.idorigen = idorigen;
    }

    public Integer getpIdorigen() {
        return pIdorigen;
    }

    public void setpIdorigen(Integer pIdorigen) {
        this.pIdorigen = pIdorigen;
    }

    public Integer getpIdgrupo() {
        return pIdgrupo;
    }

    public void setpIdgrupo(Integer pIdgrupo) {
        this.pIdgrupo = pIdgrupo;
    }

    public Integer getpIdsocio() {
        return pIdsocio;
    }

    public void setpIdsocio(Integer pIdsocio) {
        this.pIdsocio = pIdsocio;
    }

    public Date getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(Date fechalimite) {
        this.fechalimite = fechalimite;
    }

    /*
    public List<Integer> getProcpid() {
        return procpid;
    }

    public void setProcpid(List<Integer> procpid) {
        this.procpid = procpid;
    }
    */

    public String getImpticket() {
        return impticket;
    }

    public void setImpticket(String impticket) {
        this.impticket = impticket;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /*
    public List<String> getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(List<String> ipLocal) {
        this.ipLocal = ipLocal;
    }
    */

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idusuario);
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
        final UsuariosDTO other = (UsuariosDTO) obj;
        return Objects.equals(this.idusuario, other.idusuario);
    }

    @Override
    public String toString() {
        return "UsuariosDTO{" + "idusuario=" + idusuario + ", login=" + login + ", nombre=" + nombre + ", activo=" + activo + ", idorigenpDd=" + idorigenpDd + ", idproductoDd=" + idproductoDd + ", idauxiliarDd=" + idauxiliarDd + ", idorigenpAd=" + idorigenpAd + ", idproductoAd=" + idproductoAd + ", idauxiliarAd=" + idauxiliarAd + ", pingreso=" + pingreso + ", pegreso=" + pegreso + ", pdiario=" + pdiario + ", idorigen=" + idorigen + ", pIdorigen=" + pIdorigen + ", pIdgrupo=" + pIdgrupo + ", pIdsocio=" + pIdsocio + ", fechalimite=" + fechalimite + /*", procpid=" + procpid +*/ ", impticket=" + impticket + ", passwd=" + passwd + /*", ipLocal=" + ipLocal +*/ ", ticket=" + ticket + '}';
    }

}
