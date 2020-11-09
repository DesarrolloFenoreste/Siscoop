/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto.siscoop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prometeo
 */
@XmlRootElement(name = "CuentaUsuarioDto")
public class CuentaUsuarioDTO implements Serializable {

    private String ResponseCode;            // Codigo de respuesta de errores
    private String IdClient;                // Identificador del cliente (ogs)
    private String AcctType;                // Tipo de cuenta
    private String IdAcct;                  // Identificador de la cuenta (opa)
    private String AcctStatus;              // Status de la cuenta
    private String Description;             // Nombre de la tabla del producto
    private BigDecimal MaxAmountDeposit;    // Monto maximo permitido a depositar
    private String AvailBalance;            // Saldo  de la tabla auxiliares
    private Date CourtDate;                 // Ahorro no tiene fecha de corte prestamos es una funcion
    private String NextPayAmount;           // Es una funcion
    private String TotalAmount;             // Lo mismo que el saldo
    private String Xtra1;                   // Xtra1
    private String Xtra2;                   // Xtra2
    private String Xtra3;                   // Xtra3
    private String Xtra4;                   // Xtra4
    private String Xtra5;                   // Xtra5
    private String config;                  // Este valor sale de tablas idelemento= ws_get_account_req

    public CuentaUsuarioDTO() {
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getIdClient() {
        return IdClient;
    }

    public void setIdClient(String IdClient) {
        this.IdClient = IdClient;
    }

    public String getAcctType() {
        return AcctType;
    }

    public void setAcctType(String AcctType) {
        this.AcctType = AcctType;
    }

    public String getIdAcct() {
        return IdAcct;
    }

    public void setIdAcct(String IdAcct) {
        this.IdAcct = IdAcct;
    }

    public String getAcctStatus() {
        return AcctStatus;
    }

    public void setAcctStatus(String AcctStatus) {
        this.AcctStatus = AcctStatus;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public BigDecimal getMaxAmountDeposit() {
        return MaxAmountDeposit;
    }

    public void setMaxAmountDeposit(BigDecimal MaxAmountDeposit) {
        this.MaxAmountDeposit = MaxAmountDeposit;
    }

    public String getAvailBalance() {
        return AvailBalance;
    }

    public void setAvailBalance(String AvailBalance) {
        this.AvailBalance = AvailBalance;
    }

    public Date getCourtDate() {
        return CourtDate;
    }

    public void setCourtDate(Date CourtDate) {
        this.CourtDate = CourtDate;
    }

    public String getNextPayAmount() {
        return NextPayAmount;
    }

    public void setNextPayAmount(String NextPayAmount) {
        this.NextPayAmount = NextPayAmount;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String TotalAmount) {
        this.TotalAmount = TotalAmount;
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
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.IdAcct);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CuentaUsuarioDTO other = (CuentaUsuarioDTO) obj;
        return Objects.equals(this.IdAcct, other.IdAcct);
    }


    @Override
    public String toString() {
        return "CuentaUsuarioDTO{" + "ResponseCode=" + ResponseCode + ", IdClient=" + IdClient + ", AcctType=" + AcctType + ", IdAcct=" + IdAcct + ", AcctStatus=" + AcctStatus + ", Description=" + Description + ", MaxAmountDeposit=" + MaxAmountDeposit + ", AvailBalance=" + AvailBalance + ", CourtDate=" + CourtDate + ", NextPayAmount=" + NextPayAmount + ", TotalAmount=" + TotalAmount + ", Xtra1=" + Xtra1 + ", Xtra2=" + Xtra2 + ", Xtra3=" + Xtra3 + ", Xtra4=" + Xtra4 + ", Xtra5=" + Xtra5 + ", config=" + config + '}';
    }

}
