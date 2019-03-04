package com.agenda.electronic.controller;

import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.persister.PersisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerEvent {

    @Autowired
    PersisterEvent persisterEvent;
    public List<EventoEntity>  findAllEvent() {
        List<EventoEntity> entityList= new ArrayList<>();
        entityList = persisterEvent.findAllEvent();
        return entityList;
    }

    public void save(EventoEntity entityEvent) {
        persisterEvent.save(entityEvent);
    }

    public void update(EventoEntity eventEntitySelect) {
        persisterEvent.update(eventEntitySelect);
    }

    public void delete(EventoEntity eventEntitySelect) {
        persisterEvent.delete(eventEntitySelect);
    }
}
