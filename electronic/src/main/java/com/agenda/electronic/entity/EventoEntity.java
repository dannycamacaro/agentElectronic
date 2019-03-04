package com.agenda.electronic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "evento", schema = "dbagendaelectronica")
public class EventoEntity {
    private Integer idevento;
    private String tema;
    private String duracion;
    private String locacion;
    private LocalDate fechainicio;
    private LocalDate fechafin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevento")
    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    @Basic
    @Column(name = "tema")
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Basic
    @Column(name = "duracion")
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "locacion")
    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    @Basic
    @Column(name = "fechainicio")
    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    @Basic
    @Column(name = "fechafin")
    public LocalDate getFechafin() {
        return fechafin;
    }

    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEntity that = (EventoEntity) o;
        return Objects.equals(idevento, that.idevento) &&
                Objects.equals(tema, that.tema) &&
                Objects.equals(duracion, that.duracion) &&
                Objects.equals(locacion, that.locacion) &&
                Objects.equals(fechainicio, that.fechainicio) &&
                Objects.equals(fechafin, that.fechafin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idevento, tema, duracion, locacion, fechainicio, fechafin);
    }
}
