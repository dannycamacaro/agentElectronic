package com.agenda.electronic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "detalleparticipantes", schema = "dbagendaelectronica")

public class DetalleparticipantesEntity implements Serializable {
    private Integer iddetalleparticipantes;
    private Integer eventoIdevento;
    private Integer participantesIdparticipante;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalleparticipantes")
    public Integer getIddetalleparticipantes() {
        return iddetalleparticipantes;
    }

    public void setIddetalleparticipantes(Integer iddetalleparticipantes) {
        this.iddetalleparticipantes = iddetalleparticipantes;
    }

    @Column(name = "evento_idevento")
    public Integer getEventoIdevento() {
        return eventoIdevento;
    }

    public void setEventoIdevento(Integer eventoIdevento) {
        this.eventoIdevento = eventoIdevento;
    }

    @Column(name = "participantes_idparticipante")
    public Integer getParticipantesIdparticipante() {
        return participantesIdparticipante;
    }

    public void setParticipantesIdparticipante(Integer participantesIdparticipante) {
        this.participantesIdparticipante = participantesIdparticipante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleparticipantesEntity that = (DetalleparticipantesEntity) o;
        return Objects.equals(iddetalleparticipantes, that.iddetalleparticipantes) &&
                Objects.equals(eventoIdevento, that.eventoIdevento) &&
                Objects.equals(participantesIdparticipante, that.participantesIdparticipante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetalleparticipantes, eventoIdevento, participantesIdparticipante);
    }
}
