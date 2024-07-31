package com.teambridge.service.impl;

import com.teambridge.dto.TaskDTO;
import com.teambridge.enums.Status;
import com.teambridge.service.TaskService;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {

    private final UserService userService;

    public TaskServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public TaskDTO save(TaskDTO task) {
        // when a new task is created, its status is set OPEN, by default
        if (task.getTaskStatus() == null) {
            task.setTaskStatus(Status.OPEN);
        }
        // set assigned date of creation date
        if (task.getAssignedDate() == null) {
            task.setAssignedDate(LocalDate.now());
        }
        // set task id, when new task is created
        if (task.getId() == null) {
            task.setId(UUID.randomUUID().getMostSignificantBits()); // random Long id
        }
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
        // set status and assignedDate for the task,
        // since they are not in the Task Create form, but in the Task List table
        TaskDTO foundTask = super.findById(task.getId());

        task.setTaskStatus(foundTask.getTaskStatus());
        task.setAssignedDate(foundTask.getAssignedDate());

        super.update(task.getId(), task);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
