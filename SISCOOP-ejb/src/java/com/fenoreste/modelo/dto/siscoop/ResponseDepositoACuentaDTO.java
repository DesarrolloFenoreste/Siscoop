/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "responseDepositoACuentadto")
public class ResponseDepositoACuentaDTO implements Serializable{

    private String ResponseCode;
    private String IdTeller;
    private String IdAcct;
    private Date DateTime;
    private String Amount1;
    private String Amount2;
    private String Amount3;
    private String AutorizationNumber;
    private String Xtra1;
    private String Xtra2;
    private String Xtra3;
    private String Xtra4;
    private String Xtra5;
    private String Config;

    public ResponseDepositoACuentaDTO() {
    }
    
    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getIdTeller() {
        return IdTeller;
    }

    public void setIdTeller(String IdTeller) {
        this.IdTeller = IdTeller;
    }

    public String getIdAcct() {
        return IdAcct;
    }

    public void setIdAcct(String IdAcct) {
        this.IdAcct = IdAcct;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }

    public String getAmount1() {
        return Amount1;
    }

    public void setAmount1(String Amount1) {
        this.Amount1 = Amount1;
    }

    public String getAmount2() {
        return Amount2;
    }

    public void setAmount2(String Amount2) {
        this.Amount2 = Amount2;
    }

    public String getAmount3() {
        return Amount3;
    }

    public void setAmount3(String Amount3) {
        this.Amount3 = Amount3;
    }

    public String getAutorizationNumber() {
        return AutorizationNumber;
    }

    public void setAutorizationNumber(String AutorizationNumber) {
        this.AutorizationNumber = AutorizationNumber;
    }

    public String getXtra1() {
        return Xtra1;
    }

    public void setXtra1(String Xtra1) {
        this.Xtra1 = Xtra1;
    }

    public String getXtra2() {
        return Xtra2;
    }

    public void setXtra2(String Xtra2) {
        this.Xtra2 = Xtra2;
    }

    public String getXtra3() {
        return Xtra3;
    }

    public void setXtra3(String Xtra3) {
        this.Xtra3 = Xtra3;
    }

    public String getXtra4() {
        return Xtra4;
    }

    public void setXtra4(String Xtra4) {
        this.Xtra4 = Xtra4;
    }

    public String getXtra5() {
        return Xtra5;
    }

    public void setXtra5(String Xtra5) {
        this.Xtra5 = Xtra5;
    }

    public String getConfig() {
        return Config;
    }

    public void setConfig(String Config) {
        this.Config = Config;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.IdAcct);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseDepositoACuentaDTO other = (ResponseDepositoACuentaDTO) obj;
        return Objects.equals(this.IdAcct, other.IdAcct);
    }

    @Override
    public String toString() {
        return "ResponseDepositoACuenta{" + "ResponseCode=" + ResponseCode + ", IdTeller=" + IdTeller + ", IdAcct=" + IdAcct + ", DateTime=" + DateTime + ", Amount1=" + Amount1 + ", Amount2=" + Amount2 + ", Amount3=" + Amount3 + ", AutorizationNumber=" + AutorizationNumber + ", Xtra1=" + Xtra1 + ", Xtra2=" + Xtra2 + ", Xtra3=" + Xtra3 + ", Xtra4=" + Xtra4 + ", Xtra5=" + Xtra5 + ", Config=" + Config + '}';
    }

}
