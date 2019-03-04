package com.agenda.electronic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "participantes", schema = "dbagendaelectronica")
public class ParticipantesEntity {
    private Integer idparticipante;
    private String nombres;
    private String apellidos;
    private String iddocumento;
    private Integer edad;
    private String curso;
    private String seccion;
    private String telefono;
    private String correo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparticipante")
    public Integer getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(Integer idparticipante) {
        this.idparticipante = idparticipante;
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
    @Column(name = "iddocumento")
    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    @Basic
    @Column(name = "edad")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Basic
    @Column(name = "curso")
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Basic
    @Column(name = "seccion")
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
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
        ParticipantesEntity that = (ParticipantesEntity) o;
        return Objects.equals(idparticipante, that.idparticipante) &&
                Objects.equals(nombres, that.nombres) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(iddocumento, that.iddocumento) &&
                Objects.equals(edad, that.edad) &&
                Objects.equals(curso, that.curso) &&
                Objects.equals(seccion, that.seccion) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idparticipante, nombres, apellidos, iddocumento, edad, curso, seccion, telefono, correo);
    }
}
