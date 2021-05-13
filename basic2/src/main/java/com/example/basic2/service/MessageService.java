package com.example.basic2.service;

import com.example.basic2.entity.Message;
import com.example.basic2.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository MessageRepository;

    public void saveMessage(Message msg) { MessageRepository.save(msg);
    }

    public List<Message> getAllMessages(){
        return MessageRepository.findAll();
    }
}
