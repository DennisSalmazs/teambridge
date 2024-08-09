package com.teambridge.service;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long id);
    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);
}
