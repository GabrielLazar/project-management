package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ProjectStatusController {

    private ProjectService projectService;

    public ProjectStatusController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project-status")
    public String getProjectStatus(Model model){
        model.addAttribute("projects",projectService.findAllProjectsWithTasks());
        return "/manager/project-status/project-status";
    }
}
