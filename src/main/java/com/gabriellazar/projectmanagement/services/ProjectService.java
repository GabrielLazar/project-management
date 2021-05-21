package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



@Service
public interface ProjectService {


    Page<ProjectDTO> findAllPageableProjects(int pageNo, int pageSize);
    ProjectDTO findProjectByProjectCode(String projectCode);
    void saveProject(ProjectDTO projectDTO);
}
