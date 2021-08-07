package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProjectService {


    Page<ProjectDTO> findAllPageableProjects(int pageNo, int pageSize);
    ProjectDTO findProjectByProjectCode(String projectCode);
    void saveProject(ProjectDTO projectDTO);
    void deleteProject(Long id);
    ProjectDTO findProjectById(Long id);
    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);
    List<ProjectDTO> findAllActiveProjects();
    List<ProjectDTO> findAllProjectsWithTasks();
}
