package com.teambridge.controller;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model) {

        model.addAttribute("project",new ProjectDTO()); // project object, for the project form
        model.addAttribute("projects",projectService.findAll()); // projects list, for the project table

        return "project/create";
    }
}
