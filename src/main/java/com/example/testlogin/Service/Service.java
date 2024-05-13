package com.example.testlogin.Service;

import com.example.testlogin.DBcontroller.Repository;
import com.example.testlogin.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    private Repository repository;
    private MyUser user;



    public boolean authenticateUser(String email) {
        return repository.findUserByEmail(email).isPresent();
    }
}
