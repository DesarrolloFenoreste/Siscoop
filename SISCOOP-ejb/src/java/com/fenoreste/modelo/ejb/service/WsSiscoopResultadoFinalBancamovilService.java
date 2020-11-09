/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.ejb.service;

import com.fenoreste.modelo.dto.tdd.WsSiscoopResultadoFinalBancamovilDTO;
import com.fenoreste.modelo.ejb.facade.WsSiscoopResultadoFinalBancamovilFacade;
import com.fenoreste.modelo.ejb.interfaceService.WsSiscoopResultadoFinalBancamovilServiceLocal;
import com.fenoreste.modelo.entidad.WsSiscoopResultadoFinalBancamovil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gerardo
 */
@Stateless
public class WsSiscoopResultadoFinalBancamovilService implements WsSiscoopResultadoFinalBancamovilServiceLocal {

    @EJB
    private WsSiscoopResultadoFinalBancamovilFacade wsSiscoopResultadoFinalBancamovilFacade;

    @Override
    public int insertaResponse(WsSiscoopResultadoFinalBancamovilDTO wsSiscoopResultadoFinalBancamovilDTO) {
        WsSiscoopResultadoFinalBancamovil wsSiscoopResultadoFinalBancamovil = new WsSiscoopResultadoFinalBancamovil();
        wsSiscoopResultadoFinalBancamovil.setWsSiscoopResultadoFinalBancamovilPK(wsSiscoopResultadoFinalBancamovilDTO.getWsSiscoopResultadoFinalBancamovilPK());
        wsSiscoopResultadoFinalBancamovil.setIdusuario(wsSiscoopResultadoFinalBancamovilDTO.getIdusuario());
        wsSiscoopResultadoFinalBancamovil.setEnvio(wsSiscoopResultadoFinalBancamovilDTO.getEnvio());
        wsSiscoopResultadoFinalBancamovil.setRespuesta(wsSiscoopResultadoFinalBancamovilDTO.getRespuesta());
        int n = wsSiscoopResultadoFinalBancamovilFacade.inserta(wsSiscoopResultadoFinalBancamovil);
        return n;
    }

}
