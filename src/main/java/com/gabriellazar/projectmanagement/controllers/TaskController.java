package com.gabriellazar.projectmanagement.controllers;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.dto.TaskDTO;
import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.enums.Status;
import com.gabriellazar.projectmanagement.services.ProjectService;
import com.gabriellazar.projectmanagement.services.TaskService;
import com.gabriellazar.projectmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
public class TaskController {

    private ProjectService projectService;
    private UserService userService;
    private TaskService taskService;

    @Value("${pageSize}")
    private String pageSize;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create-task{page}")
    public String getCreateTask(@RequestParam(value = "page", required = false) Optional<Integer> pageNumber, Model model) {
        int currentPage = pageNumber.orElse(1);
        int pageSize = Integer.valueOf(this.pageSize);

        Page<TaskDTO> taskDTOPage = taskService.findAllPageableTask(currentPage, pageSize);
        List<TaskDTO> tasksDTOS = taskDTOPage.getContent();
        List<ProjectDTO> activeProjects = projectService.findAllActiveProjects();
        List<UserDTO> employees = userService.findAllUsersByRole("Employee");

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalNumberOfPages", taskDTOPage.getTotalPages());
        model.addAttribute("tasks", tasksDTOS);
        model.addAttribute("activeProjects", activeProjects);
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("employees", employees);

        return "/manager/task/create-task";

    }

    @PostMapping("/create-task")
    public String postCreateTask(@ModelAttribute("task") TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return "redirect:/manager/create-task";
    }

    @GetMapping("update-task/{id}")
    public String updateTask(@PathVariable("id") Long id, Model model) {
        TaskDTO existingTask = taskService.findTaskById(id);
        int pageSize = Integer.valueOf(this.pageSize);

        Page<TaskDTO> taskDTOPage = taskService.findAllPageableTask(1, pageSize);
        List<TaskDTO> tasksDTOS = taskDTOPage.getContent();
        List<ProjectDTO> activeProjects = projectService.findAllActiveProjects();
        List<UserDTO> employees = userService.findAllUsersByRole("Employee");

        model.addAttribute("statusList", Status.values());
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalNumberOfPages", taskDTOPage.getTotalPages());
        model.addAttribute("tasks", tasksDTOS);
        model.addAttribute("activeProjects", activeProjects);
        model.addAttribute("task", existingTask);
        model.addAttribute("employees", employees);

        return "/manager/task/update-task";
    }

    @PostMapping("update-task/{id}")
    public String updatePostTask(@PathVariable("id") Long id, @Valid @ModelAttribute("task") TaskDTO taskDTO) {
        taskService.updateTask(id, taskDTO);
        return "redirect:/manager/create-task";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable(value = "id") Long id){
        taskService.deleteProject(id);
        return "redirect:/manager/create-task";
    }
}

