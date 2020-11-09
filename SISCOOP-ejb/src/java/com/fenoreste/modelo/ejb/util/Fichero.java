/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author prometeo
 */
public class Fichero {

    String fichero = "kgro.config";
    String nbd = "";
    String ipbd = "";

    public Fichero() {
        leeFichero();
    }

    private String getHome() {
        return System.getProperty("user.home");
    }

    private String getSeparador() {
        return System.getProperty("file.separator");
    }

    private File getFichero() {
        String sf = getHome() + getSeparador() + fichero;
        File f = new File(sf);
        if (f.exists()) {
            return f;
        } else {
            System.out.println("El fichero no existe: " + sf);
            return null;
        }
    }

    private void leeFichero() {
        if (getFichero() != null) {
            try {
                try (FileReader fr = new FileReader(getFichero())) {
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        establece(linea);
                    }
                }
            } catch (Exception e) {
                System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
            }
        } else {
            System.out.println("No se encontro el fichero.");
        }
    }

    private void establece(String linea) {
        if (linea.contains("base_de_datos")) {
            nbd = linea.split("=")[1];
        }
        if (linea.contains("ip_servidor")) {
            ipbd = linea.split("=")[1];
        }
    }

    public String getBd() {
        return nbd;
    }

    public String getIpbd() {
        return ipbd;
    }
}
