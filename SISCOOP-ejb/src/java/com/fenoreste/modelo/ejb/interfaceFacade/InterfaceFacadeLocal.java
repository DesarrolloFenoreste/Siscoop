/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.interfaceFacade;

import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author gerardo
 * @param <EntityClass>
 */
@LocalBean
public interface InterfaceFacadeLocal<EntityClass> {

    int inserta(EntityClass entityClass);

    int actualiza(EntityClass entityClass);

    int elimina(EntityClass entityClass);

    EntityClass find(Object id);

    List<EntityClass> findAll();

    List<EntityClass> findRange(int[] range);

    int count();

}
