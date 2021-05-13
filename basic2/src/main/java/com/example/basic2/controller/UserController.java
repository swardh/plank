package com.example.basic2.controller;

import com.example.basic2.entity.Users;
import com.example.basic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

   @Autowired
   private UserService userService;


    @GetMapping("/welcome")
    public String welcome(@ModelAttribute("usr") Users usr,
                          Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "welcome";
    }


    //Skicka information "post" sparar en användare
    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("usr") Users usr){
        userService.saveUser(usr);
        return "redirect:/welcome";
    }


}
