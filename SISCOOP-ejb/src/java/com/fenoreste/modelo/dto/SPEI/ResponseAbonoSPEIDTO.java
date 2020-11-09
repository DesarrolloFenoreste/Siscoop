/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.SPEI;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "responseabonospeidto")
public class ResponseAbonoSPEIDTO implements Serializable {

    private String ResponseCode;

    public ResponseAbonoSPEIDTO() {
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    @Override
    public String toString() {
        return "ResponseAbonoSPEIDTO{" + "ResponseCode=" + ResponseCode + '}';
    }

}
