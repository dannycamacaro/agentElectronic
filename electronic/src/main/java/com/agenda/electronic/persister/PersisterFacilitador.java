package com.agenda.electronic.persister;


import com.agenda.electronic.entity.FacilitadoresEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersisterFacilitador {


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

    public List<FacilitadoresEntity> findAllFacilitadores() {
        Query query = entityManager.createQuery("from FacilitadoresEntity");
        List<FacilitadoresEntity> eventoEntities = new ArrayList<>();
        eventoEntities = query.getResultList();
        return eventoEntities;
    }
}
