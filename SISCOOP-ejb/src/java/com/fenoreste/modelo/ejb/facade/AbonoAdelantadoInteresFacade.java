/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.facade;

/**
 *
 * @author prometeo
 */
import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.entidad.AbonoAdelantadoInteres;
import javax.ejb.Stateless;

@Stateless
public class AbonoAdelantadoInteresFacade extends AbstractFacade<AbonoAdelantadoInteres> {

    public AbonoAdelantadoInteresFacade() {
        super(AbonoAdelantadoInteres.class);
    }

}
