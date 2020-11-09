/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import java.util.StringTokenizer;

/**
 *
 * @author gerardo
 */
public class Seguridad {

    // Solo muestra el primer caracter el resto lo reemplaza con *
    public String enmascararCadenaIni(String cadena) {
        String cadenaFinal = "";
        StringTokenizer stNombre = new StringTokenizer(cadena, " ", true);
        while (stNombre.hasMoreTokens()) {
            String cadenaOriginal = stNombre.nextToken();
            cadenaFinal = cadenaFinal + cadenaOriginal.charAt(0);
            for (int i = 2; cadenaOriginal.length() >= i; i++) {
                cadenaFinal = cadenaFinal + "*";
            }
        }
        return cadenaFinal;
    }

    // Solo muestra el ultimo caracter el resto lo reemplaza con *
    public String enmascararCadenaFin(String cadena) {
        String cadenaFinal = "";
        StringTokenizer stNombre = new StringTokenizer(cadena, " ", true);
        while (stNombre.hasMoreTokens()) {
            String cadenaOriginal = stNombre.nextToken();
            for (int i = 1; cadenaOriginal.length() - 1 >= i; i++) {
                cadenaFinal = cadenaFinal + "*";
            }
            cadenaFinal = cadenaFinal + cadenaOriginal.charAt(cadenaOriginal.length() - 1);
        }
        return cadenaFinal;
    }

}
