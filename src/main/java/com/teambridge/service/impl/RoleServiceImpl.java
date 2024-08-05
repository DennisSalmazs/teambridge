package com.teambridge.service.impl;

import com.teambridge.dto.RoleDTO;
import com.teambridge.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<RoleDTO> listAllRoles() {
        return List.of();
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
