package com.agenda.electronic.controller;

import com.agenda.electronic.persister.PersisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.Query;

@Controller
public class ControllerUser {

    @Autowired
    PersisterUser persisterUser;

    public Boolean validateLogin(String userName, String password){
        Boolean respond = false;
        if (persisterUser.validateUser(userName,password)){
            respond = true;
        }
        return respond;
    }
}
