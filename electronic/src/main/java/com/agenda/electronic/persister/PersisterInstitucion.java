package com.agenda.electronic.persister;


import com.agenda.electronic.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersisterInstitucion {


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

    public List<InstitucionesEntity> findAllInstituciones() {
        Query query = entityManager.createQuery("from InstitucionesEntity");
        List<InstitucionesEntity> institucionesEntities = new ArrayList<>();
        institucionesEntities = query.getResultList();
        return institucionesEntities;
    }

    @Transactional
    public void saveRelation(InstitucionesEntity institucionesEntitySelected, EventoEntity eventoEntitySelected) {
        DetalleeventosEntity entity = new DetalleeventosEntity();
        entity.setEventoIdevento(eventoEntitySelected.getIdevento());
        entity.setInstitucionesIdinstituciones(institucionesEntitySelected.getIdinstituciones());
        entityManager.persist(entity);
    }

    @Transactional
    public void deleteRelation(InstitucionesEntity institucionesEntitySelectedAdded, EventoEntity eventoEntitySelected) {
        Query query = entityManager.createQuery("from DetalleeventosEntity d where d.eventoIdevento=:idevento and d.institucionesIdinstituciones=:idInstitucion");
        query.setParameter("idevento", eventoEntitySelected.getIdevento());
        query.setParameter("idInstitucion", institucionesEntitySelectedAdded.getIdinstituciones());
        List<DetalleeventosEntity> entities = new ArrayList<>();
        entities = query.getResultList();
        for (DetalleeventosEntity detail : entities) {
            Object obj = entityManager.merge(detail);
            entityManager.remove(obj);
        }
        entityManager.flush();
    }

    @Transactional
    public List<InstitucionesEntity> findAllInstitucionesAdded(EventoEntity eventoEntitySelected) {
        Query query = entityManager.createQuery("from DetalleeventosEntity e where e.eventoIdevento=:idevento");
        query.setParameter("idevento", eventoEntitySelected.getIdevento());
        List<InstitucionesEntity> entities = new ArrayList<>();
        for (Object detalleParticipante : query.getResultList()) {
            entities.addAll(findInstitucionesById(((DetalleeventosEntity) detalleParticipante).getInstitucionesIdinstituciones()));
        }
        return entities;
    }

    @Transactional
    public List<InstitucionesEntity> findInstitucionesById(Integer id) {
        Query query = entityManager.createQuery("from InstitucionesEntity f where f.idinstituciones=:id");
        query.setParameter("id", id);
        List<InstitucionesEntity> entities = new ArrayList<>();
        entities = query.getResultList();
        return entities;
    }
}
