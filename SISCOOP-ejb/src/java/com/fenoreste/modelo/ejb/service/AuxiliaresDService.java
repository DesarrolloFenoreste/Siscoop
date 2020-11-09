/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.AuxiliaresdDTO;
import com.fenoreste.modelo.ejb.facade.AuxiliaresDFacade;
import com.fenoreste.modelo.ejb.interfaceService.AuxiliaresDServiceLocal;
import com.fenoreste.modelo.entidad.AuxiliaresD;
import com.fenoreste.modelo.entidad.AuxiliaresDPK;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
public class AuxiliaresDService implements AuxiliaresDServiceLocal {

    @EJB
    private AuxiliaresDFacade auxiliaresDFacade;

    EntityManager entity;

    @Override
    public AuxiliaresdDTO buscarAuxiliarD(AuxiliaresDPK auxiliar, int idtipo) {
        entity = auxiliaresDFacade.getEntityManager();
         //EntityManagerFactory emf=auxiliaresDFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        AuxiliaresdDTO auxiliaresdDTO = new AuxiliaresdDTO();
        try {
            String consulta = " SELECT a.* "
                    + "         FROM auxiliares_d a "
                    + "         WHERE a.idorigenp = ? "
                    + "           AND a.idproducto = ? "
                    + "           AND a.idauxiliar = ? "
                    + "           AND a.idtipo = ? "
                    + "         ORDER BY fecha DESC "
                    + "         LIMIT 1 ";
            Query query = entity.createNativeQuery(consulta, AuxiliaresD.class);
            query.setParameter(1, auxiliar.getIdorigenp());
            query.setParameter(2, auxiliar.getIdproducto());
            query.setParameter(3, auxiliar.getIdauxiliar());
            query.setParameter(4, idtipo);
            AuxiliaresD auxiliaresd = (AuxiliaresD) query.getSingleResult();
            if (auxiliaresd != null) {
                auxiliaresdDTO = fromEntity2DTO(auxiliaresd);
            }
        } catch (Exception e) {
            System.out.println("Error en buscaAuxiliaresD de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return auxiliaresdDTO;
    }

    @Override
    public List<AuxiliaresdDTO> buscaListaDeAuxiliaresD(int idorigenp, int idproducto, int idauxiliar, int maxResult) {
        entity = auxiliaresDFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresDFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        List<AuxiliaresdDTO> listAuxiliaresdDTO = new ArrayList<>(0);
        try {
            String consulta = " SELECT a.* "
                    + "         FROM auxiliares_d a"
                    + "         WHERE a.idorigenp = ? "
                    + "           AND a.idproducto = ? "
                    + "           AND a.idauxiliar = ? "
                    + "         ORDER BY fecha DESC "
                    + "         LIMIT ? ";
            Query query = entity.createNativeQuery(consulta, AuxiliaresD.class);
            query.setParameter(1, idorigenp);
            query.setParameter(2, idproducto);
            query.setParameter(3, idauxiliar);
            query.setParameter(4, maxResult);
            List<AuxiliaresD> auxiliaresD = query.getResultList();
            if (!auxiliaresD.isEmpty()) {
                auxiliaresD.stream().forEach((auxiliaresD1) -> {
                    listAuxiliaresdDTO.add(fromEntity2DTO((AuxiliaresD) auxiliaresD1));
                });
            }
        } catch (Exception e) {
            System.out.println("Error en buscaListaDeAuxiliaresD de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return listAuxiliaresdDTO;
    }

    @Override
    public BigDecimal saldoAuxiliaresD(int cargoabono, int idorigenp, int idproducto, int idauxiliar, int meses) {
        entity = auxiliaresDFacade.getEntityManager();
        //EntityManagerFactory emf=auxiliaresDFacade.getEntityManager();        
        //entity =emf.createEntityManager();
        BigDecimal auxiliaresD = new BigDecimal(-1);
        try {
            String consulta = " SELECT (CASE WHEN saldoCargoAbono IS NULL THEN 0.00 ELSE saldoCargoAbono END) AS saldoCargoAbono "
                    + "         FROM (SELECT SUM(saldoCargoAbono) AS saldoCargoAbono "
                    + "               FROM (SELECT SUM(CASE WHEN a.cargoabono = ? THEN a.monto ELSE 0.00 END) AS saldoCargoAbono "
                    + "                     FROM auxiliares_d a "
                    + "                     WHERE a.idorigenp = ? "
                    + "                       AND a.idproducto = ? "
                    + "                       AND a.idauxiliar = ? "
                    + "                       AND a.fecha BETWEEN (SELECT fechatrabajo FROM origenes LIMIT 1) AND ((SELECT fechatrabajo FROM origenes LIMIT 1) + text (? || ' month')::INTERVAL) "
                    + "                     GROUP BY cargoabono, fecha "
                    + "                     ORDER BY fecha DESC) AS a) AS a ";
            Query query = entity.createNativeQuery(consulta);
            query.setParameter(1, cargoabono);
            query.setParameter(2, idorigenp);
            query.setParameter(3, idproducto);
            query.setParameter(4, idauxiliar);
            query.setParameter(5, meses);
            auxiliaresD = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en buscaListaDeAuxiliaresD de AuxiliaresDService: " + e.getMessage());
        }
        entity.close();
        return auxiliaresD;
    }
    
    private AuxiliaresdDTO fromEntity2DTO(AuxiliaresD auxiliaresD) {
        AuxiliaresdDTO dto = new AuxiliaresdDTO();
        try {
            BeanUtils.copyProperties(dto, auxiliaresD);
        } catch (javax.ejb.TransactionRolledbackLocalException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al pasar Entity a DTO");
        }
        return dto;
    }

}
