package com.example.basic2.service;

import com.example.basic2.entity.Users;
import com.example.basic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public void saveUser(Users user){
        UserRepository.save(user);
    }

    public List<Users> getAllUsers(){
        return UserRepository.findAll();
    }


}
