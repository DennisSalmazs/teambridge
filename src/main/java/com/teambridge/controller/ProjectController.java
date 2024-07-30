package com.teambridge.controller;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.service.ProjectService;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model) {

        model.addAttribute("project",new ProjectDTO()); // project object, for the project form
        model.addAttribute("managers",userService.findManagers()); // manager list, for the project dropdown in the form
        model.addAttribute("projects",projectService.findAll()); // projects list, for the project table

        return "project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute ProjectDTO project) {

        projectService.save(project);
        return "redirect:/project/create";
    }
}
