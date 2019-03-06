package com.agenda.electronic.persister;


import com.agenda.electronic.entity.DetallefacilitadorEntity;
import com.agenda.electronic.entity.EventoEntity;
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

    public List<FacilitadoresEntity> findFacilitadoresById(Integer id) {
        Query query = entityManager.createQuery("from FacilitadoresEntity f where f.idfacilitadores=:id");
        query.setParameter("id", id);
        List<FacilitadoresEntity> eventoEntities = new ArrayList<>();
        eventoEntities = query.getResultList();
        return eventoEntities;
    }

    @Transactional
    public void saveRelation(FacilitadoresEntity facilitadoresEntity, EventoEntity eventoEntity) {
        DetallefacilitadorEntity detallefacilitadorEntity = new DetallefacilitadorEntity();
        detallefacilitadorEntity.setEventoIdevento(eventoEntity.getIdevento());
        detallefacilitadorEntity.setFacilitadoresIdfacilitadores(facilitadoresEntity.getIdfacilitadores());
        entityManager.persist(detallefacilitadorEntity);
    }

    @Transactional
    public void deleteRelation(FacilitadoresEntity facilitadoresEntity, EventoEntity eventoEntity) {
        Query query = entityManager.createQuery("from DetallefacilitadorEntity d where d.eventoIdevento=:idevento and d.facilitadoresIdfacilitadores=:idfacilitador");
        query.setParameter("idevento", eventoEntity.getIdevento());
        query.setParameter("idfacilitador", facilitadoresEntity.getIdfacilitadores());
        List<DetallefacilitadorEntity> entities = new ArrayList<>();
        entities = query.getResultList();
        for (DetallefacilitadorEntity detail : entities) {
            Object obj = entityManager.merge(detail);
            entityManager.remove(obj);
        }
        entityManager.flush();
    }

    @Transactional
    public List<FacilitadoresEntity> findAllFacilitadoresAdded(EventoEntity eventoEntity) {
        Query query = entityManager.createQuery("from DetallefacilitadorEntity e where e.eventoIdevento=:idevento");
        query.setParameter("idevento", eventoEntity.getIdevento());
        List<FacilitadoresEntity> facilitadoresEntities = new ArrayList<>();

        for (Object detallefacilitadorEntity : query.getResultList()) {
            facilitadoresEntities.addAll(findFacilitadoresById(((DetallefacilitadorEntity) detallefacilitadorEntity).getFacilitadoresIdfacilitadores()));
        }
        return facilitadoresEntities;
    }
}
