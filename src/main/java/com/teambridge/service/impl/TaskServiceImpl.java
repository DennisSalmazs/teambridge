package com.teambridge.service.impl;

import com.teambridge.dto.TaskDTO;
import com.teambridge.entity.Task;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.TaskRepository;
import com.teambridge.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().
                map(task -> mapperUtil.convert(task, TaskDTO.class)).
                collect(Collectors.toList());
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
