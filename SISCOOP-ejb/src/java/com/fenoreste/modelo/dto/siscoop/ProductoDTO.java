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
 * @author gerardo
 */
@XmlRootElement(name = "ProductoDTO")
public class ProductoDTO implements Serializable {

    private String ResponseCode;        // Codigo de respuesta (errores) tabla CR
    private String IdClient;            // Identificador del cliente (ogs) formado de 32 digitos
    private String AcctType;            // Tipo de cuenta (00=TODOS 10=AHORRO, 20=PRESTAMOS)
    private String IdAcct;              // Identificador de cuenta (opa) formado por 32 digitos
    private String AcctStatus;          // Status de la cuenta (0=activo 1=bloqueado)
    private String Description;         // Descripcion de la cuenta (alias)
    private String MaxAmountDeposit;    // Monto maximo permitido para abono
    private String AvailBalance;        // Saldo disponible
    private Date CourtDate;             // Fecha de corte (yyyy/mm/dd)
    private String NextPayAmount;       // Proximo pago
    private String TotalAmount;         // Saldo total
    private String Xtra1;               // Campo extra 1
    private String Xtra2;               // Campo extra 2
    private String Xtra3;               // Campo extra 3
    private String Xtra4;               // Campo extra 4
    private String Xtra5;               // Campo extra 5
    private String ShowConf;            // Muestra campos de 1 al 14 (0=oculto 1=mostrar)

    public ProductoDTO() {
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

    public String getMaxAmountDeposit() {
        return MaxAmountDeposit;
    }

    public void setMaxAmountDeposit(String MaxAmountDeposit) {
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

    public String getShowConf() {
        return ShowConf;
    }

    public void setShowConf(String ShowConf) {
        this.ShowConf = ShowConf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.IdAcct);
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
        final ProductoDTO other = (ProductoDTO) obj;
        return Objects.equals(this.IdAcct, other.IdAcct);
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "ResponseCode=" + ResponseCode + ", IdClient=" + IdClient + ", AcctType=" + AcctType + ", IdAcct=" + IdAcct + ", AcctStatus=" + AcctStatus + ", Description=" + Description + ", MaxAmountDeposit=" + MaxAmountDeposit + ", AvailBalance=" + AvailBalance + ", CourtDate=" + CourtDate + ", NextPayAmount=" + NextPayAmount + ", TotalAmount=" + TotalAmount + ", Xtra1=" + Xtra1 + ", Xtra2=" + Xtra2 + ", Xtra3=" + Xtra3 + ", Xtra4=" + Xtra4 + ", Xtra5=" + Xtra5 + ", ShowConf=" + ShowConf + '}';
    }

}
