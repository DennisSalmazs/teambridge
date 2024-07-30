package com.teambridge.controller;

import com.teambridge.dto.UserDTO;
import com.teambridge.service.RoleService;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    // get the page that allows us to create user
    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new UserDTO()); // user object, for the user form
        model.addAttribute("roles", roleService.findAll()); // roles, for the user dropdown in the form
        model.addAttribute("users", userService.findAll()); // users list, for the user table

        return "user/create";
    }

    // create user
    @PostMapping("/create")
    public String insertUser(@ModelAttribute UserDTO user) {

        userService.save(user);
        return "redirect:/user/create"; // redirect points to the endpoint, not html file!!
    }

    //get the page that allows us to update the user
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findById(username));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "/user/update";
    }

    // update user
    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserDTO user) {

        userService.update(user);
        return "redirect:/user/create";
    }

    // delete user
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteById(username);
        return "redirect:/user/create";
    }
}
