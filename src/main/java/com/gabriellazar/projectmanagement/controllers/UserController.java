package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create-user")
    public String getCreateUser(Model model){
        model.addAttribute("user",new UserDTO());
        return "/administration/user/create-user";
    }

    @PostMapping("/create-user")
    public String insertUser(UserDTO userDTO,Model model){
        System.out.println(userDTO.toString());
        model.addAttribute("user",new UserDTO());
        return "/administration/user/create-user";
    }
}
