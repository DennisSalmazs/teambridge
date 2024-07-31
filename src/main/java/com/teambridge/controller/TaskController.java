package com.teambridge.controller;

import com.teambridge.dto.TaskDTO;
import com.teambridge.service.ProjectService;
import com.teambridge.service.TaskService;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute TaskDTO task) {

        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {

        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }


}
