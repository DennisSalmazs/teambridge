package com.teambridge.controller;

import com.teambridge.dto.UserDTO;
import com.teambridge.service.RoleService;
import com.teambridge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("roles", roleService.listAllRoles()); // roles, for the user dropdown in the form
        model.addAttribute("users", userService.listAllUsers()); // users list, for the user table

        return "user/create";
    }

    // create user
    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());
            return "user/create";
        }

        userService.save(user);
        return "redirect:/user/create"; // redirect points to the endpoint, not html file!!
    }

    //get the page that allows us to update the user
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());

        return "/user/update";
    }

    // update user
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());
            return "user/create";
        }

        userService.update(user);
        return "redirect:/user/create";
    }

    // delete user
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {

        userService.delete(username);
        return "redirect:/user/create";
    }
}
