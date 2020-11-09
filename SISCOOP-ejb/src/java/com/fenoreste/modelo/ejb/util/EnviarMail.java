/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import java.util.Date;
import javax.mail.Authenticator;
//import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author gerardo
 */
public class EnviarMail {

    // PARAMETROS: host, puerto, usuario, password, email, destinatario, tipo SSL o TLS, titulo del correo, mensaje
    public void enviar(String host, String puerto, String usuario, String password, String emailOrigen, String emailDestino, String tipoConexion, String titulo, String mensaje) {
        ThreadEnviarMail threadEnviarMail = new ThreadEnviarMail(host, puerto, usuario, password, emailOrigen, emailDestino, tipoConexion, titulo, mensaje);
        //System.out.println("EnviarMail de EnviarMail inicia el ThreadEnviarMail \n" + mensaje);
        threadEnviarMail.start();
    }

    class ThreadEnviarMail extends Thread {

        String host, puerto, usuario, password, emailOrigen, emailDestino, tipoConexion, titulo, mensaje;

        // PARAMETROS: host, puerto, usuario, password, email, destinatario, tipo SSL o TLS, titulo del correo, mensaje
        public ThreadEnviarMail(String host, String puerto, String usuario, String password, String emailOrigen, String emailDestino, String tipoConexion, String titulo, String mensaje) {
            this.host = host;
            this.puerto = puerto;
            this.usuario = usuario;
            this.password = password;
            this.emailOrigen = emailOrigen;
            this.emailDestino = emailDestino;
            this.tipoConexion = tipoConexion;
            this.titulo = titulo;
            this.mensaje = mensaje;
        }

        @Override
        public void run() {
            try {
                //System.out.println("EnviarMail de ThreadEnviarMail inicia el run \n" + mensaje);
                enviarMail(host, puerto, usuario, password, emailOrigen, emailDestino, tipoConexion, titulo, mensaje);
            } catch (Exception e) {
                System.out.println("Error en ThreadEnviarMail que envia el correo" + e);
            }
        }
    }

    // TLS
    // PARAMETROS: host, puerto, usuario, password, emailOrigen, destinatario, tipo SSL o TLS, titulo del correo, mensaje
    private void enviarMail(String host, String puerto, String usuario, String password, String emailOrigen, String emailDestino, String tipoConexion, String titulo, String mensaje) {

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);
        props.put("usuario", usuario);
        props.put("password", password);
        // TLS
        if ("TLS".equals(tipoConexion)) {
            props.put("mail.smtp.starttls.enable", "false");
            //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        } else {
            // SSL
            props.put("mail.smtp.socketFactory.port", puerto);
            //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "false");
        }
        //props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("usuario"), props.getProperty("password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailOrigen));
            // Estableciendo el destino (TO)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
            // Estableciendo el destino de la copia (CC)
            //message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailDestino));
            // Estableciendo el destino de la copia oculta (BCC)
            //message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailDestino));
            // Estableciendo el titulo del mensaje (subject)
            message.setSubject(titulo);
            message.setSentDate(new Date()); 

            // Estableciendo el contenido del correo electronico enriquesido(HTML)
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensaje, "text/html");
            /*
            // Se compone el adjunto
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/gerardo/kgro.config")));
            adjunto.setFileName("kgro.config");
             */
            // Una MultiParte para agrupar texto y adjunto
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(mimeBodyPart);
            //multiParte.addBodyPart(adjunto);

            // Se envia el mensaje al content
            message.setContent(multiParte);

            // Se envia el correo
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Error al enviar correo, no se envio al destinatario: " + emailDestino + "\n\n" + e);
        }

    }

}
