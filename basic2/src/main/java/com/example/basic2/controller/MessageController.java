package com.example.basic2.controller;
import com.example.basic2.entity.Message;
import com.example.basic2.entity.Users;
import com.example.basic2.repository.UserRepository;
import com.example.basic2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/plank")
    public String plank(@ModelAttribute("mssg")Message mess, Model model, Principal principal) {

        model.addAttribute("msg", messageService.getAllMessages());
        model.addAttribute("name", principal.getName());
        return "plank";
    }

    @GetMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("msg") Message msg, Principal principal){
        String username = principal.getName();
        Users user = userRepository.findByUserName(username);
        msg.setUserId(user.getId());
        messageService.saveMessage(msg);
        return "redirect:/plank";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/plank";
    }
}
