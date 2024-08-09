package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.UserDTO;
import com.teambridge.entity.Project;
import com.teambridge.entity.User;
import com.teambridge.enums.Status;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.ProjectRepository;
import com.teambridge.service.ProjectService;
import com.teambridge.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil, UserService userService) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
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
        Project foundProject = projectRepository.findByProjectCode(project.getProjectCode());

        Project convertedProject = mapperUtil.convert(project, Project.class);
        convertedProject.setProjectStatus(foundProject.getProjectStatus());
        convertedProject.setId(foundProject.getId());

        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode() + "-" + project.getId()); // SP00-1 ==> so that we can reuse same projectCode
        projectRepository.save(project);
    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETED);
        projectRepository.save(project);
    }

    // list all the projects that belong to current manager user
    @Override
    public List<ProjectDTO> listAllProjectsDetails() {
        UserDTO currentUser = userService.findByUserName("harold@manager.com");
        User user = mapperUtil.convert(currentUser, User.class);

        List<Project> projects = projectRepository.findAllByAssignedManager(user);

        return projects.stream().
                map(project -> {
                    ProjectDTO dto = mapperUtil.convert(project, ProjectDTO.class);
                    dto.setUnfinishedTaskCounts(3);
                    dto.setCompleteTaskCounts(5);
                    return dto;
                }).
                collect(Collectors.toList());
    }
}
