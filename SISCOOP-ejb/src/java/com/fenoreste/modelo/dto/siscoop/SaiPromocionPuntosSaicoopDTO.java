/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "SaiPromocionPuntosSaicoopDTO")
public class SaiPromocionPuntosSaicoopDTO implements Serializable {

    private String ogs_nombre;
    private String puntos_ganados;
    private String puntos_globales;
    private String descalificacion;
    private String motivodesc;

    public SaiPromocionPuntosSaicoopDTO() {
    }

    public String getOgs_nombre() {
        return ogs_nombre;
    }

    public void setOgs_nombre(String ogs_nombre) {
        this.ogs_nombre = ogs_nombre;
    }

    public String getPuntos_ganados() {
        return puntos_ganados;
    }

    public void setPuntos_ganados(String puntos_ganados) {
        this.puntos_ganados = puntos_ganados;
    }

    public String getPuntos_globales() {
        return puntos_globales;
    }

    public void setPuntos_globales(String puntos_globales) {
        this.puntos_globales = puntos_globales;
    }

    public String getDescalificacion() {
        return descalificacion;
    }

    public void setDescalificacion(String descalificacion) {
        this.descalificacion = descalificacion;
    }

    public String getMotivodesc() {
        return motivodesc;
    }

    public void setMotivodesc(String motivodesc) {
        this.motivodesc = motivodesc;
    }

    @Override
    public String toString() {
        return "SaiPromocionPuntosSaicoopDTO{" + "ogs_nombre=" + ogs_nombre + ", puntos_ganados=" + puntos_ganados + ", puntos_globales=" + puntos_globales + ", descalificacion=" + descalificacion + ", motivodesc=" + motivodesc + '}';
    }

}
