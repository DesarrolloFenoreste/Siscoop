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
@XmlRootElement(name = "DatoClienteDTO")
public class DatoClienteDTO implements Serializable {

    private String ResponseCode;
    private String IdClient;
    private String ClientType;
    private String Name;
    private String LastName;
    private String MaidenName;
    private String Adress1;
    private String Adress2;
    private String City;
    private String State;
    private String ZIP;
    private String Country;
    private String Phone1;
    private String Phone2;
    private String Mobile;
    private String email;
    private String Gerender;
    private Date Date1;
    private Date Date2;
    private String SSN1;
    private String SSN2;
    private String Xtra1;
    private String Xtra2;
    private String Xtra3;
    private String Xtra4;
    private String Xtra5;
    private String Status;
    private String ShowConf;

    public DatoClienteDTO() {
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

    public String getClientType() {
        return ClientType;
    }

    public void setClientType(String ClientType) {
        this.ClientType = ClientType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getMaidenName() {
        return MaidenName;
    }

    public void setMaidenName(String MaidenName) {
        this.MaidenName = MaidenName;
    }

    public String getAdress1() {
        return Adress1;
    }

    public void setAdress1(String Adress1) {
        this.Adress1 = Adress1;
    }

    public String getAdress2() {
        return Adress2;
    }

    public void setAdress2(String Adress2) {
        this.Adress2 = Adress2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String Phone2) {
        this.Phone2 = Phone2;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGerender() {
        return Gerender;
    }

    public void setGerender(String Gerender) {
        this.Gerender = Gerender;
    }

    public Date getDate1() {
        return Date1;
    }

    public void setDate1(Date Date1) {
        this.Date1 = Date1;
    }

    public Date getDate2() {
        return Date2;
    }

    public void setDate2(Date Date2) {
        this.Date2 = Date2;
    }

    public String getSSN1() {
        return SSN1;
    }

    public void setSSN1(String SSN1) {
        this.SSN1 = SSN1;
    }

    public String getSSN2() {
        return SSN2;
    }

    public void setSSN2(String SSN2) {
        this.SSN2 = SSN2;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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
        hash = 47 * hash + Objects.hashCode(this.IdClient);
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
        final DatoClienteDTO other = (DatoClienteDTO) obj;
        return Objects.equals(this.IdClient, other.IdClient);
    }

    @Override
    public String toString() {
        return "DatoClienteDTO{" + "ResponseCode=" + ResponseCode + ", IdClient=" + IdClient + ", ClientType=" + ClientType + ", Name=" + Name + ", LastName=" + LastName + ", MaidenName=" + MaidenName + ", Adress1=" + Adress1 + ", Adress2=" + Adress2 + ", City=" + City + ", State=" + State + ", ZIP=" + ZIP + ", Country=" + Country + ", Phone1=" + Phone1 + ", Phone2=" + Phone2 + ", Mobile=" + Mobile + ", email=" + email + ", Gerender=" + Gerender + ", Date1=" + Date1 + ", Date2=" + Date2 + ", SSN1=" + SSN1 + ", SSN2=" + SSN2 + ", Xtra1=" + Xtra1 + ", Xtra2=" + Xtra2 + ", Xtra3=" + Xtra3 + ", Xtra4=" + Xtra4 + ", Xtra5=" + Xtra5 + ", Status=" + Status + ", ShowConf=" + ShowConf + '}';
    }

}
