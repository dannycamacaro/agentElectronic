package com.agenda.electronic.controller;

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

}
