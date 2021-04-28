package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.services.ProjectService;
import com.gabriellazar.projectmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("administration")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    @Value("${pageSize}")
    private String pageSize;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create-project{page}")
    public String getCreateProject(@RequestParam(value = "page",required = false) Optional<Integer> pageNumber,Model model){
        int currentPage = pageNumber.orElse(1);
        int pageSize = Integer.valueOf(this.pageSize);

        Page<ProjectDTO> page = projectService.findAllPageableProjects(currentPage,pageSize);
        List<ProjectDTO> projectDTOS = page.getContent();

        List<UserDTO> managers = userService.findAllUsersByRole("Manager");
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalNumberOfPages",page.getTotalPages());

        model.addAttribute("projects",projectDTOS);
        model.addAttribute("managers",managers);
        model.addAttribute("project", new ProjectDTO());

        return "/administration/project/create-project";
    }
}
