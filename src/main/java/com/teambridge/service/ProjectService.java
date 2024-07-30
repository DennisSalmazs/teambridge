package com.teambridge.service;

import com.teambridge.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO,String> {

    void complete(ProjectDTO project);
}
