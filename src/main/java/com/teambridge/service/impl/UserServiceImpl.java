package com.teambridge.service.impl;

import com.teambridge.dto.UserDTO;
import com.teambridge.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService {

    @Override
    public UserDTO save(UserDTO user) {
        return super.save(user.getUserName(),user);
    }

    @Override
    public UserDTO findById(String username) {
        return super.findById(username);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(UserDTO object) {
        super.update(object.getUserName(),object);
    }

    @Override
    public void deleteById(String username) {
        super.deleteById(username);
    }

    @Override
    public List<UserDTO> findManagers() {
        return findAll().stream().
                filter(user -> user.getRole().getDescription().equals("Manager")).
                collect(Collectors.toList());
    }
}
