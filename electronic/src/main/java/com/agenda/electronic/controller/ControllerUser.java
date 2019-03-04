package com.agenda.electronic.controller;

import com.agenda.electronic.persister.PersistUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerUser {

    @Autowired
    PersistUser persistUser;

    public Boolean validateLogin(String userName, String password){
        Boolean respond = false;
        if (persistUser.validateUser(userName,password)){
            respond = true;
        }
        return respond;
    }
}
