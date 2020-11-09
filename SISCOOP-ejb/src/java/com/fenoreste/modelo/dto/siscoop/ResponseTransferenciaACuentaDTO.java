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
@XmlRootElement(name = "responsetransferenciacuentadto")
public class ResponseTransferenciaACuentaDTO implements Serializable {

    private String ResponseCode;
    private String IdTeller;
    private String IdAcctO;
    private String IdAcctD;
    private Date DateTime;
    private String Amount1;
    private String Amount2;
    private String AutorizationNumber;
    private String Description;
    private String Fee;
    private String FeeDescription;
    private String Xtra1;
    private String Xtra2;
    private String Xtra3;
    private String Xtra4;
    private String Xtra5;
    private String Config;

    public ResponseTransferenciaACuentaDTO() {
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

    public String getIdAcctO() {
        return IdAcctO;
    }

    public void setIdAcctO(String IdAcctO) {
        this.IdAcctO = IdAcctO;
    }

    public String getIdAcctD() {
        return IdAcctD;
    }

    public void setIdAcctD(String IdAcctD) {
        this.IdAcctD = IdAcctD;
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

    public String getAutorizationNumber() {
        return AutorizationNumber;
    }

    public void setAutorizationNumber(String AutorizationNumber) {
        this.AutorizationNumber = AutorizationNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public String getFeeDescription() {
        return FeeDescription;
    }

    public void setFeeDescription(String FeeDescription) {
        this.FeeDescription = FeeDescription;
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.IdTeller);
        hash = 89 * hash + Objects.hashCode(this.IdAcctD);
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
        final ResponseTransferenciaACuentaDTO other = (ResponseTransferenciaACuentaDTO) obj;
        if (!Objects.equals(this.IdTeller, other.IdTeller)) {
            return false;
        }
        if (!Objects.equals(this.IdAcctO, other.IdAcctO)) {
            return false;
        }
        return Objects.equals(this.IdAcctD, other.IdAcctD);
    }

    @Override
    public String toString() {
        return "ResponseTransferenciaACuentaDTO{" + "ResponseCode=" + ResponseCode + ", IdTeller=" + IdTeller + ", IdAcctO=" + IdAcctO + ", IdAcctD=" + IdAcctD + ", DateTime=" + DateTime + ", Amount1=" + Amount1 + ", Amount2=" + Amount2 + ", AutorizationNumber=" + AutorizationNumber + ", Description=" + Description + ", Fee=" + Fee + ", FeeDescription=" + FeeDescription + ", Xtra1=" + Xtra1 + ", Xtra2=" + Xtra2 + ", Xtra3=" + Xtra3 + ", Xtra4=" + Xtra4 + ", Xtra5=" + Xtra5 + ", Config=" + Config + '}';
    }

}
