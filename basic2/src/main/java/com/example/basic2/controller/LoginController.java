package com.example.basic2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "hej";
    }

    @GetMapping ("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

}
