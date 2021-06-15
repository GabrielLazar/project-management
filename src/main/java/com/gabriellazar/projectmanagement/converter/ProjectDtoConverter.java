package com.gabriellazar.projectmanagement.converter;

import com.gabriellazar.projectmanagement.dto.ProjectDTO;
import com.gabriellazar.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProjectDtoConverter implements Converter<String, ProjectDTO> {

    @Autowired
    ProjectService projectService;

    @Override
    public ProjectDTO convert(String source) {
        Long id = Long.valueOf(source);
        return projectService.findProjectById(id);
    }
}
