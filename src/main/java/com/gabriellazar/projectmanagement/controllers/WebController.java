package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/welcome")
    public String get(@ModelAttribute("user") UserDTO userDTO) {
        return "welcome";
    }

   @RequestMapping
    public String getIndex(Model model){
        model.addAttribute("user", new UserDTO());
        return "index";
    }

    @RequestMapping("/create-user")
    public String getCreateUser(){
        return "/user/create-user";
    }




}
