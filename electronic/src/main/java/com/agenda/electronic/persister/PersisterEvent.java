package com.agenda.electronic.persister;

import com.agenda.electronic.entity.EventoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersisterEvent {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Object object) {
        entityManager.persist(object);
    }

    @Transactional
    public void update(Object object) {
        entityManager.merge(object);
    }

    @Transactional
    public void delete(Object object) {
        Object obj = entityManager.merge(object);
        entityManager.remove(obj);
        entityManager.flush();

    }

    public List<EventoEntity> findAllEvent() {
        Query query = entityManager.createQuery("from EventoEntity");
        List<EventoEntity> eventoEntities = new ArrayList<>();
        eventoEntities = query.getResultList();
        return eventoEntities;
    }

}
