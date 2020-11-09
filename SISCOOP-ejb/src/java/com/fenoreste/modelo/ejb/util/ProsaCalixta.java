/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import com.auronix.calixta.GatewayException;
import com.auronix.calixta.sms.SMSGateway;

/**
 *
 * @author prometeo
 */
public class ProsaCalixta {

    String clientid;
    String password;
    String user;
    String protocol;
    String url;
    int port;

    public ProsaCalixta(String clientid, String password, String user, String protocol, String url, int port) {
        this.clientid = clientid;
        this.password = password;
        this.user = user;
        this.protocol = protocol;
        this.url = url;
        this.port = port;
    }

    public void enviar(String numCel, String msj) {
        Thread tsms = new Thread(new Runnable() {
            @Override
            public void run() {
                Envia envia = new Envia(numCel, msj);
            }
        });
        tsms.start();

    }

    class Envia {
        public Envia(String numCel, String msfj) {
            System.out.println("enviando mensaje");
            numCel = ((numCel.replace(" ", "")).replace("-", "")).trim();
            //msfj = msfj.replace(" ", "%20");
            try {
                SMSGateway smsGateway = new SMSGateway(clientid, password, user, protocol, url, port);
                int idEnvio = smsGateway.sendMessageOL(numCel, msfj);
                System.out.println("mensaje enviado.-"+idEnvio);
            } catch (GatewayException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
