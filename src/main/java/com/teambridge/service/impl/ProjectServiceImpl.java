package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.UserDTO;
import com.teambridge.enums.Status;
import com.teambridge.service.ProjectService;
import com.teambridge.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {
        // when a new project is created, its status is set OPEN, by default
        if (project.getProjectStatus() == null) {
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO project) {
        // to avoid existing status turn into null, we set existing status
        if (project.getProjectStatus() == null) {
            project.setProjectStatus(findById(project.getProjectCode()).getProjectStatus());
        }
            super.update(project.getProjectCode(), project);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETED);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectList = findAll().stream().
                filter(project -> project.getAssignedManager().equals(manager)). //Shaun Hayns
                map(project -> {

                    int completedTaskCounts = (int) taskService.findAll().stream().
                            filter(task -> task.getProject().equals(project) && task.getTaskStatus() == Status.COMPLETED).
                            count();

                    int unfinishedTaskCounts = (int) taskService.findAll().stream().
                            filter(task -> task.getProject().equals(project) && task.getTaskStatus() != Status.COMPLETED).
                            count();

                    project.setCompleteTaskCounts(completedTaskCounts);
                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);
                    return project;
                }).
                collect(Collectors.toList());
        return projectList;
    }
}
