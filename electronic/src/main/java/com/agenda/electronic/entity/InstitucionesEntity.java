package com.agenda.electronic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "instituciones", schema = "dbagendaelectronica")
public class InstitucionesEntity {
    private Integer idinstituciones;
    private String rif;
    private String nombeinstitucion;
    private String telefono;
    private String direccion;
    private String correo;
    private String personacontacto;
    private String telefonopersona;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinstituciones")
    public Integer getIdinstituciones() {
        return idinstituciones;
    }

    public void setIdinstituciones(Integer idinstituciones) {
        this.idinstituciones = idinstituciones;
    }

    @Basic
    @Column(name = "rif")
    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    @Basic
    @Column(name = "nombeinstitucion")
    public String getNombeinstitucion() {
        return nombeinstitucion;
    }

    public void setNombeinstitucion(String nombeinstitucion) {
        this.nombeinstitucion = nombeinstitucion;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "personacontacto")
    public String getPersonacontacto() {
        return personacontacto;
    }

    public void setPersonacontacto(String personacontacto) {
        this.personacontacto = personacontacto;
    }

    @Basic
    @Column(name = "telefonopersona")
    public String getTelefonopersona() {
        return telefonopersona;
    }

    public void setTelefonopersona(String telefonopersona) {
        this.telefonopersona = telefonopersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitucionesEntity that = (InstitucionesEntity) o;
        return Objects.equals(idinstituciones, that.idinstituciones) &&
                Objects.equals(rif, that.rif) &&
                Objects.equals(nombeinstitucion, that.nombeinstitucion) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(correo, that.correo) &&
                Objects.equals(personacontacto, that.personacontacto) &&
                Objects.equals(telefonopersona, that.telefonopersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idinstituciones, rif, nombeinstitucion, telefono, direccion, correo, personacontacto, telefonopersona);
    }
}
