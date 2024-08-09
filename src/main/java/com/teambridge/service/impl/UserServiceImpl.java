package com.teambridge.service.impl;

import com.teambridge.dto.UserDTO;
import com.teambridge.entity.User;
import com.teambridge.mapper.MapperUtil;
import com.teambridge.repository.UserRepository;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> users = userRepository.findAllByIsNotDeleted(); // findAll() + @Where(clause = "is_deleted=false")
        return users.stream().
                map(user -> mapperUtil.convert(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return mapperUtil.convert(user, UserDTO.class);
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(mapperUtil.convert(user, User.class));
    }

    @Override
    public void update(UserDTO user) {
        User foundUser = userRepository.findByUserName(user.getUserName()); // has iD
        User updatedUser = mapperUtil.convert(user, User.class); // lost its id
        updatedUser.setId(foundUser.getId());
        userRepository.save(updatedUser);
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        user.setUserName(user.getUserName() + "-" + user.getId()); // so that creating user with same username is possible
        userRepository.save(user); // save, to update object in DB
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);
        return users.stream().
                map(user -> mapperUtil.convert(user, UserDTO.class)).
                collect(Collectors.toList());
    }
}
