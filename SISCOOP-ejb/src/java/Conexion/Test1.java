/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.fenoreste.modelo.dto.siscoop.DatoClienteDTO;
import com.fenoreste.modelo.entidad.Personas;
import com.fenoreste.modelo.entidad.PersonasPK;

/**
 *
 * @author wilmer
 */
public class Test1 {
       
    public static void main(String[] args) {
        DatoClienteDTO dto=new DatoClienteDTO();
        System.out.println("nombre:"+dto.getName());
    }
}
