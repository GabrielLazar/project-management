package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.ProjectRepository;
import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private MapperUtil mapperUtil;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Page<ProjectDTO> findAllPageableProjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return projectRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable)
                .map(project -> mapperUtil.convertToDTO(project,new ProjectDTO()));
    }
}
