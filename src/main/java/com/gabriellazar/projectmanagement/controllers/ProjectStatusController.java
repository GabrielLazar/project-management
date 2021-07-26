package com.gabriellazar.projectmanagement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ProjectStatusController {

    @GetMapping("/project-status")
    public String getProjectStatus(){
        return "/manager/project-status/project-status";
    }
}
