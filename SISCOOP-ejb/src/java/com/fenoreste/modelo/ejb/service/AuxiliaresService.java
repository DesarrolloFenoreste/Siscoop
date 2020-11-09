/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.AuxiliaresDTO;
import com.fenoreste.modelo.ejb.facade.AuxiliaresFacade;
import com.fenoreste.modelo.ejb.interfaceService.AuxiliaresServiceLocal;
import com.fenoreste.modelo.entidad.Auxiliares;
import com.fenoreste.modelo.entidad.AuxiliaresPK;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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
public class AuxiliaresService implements AuxiliaresServiceLocal {

    @EJB
    private AuxiliaresFacade auxiliaresFacade;

    EntityManager entity;

    @Override
    public AuxiliaresDTO buscarAuxiliar(AuxiliaresPK auxiliar) {
        entity = auxiliaresFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        AuxiliaresDTO auxiliaresDTO = new AuxiliaresDTO();
        try {
            String consulta = " SELECT a.* "
                    + "         FROM auxiliares a"
                    + "         WHERE a.idorigenp = ? "
                    + "           AND a.idproducto = ? "
                    + "           AND a.idauxiliar = ? "
                    + "           AND a.estatus = 2 ";
            Query query = entity.createNativeQuery(consulta, Auxiliares.class);
            query.setParameter(1, auxiliar.getIdorigenp());
            query.setParameter(2, auxiliar.getIdproducto());
            query.setParameter(3, auxiliar.getIdauxiliar());
            Auxiliares auxiliares = (Auxiliares) query.getSingleResult();
            if (auxiliares != null) {
                auxiliaresDTO = fromEntity2DTO(auxiliares);
            }
        } catch (Exception e) {
            System.out.println("Error en buscarAuxiliar de AuxiliaresService: " + e.getMessage());
        }
        entity.close();
        return auxiliaresDTO;
    }

    @Override
    public List<AuxiliaresDTO> buscaAuxiliaresRango(int[] range) {
        entity = auxiliaresFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        List<AuxiliaresDTO> listAuxiliaresDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT a.* "
                    + "         FROM auxiliares a "
                    + "         WHERE a.estatus = 2 ";
            Query query = entity.createNativeQuery(consulta, Auxiliares.class);
            query.setMaxResults(range[1] - range[0] + 1);
            query.setFirstResult(range[0]);
            List amortizaciones = query.getResultList();
            if (!amortizaciones.isEmpty()) {
                amortizaciones.stream().forEach((amortizacion) -> {
                    listAuxiliaresDTO.add(fromEntity2DTO((Auxiliares) amortizacion));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaListaDeAuxiliaresD de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return listAuxiliaresDTO;
    }

    @Override
    public List<AuxiliaresDTO> buscaAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio) {
        entity = auxiliaresFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        List<AuxiliaresDTO> listAuxiliaresDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT a.* "
                    + "         FROM auxiliares a"
                    + "         WHERE a.idorigen = ? "
                    + "           AND a.idgrupo = ? "
                    + "           AND a.idsocio = ? "
                    + "           AND a.estatus = 2 ";
            Query query = entity.createNativeQuery(consulta, Auxiliares.class);
            query.setParameter(1, idorigen);
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsocio);
            List amortizaciones = query.getResultList();
            if (!amortizaciones.isEmpty()) {
                amortizaciones.stream().forEach((amortizacion) -> {
                    listAuxiliaresDTO.add(fromEntity2DTO((Auxiliares) amortizacion));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaAuxiliarPorOGS de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return listAuxiliaresDTO;
    }

    @Override
    public boolean existeAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio, int idproducto) {
        entity = auxiliaresFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        boolean existe = false;
        try {
            // Estatus 0 porque es para validar si tiene acceso a banca movil
            String consulta = " SELECT *FROM "
                    + "auxiliares "
                    + "WHERE idorigen = ? "
                    + "AND idgrupo = ? "
                    + "AND idsocio = ? "
                    + "AND idproducto = ? "
                    + "AND estatus = 0 "
                    + "LIMIT 1; ";
            Query query = entity.createNativeQuery(consulta, Auxiliares.class);
            query.setParameter(1, idorigen);
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsocio);
            query.setParameter(4, idproducto);
            Auxiliares auxiliares = (Auxiliares) query.getSingleResult();
            if (auxiliares != null) {
                existe = true;
            }
        } catch (Exception e) {
            System.out.println("Error en existeAuxiliarPorOGS de AuxiliaresService: " + e.getMessage());
        }
        entity.close();
        return existe;
    }

    @Override
    public AuxiliaresDTO buscaAuxiliarPorOGS(int idorigen, int idgrupo, int idsocio, int idproducto) {
        entity = auxiliaresFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        AuxiliaresDTO auxdto = new AuxiliaresDTO();
        try {
            String consulta = "SELECT a.* "
                    + "         FROM auxiliares a"
                    + "         WHERE a.idorigen = ? "
                    + "           AND a.idgrupo = ? "
                    + "           AND a.idsocio = ?"
                    + "           AND a.idproducto= ? limit 1;";
            Query query = entity.createNativeQuery(consulta, Auxiliares.class);
            query.setParameter(1, idorigen);
            query.setParameter(2, idgrupo);
            query.setParameter(3, idsocio);
            query.setParameter(4, idproducto);
            //System.out.println(query);
            List<Auxiliares> auxiliares = (List<Auxiliares>) query.getResultList();
            if (!auxiliares.isEmpty()) {
                for (int i = 0; i < auxiliares.size(); i++) {
                    Auxiliares auxiliar = auxiliares.get(i);
                    if (auxiliar != null) {
                        Auxiliares aux = (Auxiliares) auxiliar;
                        auxdto = fromEntity2DTO(aux);
                        //System.out.println("******************************************************************************");
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getAuxiliaresPK().getIdorigenp());
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getAuxiliaresPK().getIdproducto());
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getAuxiliaresPK().getIdauxiliar());
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getIdorigen());
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getIdgrupo());
                        //System.out.println("buscaAuxiliarPorOGSyp " + auxdto.getIdsocio());
                        //System.out.println("******************************************************************************");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en buscaAuxiliarPorOGS de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return auxdto;
    }

    private AuxiliaresDTO fromEntity2DTO(Auxiliares auxiliares) {
        AuxiliaresDTO dto = new AuxiliaresDTO();
        try {
            BeanUtils.copyProperties(dto, auxiliares);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO, AuxiliaresService");
        }
        return dto;
    }
}
