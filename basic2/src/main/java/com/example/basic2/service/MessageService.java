package com.example.basic2.service;

import com.example.basic2.entity.Message;
import com.example.basic2.repository.MessageRepository;
import com.example.basic2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveMessage(Message msg) { messageRepository.save(msg);
    }

    public List<Message> getAllMessages(){

        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


    public void delete(Message m, Principal principal) {

        String username = principal.getName();
        if (username.equals(m.getUsername())) {
            messageRepository.delete(m);
        }
        else{
            System.out.println("INTE DELETE " + username + " " +  m.getUsername());
        }
    }


}
