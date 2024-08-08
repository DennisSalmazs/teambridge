package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return List.of();
    }

    @Override
    public void save(ProjectDTO project) {

    }

    @Override
    public void update(ProjectDTO project) {

    }

    @Override
    public void delete(ProjectDTO project) {

    }
}
