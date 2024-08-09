package com.teambridge.service.impl;

import com.teambridge.dto.TaskDTO;
import com.teambridge.entity.Task;
import com.teambridge.enums.Status;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.TaskRepository;
import com.teambridge.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return mapperUtil.convert(task, TaskDTO.class);
        }
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
    public void save(TaskDTO task) {
        task.setAssignedDate(LocalDate.now());
        task.setTaskStatus(Status.OPEN);
        Task convertedTask = mapperUtil.convert(task, Task.class);
        taskRepository.save(convertedTask);
    }

    @Override
    public void update(TaskDTO task) {
        Optional<Task> foundTask = taskRepository.findById(task.getId());

        Task convertedTask = mapperUtil.convert(task, Task.class);
        if (foundTask.isPresent()) {
            convertedTask.setAssignedDate(foundTask.get().getAssignedDate());
            convertedTask.setTaskStatus(foundTask.get().getTaskStatus());
            taskRepository.save(convertedTask);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isPresent()) {
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }
    }
}
