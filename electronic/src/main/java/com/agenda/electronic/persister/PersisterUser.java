package com.agenda.electronic.persister;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class PersisterUser {

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
        Boolean result= false;
        Query existUser = entityManager.createQuery("from UsersEntity where  upper(username)=:userName and  password=:password");
        existUser.setParameter("username", userName);
        existUser.setParameter("username", password);
        existUser.setParameter("state", (byte)1);
        return result;
    }
}
