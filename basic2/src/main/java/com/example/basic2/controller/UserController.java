package com.example.basic2.controller;

import com.example.basic2.entity.Users;
import com.example.basic2.repository.UserRepository;
import com.example.basic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private UserRepository userRepository;


    @GetMapping("/welcome")
    public String welcome(@ModelAttribute("ussr") Users usr) {
        return "welcome";
    }


    //Skicka information "post" sparar en användare
    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("usr") Users usr, Model model){

        model.addAttribute("image", usr.getImage());
        if(usr.getImage() == ""){
            usr.setImage("https://pyxis.nymag.com/v1/imgs/630/6e0/eb215ad90cd826b9e57ff505f54c5c7228-07-avatar.rsquare.w700.jpg");
        }
        else {
            System.out.println("image " + usr.getImage());
        }

        userService.saveUser(usr);
        return "redirect:/login";
    }

    @GetMapping("/userinfo")
    public String showUserInfo(@ModelAttribute("usrinfo") Users user, Model model, Principal principal){
        String username = principal.getName();
        Users usr = userRepository.findByUserName(username);
        //model.addAttribute("username", principal.getName());
        model.addAttribute("username", usr.getUserName());
        model.addAttribute("password", usr.getPassword());
        model.addAttribute("role", usr.getRole());
        model.addAttribute("image", usr.getImage());

        return "userinfo";
    }

    @PostMapping("/edituser")
    public String editUser(@ModelAttribute("edit")Users ussser, Principal principal){
        String username = principal.getName();
        Users uzer = userRepository.findByUserName(username);

    //    System.out.println("top uzer " + uzer);
    //    System.out.println("top ussser " + ussser);
        ussser.setUserName(username);
        ussser.setId(uzer.getId());
        ussser.setRole(uzer.getRole());

        if(ussser.getPassword() == ""){
            ussser.setPassword(uzer.getPassword());
        }
        else{
            System.out.println("password " + uzer.getPassword());
        }

        if(ussser.getImage() == ""){
            ussser.setImage(uzer.getImage());
        }
        else {
            System.out.println("image " + uzer.getImage());
        }

    //    System.out.println("last uzer " + uzer);
    //    System.out.println("last ussser " + ussser);

        userService.editUser(ussser);

            return "redirect:/";
    }

}
