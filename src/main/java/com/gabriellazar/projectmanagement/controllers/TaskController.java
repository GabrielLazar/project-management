package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class TaskController {

    ProjectService projectService;

    public TaskController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/create-task")
    public String getCreateTask(Model model){

     List<ProjectDTO> a = projectService.findAllActiveProjects();

        return "/manager/task/create-task";

    }
}
