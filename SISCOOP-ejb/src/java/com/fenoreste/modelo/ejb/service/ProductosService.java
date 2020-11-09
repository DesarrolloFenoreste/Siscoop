/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.ProductosDTO;
import com.fenoreste.modelo.ejb.facade.ProductosFacade;
import com.fenoreste.modelo.ejb.interfaceService.ProductosServiceLocal;
import com.fenoreste.modelo.entidad.Productos;
import java.lang.reflect.InvocationTargetException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author gerardo
 */
@Stateless
public class ProductosService implements ProductosServiceLocal {

    @EJB
    private ProductosFacade productosFacade;

    EntityManager entity;

    @Override
    public ProductosDTO buscarProducto(Integer idproducto) {
        entity = productosFacade.getEntityManager();
        //EntityManagerFactory emf=productosFacade.getEntityManager();
        //entity=emf.createEntityManager();
        ProductosDTO productosDTO = new ProductosDTO();
        try {
            String consulta = " SELECT p.idproducto, reemplaza_letras(p.nombre) AS nombre, p.tipoproducto "
                    + "         FROM productos p"
                    + "         WHERE p.idproducto = ? ";
            Query query = entity.createNativeQuery(consulta, Productos.class);
            query.setParameter(1, idproducto);
            Productos productos = (Productos) query.getSingleResult();
            if (productos != null) {
                productosDTO = fromEntity2DTO(productos); 
            }
        } catch (Exception e) {
            System.out.println("Error en buscarProducto de ProductosService: " + e.getMessage());
        }
        entity.close();
        return productosDTO;
    }

    private ProductosDTO fromEntity2DTO(Productos pro) {
        ProductosDTO dto = new ProductosDTO();
        try {
            BeanUtils.copyProperties(dto, pro);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO de ProductosService");
        }
        return dto;
    }

}
