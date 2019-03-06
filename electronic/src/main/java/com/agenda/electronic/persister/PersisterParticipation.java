package com.agenda.electronic.persister;


import com.agenda.electronic.entity.DetalleparticipantesEntity;
import com.agenda.electronic.entity.EventoEntity;
import com.agenda.electronic.entity.ParticipantesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersisterParticipation {


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

    public List<ParticipantesEntity> findAllInstituciones() {
        Query query = entityManager.createQuery("from ParticipantesEntity");
        List<ParticipantesEntity> entities = new ArrayList<>();
        entities = query.getResultList();
        return entities;
    }

    @Transactional
    public void saveRelation(ParticipantesEntity participantesEntity, EventoEntity eventoEntity) {
        DetalleparticipantesEntity detalleparticipantesEntity = new DetalleparticipantesEntity();
        detalleparticipantesEntity.setEventoIdevento(eventoEntity.getIdevento());
        detalleparticipantesEntity.setParticipantesIdparticipante(participantesEntity.getIdparticipante());
        entityManager.persist(detalleparticipantesEntity);
    }

    @Transactional
    public void deleteRelation(ParticipantesEntity participantesEntity, EventoEntity eventoEntity) {
        Query query = entityManager.createQuery("from DetalleparticipantesEntity d where d.eventoIdevento=:idevento and d.participantesIdparticipante=:idParticipante");
        query.setParameter("idevento", eventoEntity.getIdevento());
        query.setParameter("idParticipante", participantesEntity.getIdparticipante());
        List<DetalleparticipantesEntity> entities = new ArrayList<>();
        entities = query.getResultList();
        for (DetalleparticipantesEntity detail : entities) {
            Object obj = entityManager.merge(detail);
            entityManager.remove(obj);
        }
        entityManager.flush();
    }

    @Transactional
    public List<ParticipantesEntity> findAllParticipantesAdded(EventoEntity eventoEntity) {
        Query query = entityManager.createQuery("from DetalleparticipantesEntity e where e.eventoIdevento=:idevento");
        query.setParameter("idevento", eventoEntity.getIdevento());
        List<ParticipantesEntity> participantesEntities = new ArrayList<>();
        for (Object detalleParticipante : query.getResultList()) {
            participantesEntities.addAll(findParticipantesById(((DetalleparticipantesEntity) detalleParticipante).getParticipantesIdparticipante()));
        }
        return participantesEntities;
    }
    public List<ParticipantesEntity> findParticipantesById(Integer id) {
        Query query = entityManager.createQuery("from ParticipantesEntity f where f.idparticipante=:id");
        query.setParameter("id", id);
        List<ParticipantesEntity> participantesEntities = new ArrayList<>();
        participantesEntities = query.getResultList();
        return participantesEntities;
    }
}
