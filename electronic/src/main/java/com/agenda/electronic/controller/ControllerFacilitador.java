package com.agenda.electronic.controller;

import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.FacilitadoresEntity;
import com.agenda.electronic.persister.PersisterFacilitador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerFacilitador {
    @Autowired
    PersisterFacilitador persisterFacilitador;

    public List<FacilitadoresEntity> findAllFacilitador() {
        List<FacilitadoresEntity> entityList = new ArrayList<>();
        entityList = persisterFacilitador.findAllFacilitadores();
        return entityList;
    }

    public void save(FacilitadoresEntity facilitadoresEntity) {
        persisterFacilitador.save(facilitadoresEntity);
    }

    public void saveRelation(FacilitadoresEntity facilitadoresEntity, EventoEntity eventoEntity) {
        persisterFacilitador.saveRelation(facilitadoresEntity,eventoEntity);
    }

    public void update(FacilitadoresEntity facilitadoresEntitySelected) {
        persisterFacilitador.update(facilitadoresEntitySelected);
    }

    public void delete(FacilitadoresEntity facilitadoresEntitySelected) {
        persisterFacilitador.delete(facilitadoresEntitySelected);
    }

    public void deleteRelation(FacilitadoresEntity facilitadoresEntitySelected, EventoEntity eventoEntity) {
        persisterFacilitador.deleteRelation(facilitadoresEntitySelected,eventoEntity);
    }

    public List<FacilitadoresEntity> findAllFacilitadorAdded(EventoEntity eventoEntity) {
        List<FacilitadoresEntity> entityList = new ArrayList<>();
        entityList = persisterFacilitador.findAllFacilitadoresAdded(eventoEntity);
        return entityList;
    }
}
