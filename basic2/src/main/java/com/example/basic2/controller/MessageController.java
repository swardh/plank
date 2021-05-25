package com.example.basic2.controller;
import com.example.basic2.entity.Message;
import com.example.basic2.entity.Users;
import com.example.basic2.repository.UserRepository;
import com.example.basic2.service.MessageService;
import com.example.basic2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/plank")
    public String plank(@ModelAttribute("mssg")Message mess, Model model, Principal principal) {

        Users user = userRepository.findByUserName(principal.getName());
        model.addAttribute("user", userRepository);
        model.addAttribute("msgg", messageService.getAllMessages());

        model.addAttribute("name", principal.getName());
        model.addAttribute("avatar", user.getImage());
        String cog = "https://www.pngjoy.com/pngl/199/15239808_cogwheel-settings-options-preferences-cog-cog-wheel-hd.png";
    //    String cog = "https://freepikpsd.com/wp-content/uploads/2019/11/Cogwheel_Gearwheel_Cog_Gear_97627-Transparent-Png-Images.png";
        model.addAttribute("cog", cog);

        return "plank";
    }


    @GetMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("msg") Message msg, Principal principal){
        String username = principal.getName();
        Users user = userRepository.findByUserName(username);
        msg.setUsername(user.getUserName());
        messageService.saveMessage(msg);
        return "redirect:/plank";
    }

//    @GetMapping("/editMessage")
//    public String editMessage(@ModelAttribute("msg") Message msg, Principal principal){
//        String username = principal.getName();
//        User user = userRepository.findByUserName(username);
//        msg.setUsername(user.getUserName());
//        messageService.saveMessage(msg);
//        return "redirect:/plank";
//    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }


    @PostMapping("/delMes")
    public String delete(Message mssg, Principal principal) {

        messageService.delete(mssg, principal);

        return "redirect:/plank";
    }

    @GetMapping("/getusername")
    public String getusername(@ModelAttribute("gun") int userid, Model model){

        Users user = userRepository.findUsersById(userid);
        model.addAttribute("uname", user.getUserName());
        return "redirect:/plank";
    }
}
