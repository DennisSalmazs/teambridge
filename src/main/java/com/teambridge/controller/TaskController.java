package com.teambridge.controller;

import com.teambridge.dto.TaskDTO;
import com.teambridge.enums.Status;
import com.teambridge.service.ProjectService;
import com.teambridge.service.TaskService;
import com.teambridge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("employees", userService.listAllByRole("Employee"));
        model.addAttribute("tasks", taskService.listAllTasks());

        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("projects", projectService.listAllProjects());
            model.addAttribute("employees", userService.listAllByRole("Employee"));
            model.addAttribute("tasks", taskService.listAllTasks());
            return "task/create";
        }

        taskService.save(task);
        return "redirect:/task/create";
    }

//    @GetMapping("/update/{id}")
//    public String editTask(@PathVariable Long id, Model model) {
//
//        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
//        model.addAttribute("tasks", taskService.findAll());
//        return "task/update";
//    }
//
////    @PostMapping("/update/{taskId}")
////    public String updateTask(@PathVariable Long taskId, @ModelAttribute TaskDTO task) {
////        // need to set taskId, since it is in DB, but not coming from Task Create form
////        task.setId(taskId);
////        taskService.update(task);
////        return "redirect:/task/create";
////    }
//
//    @PostMapping("/update/{id}")
//    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("projects", projectService.findAll());
//            model.addAttribute("employees", userService.findEmployees());
//            model.addAttribute("tasks", taskService.findAll());
//            return "task/update";
//        }
//
//        // since we have id in TaskDTO, spring will set value on our behalf
//        // if path parameter -- {id} -- & variable -- Long id -- match
//        taskService.update(task);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteTask(@PathVariable Long id) {
//
//        taskService.deleteById(id);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/employee/pending-tasks")
//    public String employeePendingTasks(Model model) {
//
//        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETED));
//        return "task/pending-tasks";
//    }
//
//    @GetMapping("/employee/edit/{id}")
//    public String employeeEditTask(@PathVariable Long id, Model model) {
//
//        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("statuses", Status.values());
//        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETED));
//
//        return "task/status-update";
//    }
//
//    @PostMapping("/employee/update/{id}") // {id} will be set in the behind by Spring
//    public String employeeUpdateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("statuses", Status.values());
//            model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETED));
//            return "task/status-update";
//        }
//        taskService.updateStatus(task);
//        return "redirect:/task/employee/pending-tasks";
//    }
//
//    @GetMapping("/employee/archive")
//    public String employeeArchivedTasks(Model model) {
//
//        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETED));
//        return "task/archive";
//    }
}
