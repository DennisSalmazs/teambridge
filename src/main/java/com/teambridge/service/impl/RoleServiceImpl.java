package com.teambridge.service.impl;

import com.teambridge.dto.RoleDTO;
import com.teambridge.entity.Role;
import com.teambridge.mapper.RoleMapper;
import com.teambridge.repository.RoleRepository;
import com.teambridge.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {

        //ask repository layer to give us list of roles from DB
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().
                map(roleMapper::convertToDTO).
                collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
