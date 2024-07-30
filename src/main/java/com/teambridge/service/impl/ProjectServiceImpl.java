package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.enums.Status;
import com.teambridge.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

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
}
