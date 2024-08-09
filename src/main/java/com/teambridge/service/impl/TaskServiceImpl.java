package com.teambridge.service.impl;

import com.teambridge.dto.TaskDTO;
import com.teambridge.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return List.of();
    }

    @Override
    public void save(TaskDTO taskDTO) {

    }

    @Override
    public void update(TaskDTO taskDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
