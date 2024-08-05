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
        List<User> users = userRepository.findAll();
        return users.stream().
                map(user -> mapperUtil.convert(user, UserDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(mapperUtil.convert(user, User.class));
    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(UserDTO user) {

    }
}
