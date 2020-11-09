/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosExternos;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author prometeo
 */
public class WsConnExternos {

    public String simpleConeccionURL(String urlString) {
        String inputLine = null;
        try {
            // Creo un objeto url con la cadena
            URL url = new URL(urlString);
            // Creo un objeto HttpURLConnection para conectarme
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            // El tiempo de espera para que se conecte
            con.setConnectTimeout(6000); 
            // Este es el tiempo de espera de la lectura de lo que retorno en caso de que retorne
            con.setReadTimeout(3000); 
            //Si la conexion fue exitosa
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) { 
                System.out.println("No se envio el mensaje, error en conexion. ");
            }
        } catch (Exception e) {
            return "Exception en WsConnExternos.simpleConeccionURL " + e.getMessage();
        }
        return inputLine;
    }

}
