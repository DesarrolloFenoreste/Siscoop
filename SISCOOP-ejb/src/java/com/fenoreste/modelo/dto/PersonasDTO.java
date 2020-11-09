/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenoreste.modelo.dto;

import com.fenoreste.modelo.entidad.PersonasPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gerardo
 */
@XmlRootElement(name = "personas")
public class PersonasDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected PersonasPK personasPK;
    private String calle;
    private String numeroext;
    private String numeroint;
    private String entrecalles;
    private Date fechanacimiento;
    private String lugarnacimiento;
    private Integer efnacimiento;
    private Short sexo;
    private String telefono;
    private String telefonorecados;
    private Boolean listanegra;
    private Short estadocivil;
    private String idcoop;
    private Integer idsector;
    private Boolean estatus;
    private Boolean aceptado;
    private Date fechaingreso;
    private Date fecharetiro;
    private Date fechaciudad;
    private Short regimenMat;
    private String nombre;
    private Short medioInf;
    private Integer requisitos;
    private String appaterno;
    private String apmaterno;
    private Short nacionalidad;
    private Short gradoEstudios;
    private Short categoria;
    private String rfc;
    private String curp;
    private String email;
    private String razonSocial;
    private Integer causaBaja;
    private Short nivelRiesgo;
    private String celular;
    private Boolean rfcValido;
    private Boolean curpValido;
    private Integer idcolonia;

    public PersonasDTO() {
    }

    public PersonasPK getPersonasPK() {
        return personasPK;
    }

    public void setPersonasPK(PersonasPK personasPK) {
        this.personasPK = personasPK;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroext() {
        return numeroext;
    }

    public void setNumeroext(String numeroext) {
        this.numeroext = numeroext;
    }

    public String getNumeroint() {
        return numeroint;
    }

    public void setNumeroint(String numeroint) {
        this.numeroint = numeroint;
    }

    public String getEntrecalles() {
        return entrecalles;
    }

    public void setEntrecalles(String entrecalles) {
        this.entrecalles = entrecalles;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getLugarnacimiento() {
        return lugarnacimiento;
    }

    public void setLugarnacimiento(String lugarnacimiento) {
        this.lugarnacimiento = lugarnacimiento;
    }

    public Integer getEfnacimiento() {
        return efnacimiento;
    }

    public void setEfnacimiento(Integer efnacimiento) {
        this.efnacimiento = efnacimiento;
    }

    public Short getSexo() {
        return sexo;
    }

    public void setSexo(Short sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonorecados() {
        return telefonorecados;
    }

    public void setTelefonorecados(String telefonorecados) {
        this.telefonorecados = telefonorecados;
    }

    public Boolean getListanegra() {
        return listanegra;
    }

    public void setListanegra(Boolean listanegra) {
        this.listanegra = listanegra;
    }

    public Short getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Short estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getIdcoop() {
        return idcoop;
    }

    public void setIdcoop(String idcoop) {
        this.idcoop = idcoop;
    }

    public Integer getIdsector() {
        return idsector;
    }

    public void setIdsector(Integer idsector) {
        this.idsector = idsector;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(Date fecharetiro) {
        this.fecharetiro = fecharetiro;
    }

    public Date getFechaciudad() {
        return fechaciudad;
    }

    public void setFechaciudad(Date fechaciudad) {
        this.fechaciudad = fechaciudad;
    }

    public Short getRegimenMat() {
        return regimenMat;
    }

    public void setRegimenMat(Short regimenMat) {
        this.regimenMat = regimenMat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getMedioInf() {
        return medioInf;
    }

    public void setMedioInf(Short medioInf) {
        this.medioInf = medioInf;
    }

    public Integer getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Integer requisitos) {
        this.requisitos = requisitos;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public Short getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Short nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Short getGradoEstudios() {
        return gradoEstudios;
    }

    public void setGradoEstudios(Short gradoEstudios) {
        this.gradoEstudios = gradoEstudios;
    }

    public Short getCategoria() {
        return categoria;
    }

    public void setCategoria(Short categoria) {
        this.categoria = categoria;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getCausaBaja() {
        return causaBaja;
    }

    public void setCausaBaja(Integer causaBaja) {
        this.causaBaja = causaBaja;
    }

    public Short getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(Short nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getRfcValido() {
        return rfcValido;
    }

    public void setRfcValido(Boolean rfcValido) {
        this.rfcValido = rfcValido;
    }

    public Boolean getCurpValido() {
        return curpValido;
    }

    public void setCurpValido(Boolean curpValido) {
        this.curpValido = curpValido;
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.personasPK);
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
        final PersonasDTO other = (PersonasDTO) obj;
        return Objects.equals(this.personasPK, other.personasPK);
    }

    @Override
    public String toString() {
        return "PersonasDTO{" + "personasPK=" + personasPK + ", calle=" + calle + ", numeroext=" + numeroext + ", numeroint=" + numeroint + ", entrecalles=" + entrecalles + ", fechanacimiento=" + fechanacimiento + ", lugarnacimiento=" + lugarnacimiento + ", efnacimiento=" + efnacimiento + ", sexo=" + sexo + ", telefono=" + telefono + ", telefonorecados=" + telefonorecados + ", listanegra=" + listanegra + ", estadocivil=" + estadocivil + ", idcoop=" + idcoop + ", idsector=" + idsector + ", estatus=" + estatus + ", aceptado=" + aceptado + ", fechaingreso=" + fechaingreso + ", fecharetiro=" + fecharetiro + ", fechaciudad=" + fechaciudad + ", regimenMat=" + regimenMat + ", nombre=" + nombre + ", medioInf=" + medioInf + ", requisitos=" + requisitos + ", appaterno=" + appaterno + ", apmaterno=" + apmaterno + ", nacionalidad=" + nacionalidad + ", gradoEstudios=" + gradoEstudios + ", categoria=" + categoria + ", rfc=" + rfc + ", curp=" + curp + ", email=" + email + ", razonSocial=" + razonSocial + ", causaBaja=" + causaBaja + ", nivelRiesgo=" + nivelRiesgo + ", celular=" + celular + ", rfcValido=" + rfcValido + ", curpValido=" + curpValido + ", idcolonia=" + idcolonia + '}';
    }

}
