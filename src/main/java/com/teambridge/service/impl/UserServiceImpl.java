package com.teambridge.service.impl;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.TaskDTO;
import com.teambridge.dto.UserDTO;
import com.teambridge.entity.User;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.UserRepository;
import com.teambridge.service.ProjectService;
import com.teambridge.service.TaskService;
import com.teambridge.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, @Lazy ProjectService projectService, @Lazy TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> users = userRepository.findAllByIsDeletedOrderByFirstNameDesc(false);
        return users.stream().
                map(user -> mapperUtil.convert(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserNameAndIsDeleted(username, false);
        return mapperUtil.convert(user, UserDTO.class);
    }

    @Override
    public void save(UserDTO user) {
        User convertedUser = mapperUtil.convert(user, User.class);
        //encode password, before saving into DB
        convertedUser.setPassWord(passwordEncoder.encode(convertedUser.getPassWord()));
        userRepository.save(convertedUser);
    }

    @Override
    public void update(UserDTO user) {
        User foundUser = userRepository.findByUserNameAndIsDeleted(user.getUserName(), false); // has iD
        User updatedUser = mapperUtil.convert(user, User.class); // lost its id
        updatedUser.setId(foundUser.getId());
        userRepository.save(updatedUser);
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserNameAndIsDeleted(username, false);

        //check if user can be deleted
        if (checkIfUserCanBeDeleted(mapperUtil.convert(user,UserDTO.class))) {
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId()); // so that creating user with same username is possible
            userRepository.save(user); // save, to update object in DB
        }
    }

    /**
     * Check if Manager has uncompleted projects,
     * or project has uncompleted tasks
     * @param user
     * @return
     */
    private boolean checkIfUserCanBeDeleted(UserDTO user) {

        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.listAllNonCompletedByAssignedManager(user);
                return projectDTOList.size() == 0;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.listAllNonCompletedByAssignedEmployee(user);
                return taskDTOList.size() == 0;
            default:
                return true;
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted(role, false);
        return users.stream().
                map(user -> mapperUtil.convert(user, UserDTO.class)).
                collect(Collectors.toList());
    }
}
