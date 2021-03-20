package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {




    @RequestMapping("/create-project")
    public String getProject(@ModelAttribute("user") UserDTO userDTO){
        return "/project/create-project";
    }


    @RequestMapping("/project-status")
    public String getProjectStatus(@ModelAttribute("user") UserDTO userDTO){
        return "/manager/project-status";
    }

    @RequestMapping("/account")
    public String getAccountInfo(@ModelAttribute("user") UserDTO userDTO){
        return "account";
    }

    @RequestMapping("/create-task")
    public String getTask(@ModelAttribute("user") UserDTO userDTO){
        return "/task/create-task";
    }

    @RequestMapping("archive")
    public String getArchived(@ModelAttribute("user") UserDTO userDTO){
        return "/employee/archive";
    }


}
