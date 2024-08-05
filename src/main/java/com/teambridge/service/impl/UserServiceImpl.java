package com.teambridge.service.impl;

import com.teambridge.dto.UserDTO;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<UserDTO> listAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO user) {

    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(UserDTO user) {

    }
}
