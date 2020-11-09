/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.impl;

import com.fenoreste.modelo.ejb.util.Fichero;
import com.fenoreste.modelo.ejb.util.JpaUtil;
//import java.sql.Connection;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gerardo
 * @param <T>
 */
@TransactionManagement(TransactionManagementType.BEAN)
public abstract class AbstractFacade<T> {

    private EntityManager em;
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    //protected abstract EntityManager getEntityManager();
    public static EntityManager getEntityManager() {
        Fichero f = new Fichero();
        EntityManager ems=new JpaUtil().createEntityManager(f.getIpbd(),f.getBd());
        return ems;
    }
    
    
    

    /*
    //protected abstract EntityManager getEntityManager();
    public Connection getConnection() {
        Fichero f = new Fichero();
        return new JpaUtil().getConnection(f.getIpbd(), f.getBd());
    }
    */

    public int inserta(T entity) {
       //EntityManagerFactory emf=getEntityManager();
        //em = emf.createEntityManager();
        em=getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en inserta de AbstractFacadeSAICoop tipo : " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
    }

    public int actualiza(T entity) {
        //EntityManagerFactory emf=getEntityManager();
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en actualiza de AbstractFacadeSAICoop tipo : " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
    }

    public int elimina(T entity) {
        //EntityManagerFactory emf=getEntityManager();
        em = getEntityManager();//emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en elimina de AbstractFacadeSAICoop tipo : " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
    }

    public T find(Object id) {
        //EntityManagerFactory emf=getEntityManager();
        em =getEntityManager();// emf.createEntityManager();
        T find = em.find(entityClass, id);
        em.close();
        return find;
    }

    public List<T> findAll() {
       // EntityManagerFactory emf=getEntityManager();
        em =getEntityManager();// emf.createEntityManager();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List lista = em.createQuery(cq).getResultList();
        em.close();
        return lista;
    }

    public List<T> findRange(int[] range) {
        //EntityManagerFactory emf=getEntityManager();
        em = getEntityManager();// emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        List rango = q.getResultList();
        em.close();
        return rango;
    }

    public int count() {
        //EntityManagerFactory emf=getEntityManager();
        em = getEntityManager();// emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        int conteo = ((Long) q.getSingleResult()).intValue();
        em.close();
        return conteo;
    }

}
