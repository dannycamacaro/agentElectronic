package com.agenda.electronic.controller;

import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.FacilitadoresEntity;
import com.agenda.electronic.entity.ParticipantesEntity;
import com.agenda.electronic.persister.PersisterParticipation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerParticipation {
    @Autowired
    PersisterParticipation persisterParticipation;

    public List<ParticipantesEntity> findAllParticipations() {
        List<ParticipantesEntity> entityList = new ArrayList<>();
        entityList = persisterParticipation.findAllInstituciones();
        return entityList;
    }

    public void save(ParticipantesEntity participantesEntity) {
        persisterParticipation.save(participantesEntity);
    }

    public void update(ParticipantesEntity participantesEntity) {
        persisterParticipation.update(participantesEntity);
    }

    public void delete(ParticipantesEntity participantesEntity) {
        persisterParticipation.delete(participantesEntity);
    }

    public void deleteRelation(ParticipantesEntity participantesEntity, EventoEntity eventoEntity) {
        persisterParticipation.deleteRelation(participantesEntity,eventoEntity);
    }

    public List<ParticipantesEntity> findAllParticipanteAdded(EventoEntity eventoEntity) {
        List<ParticipantesEntity> entityList = new ArrayList<>();
        entityList = persisterParticipation.findAllParticipantesAdded(eventoEntity);
        return entityList;
    }

    public void saveRelation(ParticipantesEntity participantesEntity, EventoEntity eventoEntity) {
        persisterParticipation.saveRelation(participantesEntity,eventoEntity);
    }

}
