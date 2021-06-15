package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.entity.Project;
import com.gabriellazar.projectmanagement.enums.Status;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.ProjectRepository;
import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private MapperUtil mapperUtil;

    @Autowired
    public ProjectServiceImpl(@Lazy ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Page<ProjectDTO> findAllPageableProjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return projectRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable)
                .map(project -> mapperUtil.convertToDTO(project,new ProjectDTO()));
    }

    @Override
    public ProjectDTO findProjectByProjectCode(String projectCode) {
        Optional<Project> project = projectRepository.findByProjectCodeIgnoreCase(projectCode);
        return project.map(proj -> mapperUtil.convertToDTO(proj,new ProjectDTO())).orElse(null);
    }

    @Override
    public void saveProject(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);
        projectRepository.saveAndFlush(mapperUtil.convertToEntity(projectDTO, new Project()));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDTO findProjectById(Long id) {
        return mapperUtil.convertToDTO(projectRepository.findById(id).get(),new ProjectDTO());
    }
}
