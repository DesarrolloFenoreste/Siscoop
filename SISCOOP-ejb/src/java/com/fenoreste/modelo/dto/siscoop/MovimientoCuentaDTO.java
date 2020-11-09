/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "MovimientoCuentaDTO")
public class MovimientoCuentaDTO implements Serializable {

    String ResponseCode;
    String IdAcct;
    String IdAcctType;
    String IdTxType;
    Date TxDate;
    BigDecimal TxAmount;
    String Description;
    BigDecimal Fee;
    String DescriptionFee;
    String Config;

    public MovimientoCuentaDTO() {
    }

    public MovimientoCuentaDTO(String ResponseCode,
            String IdAcct,
            String IdAcctType,
            String IdTxType,
            Date TxDate,
            BigDecimal TxAmount,
            String Description,
            BigDecimal Fee,
            String DescriptionFee,
            String Config) {
        this.ResponseCode = ResponseCode;
        this.IdAcct = IdAcct;
        this.IdAcctType = IdAcctType;
        this.IdTxType = IdTxType;
        this.TxDate = TxDate;
        this.TxAmount = TxAmount;
        this.Description = Description;
        this.Fee = Fee;
        this.DescriptionFee = DescriptionFee;
        this.Config = Config;
    }

    public MovimientoCuentaDTO(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getIdAcct() {
        return IdAcct;
    }

    public void setIdAcct(String IdAcct) {
        this.IdAcct = IdAcct;
    }

    public String getIdAcctType() {
        return IdAcctType;
    }

    public void setIdAcctType(String IdAcctType) {
        this.IdAcctType = IdAcctType;
    }

    public String getIdTxType() {
        return IdTxType;
    }

    public void setIdTxType(String IdTxType) {
        this.IdTxType = IdTxType;
    }

    public Date getTxDate() {
        return TxDate;
    }

    public void setTxDate(Date TxDate) {
        this.TxDate = TxDate;
    }

    public BigDecimal getTxAmount() {
        return TxAmount;
    }

    public void setTxAmount(BigDecimal TxAmount) {
        this.TxAmount = TxAmount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public BigDecimal getFee() {
        return Fee;
    }

    public void setFee(BigDecimal Fee) {
        this.Fee = Fee;
    }

    public String getDescriptionFee() {
        return DescriptionFee;
    }

    public void setDescriptionFee(String DescriptionFee) {
        this.DescriptionFee = DescriptionFee;
    }

    public String getConfig() {
        return Config;
    }

    public void setConfig(String Config) {
        this.Config = Config;
    }
    
    @Override
    public String toString() {
        return "MovimientoCuentaDTO{" + "ResponseCode=" + ResponseCode + ", IdAcct=" + IdAcct + ", IdAcctType=" + IdAcctType + ", IdTxType=" + IdTxType + ", TxDate=" + TxDate + ", TxAmount=" + TxAmount + ", Description=" + Description + ", Fee=" + Fee + ", DescriptionFee=" + DescriptionFee + ", Config=" + Config + '}';
    }
    
}
