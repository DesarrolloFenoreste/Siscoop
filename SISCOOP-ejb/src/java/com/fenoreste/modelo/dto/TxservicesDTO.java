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
 * @author prometeo
 */
@XmlRootElement(name = "txservicesDTO")
public class TxservicesDTO implements Serializable {

    private String idservice;
    private String valor;
    private String comentario;

    public TxservicesDTO() {
    }

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) {
        this.idservice = idservice;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.idservice);
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
        final TxservicesDTO other = (TxservicesDTO) obj;
        return Objects.equals(this.idservice, other.idservice);
    }
    
    @Override
    public String toString() {
        return "TxservicesDTO{" + "idservice=" + idservice + ", valor=" + valor + ", comentario=" + comentario + '}';
    }
    
}
