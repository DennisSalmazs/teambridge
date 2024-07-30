package com.teambridge.service;

import com.teambridge.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO,String > {

    List<UserDTO> findManagers();

}
