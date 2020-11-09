/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import serviciosExternos.WsConnExternos;

/**
 *
 * @author prometeo
 */
@Stateful
@LocalBean
public class EnvioSMS1 {

    //String s = "http://192.168.47.134/CSNsms/action.php?mensaje=_mensaje&numero=_numero";
    ////http://192.168.47.134/CSNsms/action.php?mensaje="mensaje"&numero=8121599784
    /**
     *
     * @param url
     * @param numero
     * @param mensaje
     * @return
     */
    public String enviaMensajeACel(String url, String numero, String mensaje) {
        String r = "";
        if (url != null && numero != null && mensaje != null) {
            numero = numero.replace(" ", "");
            numero = numero.trim();
            mensaje = mensaje.replace(" ", "%20");
            mensaje = mensaje.trim();
            if (numero.length() == 10) {
                if (mensaje.length() <= 160) {
                    WsConnExternos wsConnExternos = new WsConnExternos();//esta es la clase en donde se genera una conexion y se lee lo que retorna
                    url = url.replace("_mensaje", mensaje);
                    url = url.replace("_numero", numero);
                    r = wsConnExternos.simpleConeccionURL(url);
                } else {
                    System.out.println("Error: Mensaje mayor a 160 caracteres en EnvioSMS" + r);//mensaje mayor a 160 caracteres
                }
            } else {
                System.out.println("Error: Numero invalido en EnvioSMS. " + r); //numero invalido
            }
        } else {
            System.out.println("url: " + url + "\nnumero: " + numero + "\nmensaje: " + mensaje);
        }
        return r;
    }

}
