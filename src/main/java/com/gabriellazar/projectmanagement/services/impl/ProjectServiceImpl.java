package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.entity.Project;
import com.gabriellazar.projectmanagement.enums.Status;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.ProjectRepository;
import com.gabriellazar.projectmanagement.repository.TaskRepository;
import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private MapperUtil mapperUtil;
    private TaskRepository taskRepository;

    @Autowired
    public ProjectServiceImpl(@Lazy ProjectRepository projectRepository, @Lazy TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
        this.taskRepository = taskRepository;
    }

    @Override
    public Page<ProjectDTO> findAllPageableProjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ProjectDTO> pagesProjects = null;
        try {
            pagesProjects = projectRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable)
                    .map(project -> mapperUtil.convertToDTO(project, new ProjectDTO()));
        } catch (Exception e) {
            log.error("Exception in getting all pageable Projects :: {}", e);
        }
        return pagesProjects;
    }

    @Override
    public ProjectDTO findProjectByProjectCode(String projectCode) {
        Optional<Project> project = projectRepository.findByProjectCodeIgnoreCase(projectCode);
        return project.map(proj -> mapperUtil.convertToDTO(proj, new ProjectDTO())).orElse(null);
    }

    @Override
    public void saveProject(ProjectDTO projectDTO) {
        log.info("Starting to save project :: {}", projectDTO);
        try {
            projectDTO.setProjectStatus(Status.OPEN);
            projectRepository.saveAndFlush(mapperUtil.convertToEntity(projectDTO, new Project()));
            log.info("{} project was saved", projectDTO.getProjectName());
        } catch (Exception e) {
            log.error("Exception in saving project :: {}", projectDTO);
            log.error("Exception saving project :: {}", e);
        }
    }

    @Override
    public void deleteProject(Long id) {
        try {
            projectRepository.deleteById(id);
            log.info("Successfully deleted project with id :: {}", id);
        } catch (Exception e) {
            log.error("Exception in deleting the project by id {} :: {} ", id, e);
        }
    }

    @Override
    public ProjectDTO findProjectById(Long id) {
        ProjectDTO projectDTO = null;
        try {
            projectDTO = mapperUtil.convertToDTO(projectRepository.findById(id).get(), new ProjectDTO());
        } catch (Exception e) {
            log.error("Exception in finding project by id {} :: {}", id, e);
        }
        return projectDTO;
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        try {
            log.info("Updatind project with id {} :: {}", id, projectDTO);
            Project existingProject = projectRepository.findById(id).get();
            Project currentProject = mapperUtil.convertToEntity(projectDTO, new Project());
            currentProject.setId(id);
            currentProject.setInsertDateTime(existingProject.getInsertDateTime());
            currentProject.setInsertUserId(existingProject.getInsertUserId());
            projectRepository.save(currentProject);
            log.info("Successfully updated project with id {} :: {}", id, currentProject);
        } catch (Exception e) {
            log.error("Exception in updating project {} :: {}", id, projectDTO);
            log.error("Exception was ::{}", e);
        }
        return findProjectById(id);
    }

    @Override
    public List<ProjectDTO> findAllActiveProjects() {
        List<Project> activeProjects = null;
        try {
            activeProjects = projectRepository.findAllByProjectStatusNotIn(List.of(Status.COMPLETE));
            log.info("Getting all active projects :: {}", activeProjects);
        } catch (Exception e) {
            log.error("Exception in getting all active projects :: {}", e);
            return Collections.emptyList();
        }
        return activeProjects.stream().map(p -> mapperUtil.convertToDTO(p, new ProjectDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> findAllProjectsWithTasks() {
        List<ProjectDTO> allProjectsWithTasks;
        try{
          allProjectsWithTasks = projectRepository.findAll()
                  .stream()
                  .map(project -> {
                      ProjectDTO projectDTO = new ProjectDTO();
                      projectDTO = mapperUtil.convertToDTO(project,projectDTO);
                      projectDTO.setCompletedTasks(taskRepository.findAllCompletedTasksForProject(projectDTO.getId()));
                      projectDTO.setTotalTasks(taskRepository.findAllTasksForProject(projectDTO.getId()));
                      return projectDTO;
                  }).collect(Collectors.toList());
            log.info("Getting all projects with tasks :: {}", allProjectsWithTasks);
        } catch (Exception e){
            log.error("Exception in getting all projects with tasks :: {}", e);
            return Collections.emptyList();
        }
        return allProjectsWithTasks;
    }
}
