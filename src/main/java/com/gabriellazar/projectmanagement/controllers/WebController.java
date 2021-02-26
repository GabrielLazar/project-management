package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping("/welcome")
    public String get(@ModelAttribute("user") UserDTO userDTO) {
        return "welcome";
    }

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute("user", new UserDTO());
        return "index";
    }

    @RequestMapping(path = "/",method = RequestMethod.POST)
    public String verifyUsername(@ModelAttribute("user") UserDTO userDTO, Model model){
        String check = userDTO.getUserName().equals("Gabi") ? "Ga": null;
        if(check != null){
            check = "Invalid username or password.";
            model.addAttribute("check",check);
            return "index";
        }
        return "welcome";
    }

    @RequestMapping("/create-user")
    public String getCreateUser(@ModelAttribute("user") UserDTO userDTO){
        return "/user/create-user";
    }

    @RequestMapping("/create-project")
    public String getProject(@ModelAttribute("user") UserDTO userDTO){
        return "/project/create-project";
    }

    @RequestMapping("/navbar")
    public String getNavBar(@ModelAttribute("user") UserDTO userDTO){
        return "/fragments/navbar";
    }




}
