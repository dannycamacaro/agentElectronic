package com.agenda.electronic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "facilitadores", schema = "dbagendaelectronica")
public class FacilitadoresEntity {
    private Integer idfacilitadores;
    private String iddocumento;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfacilitadores")
    public Integer getIdfacilitadores() {
        return idfacilitadores;
    }

    public void setIdfacilitadores(Integer idfacilitadores) {
        this.idfacilitadores = idfacilitadores;
    }

    @Basic
    @Column(name = "iddocumento")
    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    @Basic
    @Column(name = "nombres")
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilitadoresEntity that = (FacilitadoresEntity) o;
        return Objects.equals(idfacilitadores, that.idfacilitadores) &&
                Objects.equals(iddocumento, that.iddocumento) &&
                Objects.equals(nombres, that.nombres) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idfacilitadores, iddocumento, nombres, apellidos, telefono, correo);
    }
}
