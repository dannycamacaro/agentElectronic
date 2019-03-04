package com.agenda.electronic.persister;


import com.agenda.electronic.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class PersistUser {

    public static final String QUERY_USER = "from UserEntity u where u.name=:nameUser AND u.password=:passwordUser";

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

    public boolean validateUser(String userName, String password) {
        Boolean result = false;
        Query existUser = entityManager.createQuery(QUERY_USER, UserEntity.class);
        existUser.setParameter("nameUser", userName);
        existUser.setParameter("passwordUser", password);
        if (existUser.getResultList().size() > 0)
            result = true;
        return result;
    }
}
