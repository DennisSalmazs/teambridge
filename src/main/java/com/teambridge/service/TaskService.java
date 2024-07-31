package com.teambridge.service;

import com.teambridge.dto.TaskDTO;
import com.teambridge.enums.Status;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO,Long> {

    List<TaskDTO> findAllTasksByStatusIsNot(Status status);
}
