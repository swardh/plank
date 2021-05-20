package com.example.basic2.service;

import com.example.basic2.entity.Users;
import com.example.basic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(Users user){
        if(userRepository.findByUserName(user.getUserName()) != null) {
            System.out.println("TAKEN");
        }
        else{
            userRepository.save(user);
        }
    }

    public void editUser(Users user){
        userRepository.save(user);

    }

    public void deleteUsr(Users user){
        userRepository.delete(user);
    }

}
