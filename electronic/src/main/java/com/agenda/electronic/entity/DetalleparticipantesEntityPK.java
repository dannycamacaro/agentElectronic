package com.agenda.electronic.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetalleparticipantesEntityPK implements Serializable {
    private Integer iddetalleparticipantes;
    private Integer eventoIdevento;
    private Integer participantesIdparticipante;

    @Column(name = "iddetalleparticipantes")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Integer getIddetalleparticipantes() {
        return iddetalleparticipantes;
    }

    public void setIddetalleparticipantes(Integer iddetalleparticipantes) {
        this.iddetalleparticipantes = iddetalleparticipantes;
    }

    @Column(name = "evento_idevento")
    @Id
    public Integer getEventoIdevento() {
        return eventoIdevento;
    }

    public void setEventoIdevento(Integer eventoIdevento) {
        this.eventoIdevento = eventoIdevento;
    }

    @Column(name = "participantes_idparticipante")
    @Id
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
        DetalleparticipantesEntityPK that = (DetalleparticipantesEntityPK) o;
        return Objects.equals(iddetalleparticipantes, that.iddetalleparticipantes) &&
                Objects.equals(eventoIdevento, that.eventoIdevento) &&
                Objects.equals(participantesIdparticipante, that.participantesIdparticipante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetalleparticipantes, eventoIdevento, participantesIdparticipante);
    }
}
