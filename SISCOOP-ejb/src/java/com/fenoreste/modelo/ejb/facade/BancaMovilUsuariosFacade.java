/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.facade;

import com.fenoreste.modelo.ejb.impl.AbstractFacade;
import com.fenoreste.modelo.entidad.BancaMovilUsuarios;
import javax.ejb.Stateless;

/**
 *
 * @author gerardo
 */
@Stateless
public class BancaMovilUsuariosFacade extends AbstractFacade<BancaMovilUsuarios> {

    /*
    @PersistenceContext(unitName = "CajeroReceptor-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    */
    
    public BancaMovilUsuariosFacade() {
        super(BancaMovilUsuarios.class);
    }

}
