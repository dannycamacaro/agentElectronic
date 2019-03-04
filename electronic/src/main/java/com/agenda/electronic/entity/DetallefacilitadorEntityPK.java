package com.agenda.electronic.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetallefacilitadorEntityPK implements Serializable {
    private Integer iddetallefacilitador;
    private Integer eventoIdevento;
    private Integer facilitadoresIdfacilitadores;

    @Column(name = "iddetallefacilitador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Integer getIddetallefacilitador() {
        return iddetallefacilitador;
    }

    public void setIddetallefacilitador(Integer iddetallefacilitador) {
        this.iddetallefacilitador = iddetallefacilitador;
    }

    @Column(name = "evento_idevento")
    @Id
    public Integer getEventoIdevento() {
        return eventoIdevento;
    }

    public void setEventoIdevento(Integer eventoIdevento) {
        this.eventoIdevento = eventoIdevento;
    }

    @Column(name = "facilitadores_idfacilitadores")
    @Id
    public Integer getFacilitadoresIdfacilitadores() {
        return facilitadoresIdfacilitadores;
    }

    public void setFacilitadoresIdfacilitadores(Integer facilitadoresIdfacilitadores) {
        this.facilitadoresIdfacilitadores = facilitadoresIdfacilitadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallefacilitadorEntityPK that = (DetallefacilitadorEntityPK) o;
        return Objects.equals(iddetallefacilitador, that.iddetallefacilitador) &&
                Objects.equals(eventoIdevento, that.eventoIdevento) &&
                Objects.equals(facilitadoresIdfacilitadores, that.facilitadoresIdfacilitadores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetallefacilitador, eventoIdevento, facilitadoresIdfacilitadores);
    }
}
