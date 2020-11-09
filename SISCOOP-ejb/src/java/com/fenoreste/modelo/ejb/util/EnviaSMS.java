/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import serviciosExternos.WsConnExternos;

/**
 *
 * @author gerardo
 */
public class EnviaSMS {

    // PARAMETROS: host, numero, mensaje
    public void enviar(String host, String numero, String mensaje) {
        ThreadEnviarSMS threadEnviarSMS = new ThreadEnviarSMS(host, numero, mensaje);
        threadEnviarSMS.start();
    }

    class ThreadEnviarSMS extends Thread {

        String host, numero, mensaje;

        // PARAMETROS: host, puerto, usuario, password, email, destinatario, tipo SSL o TLS, titulo del correo, mensaje
        public ThreadEnviarSMS(String host, String numero, String mensaje) {
            this.host = host;
            this.numero = numero;
            this.mensaje = mensaje;
        }

        @Override
        public void run() {
            enviarSMS(host, numero, mensaje);
        }

    }

    // PARAMETROS: host, numero, mensaje
    private String enviarSMS(String host, String numero, String mensaje) {
        String r = "";
        if (host != null && numero != null && mensaje != null) {
            numero = numero.replace(" ", "");
            numero = numero.trim();
            mensaje = mensaje.replace(" ", "%20");
            mensaje = mensaje.trim();
            if (numero.length() == 10) {
                if (mensaje.length() <= 160) {
                    WsConnExternos wsConnExternos = new WsConnExternos();//esta es la clase en donde se genera una conexion y se lee lo que retorna
                    host = host.replace("_mensaje", mensaje);
                    host = host.replace("_numero", numero);
                    r = wsConnExternos.simpleConeccionURL(host);
                } else {
                    System.out.println("Error: Mensaje mayor a 160 caracteres en EnvioSMS" + r);//mensaje mayor a 160 caracteres
                }
            } else {
                System.out.println("Error: Numero invalido en EnvioSMS. " + r); //numero invalido
            }
        } else {
            System.out.println("url: " + host + "\nnumero: " + numero + "\nmensaje: " + mensaje);
        }
        return r;
    }

}
