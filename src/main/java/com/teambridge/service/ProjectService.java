package com.teambridge.service;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String> {

    void complete(ProjectDTO project);
    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);
}
