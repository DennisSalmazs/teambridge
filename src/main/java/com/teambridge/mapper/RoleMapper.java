package com.teambridge.mapper;

import com.teambridge.dto.RoleDTO;
import com.teambridge.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleDTO convertToDTO(Role entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }

    public Role convertToEntity(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }
}
