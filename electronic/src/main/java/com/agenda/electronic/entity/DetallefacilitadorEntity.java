package com.agenda.electronic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detallefacilitador", schema = "dbagendaelectronica")
@IdClass(DetallefacilitadorEntityPK.class)
public class DetallefacilitadorEntity {
    private Integer iddetallefacilitador;
    private Integer eventoIdevento;
    private Integer facilitadoresIdfacilitadores;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetallefacilitador")
    public Integer getIddetallefacilitador() {
        return iddetallefacilitador;
    }

    public void setIddetallefacilitador(Integer iddetallefacilitador) {
        this.iddetallefacilitador = iddetallefacilitador;
    }

    @Id
    @Column(name = "evento_idevento")
    public Integer getEventoIdevento() {
        return eventoIdevento;
    }

    public void setEventoIdevento(Integer eventoIdevento) {
        this.eventoIdevento = eventoIdevento;
    }

    @Id
    @Column(name = "facilitadores_idfacilitadores")
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
        DetallefacilitadorEntity that = (DetallefacilitadorEntity) o;
        return Objects.equals(iddetallefacilitador, that.iddetallefacilitador) &&
                Objects.equals(eventoIdevento, that.eventoIdevento) &&
                Objects.equals(facilitadoresIdfacilitadores, that.facilitadoresIdfacilitadores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetallefacilitador, eventoIdevento, facilitadoresIdfacilitadores);
    }
}
