package com.agenda.electronic.controller;

import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.InstitucionesEntity;
import com.agenda.electronic.persister.PersisterInstitucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerInstituciones {

    @Autowired
    PersisterInstitucion persisterInstitucion;

    public List<InstitucionesEntity> findAllInstituciones() {
        List<InstitucionesEntity> entityList = new ArrayList<>();
        entityList = persisterInstitucion.findAllInstituciones();
        return entityList;
    }

    public void save(InstitucionesEntity InstitucionesEntity) {
        persisterInstitucion.save(InstitucionesEntity);
    }

    public void update(InstitucionesEntity institucionesEntity) {
        persisterInstitucion.update(institucionesEntity);
    }

    public void delete(InstitucionesEntity institucionesEntity) {
        persisterInstitucion.delete(institucionesEntity);
    }

    public  void saveRelation(InstitucionesEntity institucionesEntitySelected, EventoEntity eventoEntitySelected) {
        persisterInstitucion.saveRelation(institucionesEntitySelected,eventoEntitySelected);
    }

    public  void deleteRelation(InstitucionesEntity institucionesEntitySelectedAdded, EventoEntity eventoEntitySelected) {
        persisterInstitucion.deleteRelation(institucionesEntitySelectedAdded,eventoEntitySelected);
    }

    public  List<InstitucionesEntity> findAllParticipations() {
        List<InstitucionesEntity> entityList = new ArrayList<>();
        entityList = persisterInstitucion.findAllInstituciones();
        return entityList;
    }

    public  List<InstitucionesEntity> findAllParticipanteAdded(EventoEntity eventoEntitySelected) {
        List<InstitucionesEntity> entityList = new ArrayList<>();
        entityList = persisterInstitucion.findAllInstitucionesAdded(eventoEntitySelected);
        return entityList;
    }


}
