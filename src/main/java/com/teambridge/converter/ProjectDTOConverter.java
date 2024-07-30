package com.teambridge.converter;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.service.ProjectService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOConverter implements Converter<String, ProjectDTO> { // PR001 -> ProjectDTO

    private final ProjectService projectService;

    public ProjectDTOConverter(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectDTO convert(String source) {
        return projectService.findById(source);
    }
}
