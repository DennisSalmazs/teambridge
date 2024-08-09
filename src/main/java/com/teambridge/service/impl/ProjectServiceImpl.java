package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.entity.Project;
import com.teambridge.enums.Status;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.ProjectRepository;
import com.teambridge.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperUtil mapperUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        return mapperUtil.convert(project, ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projects = projectRepository.findAll(Sort.by("projectCode"));
        return projects.stream().
                map(project -> mapperUtil.convert(project, ProjectDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO project) {
        Project convertedProject = mapperUtil.convert(project, Project.class);
        convertedProject.setProjectStatus(Status.OPEN);
        projectRepository.save(convertedProject);
    }

    @Override
    public void update(ProjectDTO project) {

    }

    @Override
    public void delete(ProjectDTO project) {

    }
}
