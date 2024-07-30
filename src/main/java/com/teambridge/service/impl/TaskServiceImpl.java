package com.teambridge.service.impl;

import com.teambridge.dto.TaskDTO;
import com.teambridge.service.TaskService;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {

    private final UserService userService;

    public TaskServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public TaskDTO save(TaskDTO task) {
        return super.save(task.getId(), task);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO task) {
        super.update(task.getId(), task);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
