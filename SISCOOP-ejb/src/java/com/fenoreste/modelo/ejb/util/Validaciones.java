/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.util;

import com.fenoreste.modelo.dto.TablasDTO;
import com.fenoreste.modelo.ejb.interfaceService.TablasServiceLocal;
import com.fenoreste.modelo.entidad.TablasPK;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author prometeo
 */
@Stateful
@LocalBean
public class Validaciones {

    @EJB
    private Convertidor convertidor;
    @EJB
    private TablasServiceLocal tablasService;

    public boolean validaOPA(String opa32) {
        boolean valido = false;
        if (opa32.length() == 32 && opa32.contains("0000000000000")) {
            valido = true;
        }
        return valido;
    }

    public boolean validaOGS(String ogs32) {
        boolean valido = false;
        //if (ogs32.length() == 32 && ogs32.contains("000000000000000000")) {
        if (ogs32.length() == 32) {
            valido = true;
        }
        return valido;
    }

    public int getOrigen4_OGS_OPA(String cadena) {
        if (validaOPA(cadena) == true) {
            return convertidor.getOPA(cadena)[0];
        } else {
            return convertidor.getOGS(cadena)[0];
        }
    }

    public boolean existeEnListaDeArregloDeString(List<String[]> lista, int lugar, String cadena) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[lugar].equals(cadena)) {
                return true;
            }
        }
        return false;
    }

    public boolean exiteMontoMayorACero(Double[] arr) {
        for (Double arr1 : arr) {
            if (arr1 > 0) {
                return true;
            }
        }
        return false;
    }

    // Valida el usuario y contraseña que manda cada metodo al webservice
    public boolean validaUsuarioMetodo(String idTabla, String user, String pass) {
        try {
            TablasPK tpkcon = new TablasPK(idTabla, "usuario");
            TablasDTO tabcon = tablasService.buscaTabla(tpkcon);
            if (tabcon.getTablasPK() != null) {
                return tabcon.getDato1().equals(user) && tabcon.getDato2().equals(pass);
            }
        } catch (Exception e) {
            System.out.println("Error al validar usuario en validaUsuarioMetodo de Validaciones. " + e.getMessage());
        }
        return false;
    }

}
