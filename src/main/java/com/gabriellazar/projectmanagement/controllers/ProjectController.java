package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administration")
public class ProjectController {

    @GetMapping("/create-project")
    public String getCreateProject(@ModelAttribute("user") UserDTO userDTO){
        return "/administration/project/create-project";
    }
}
