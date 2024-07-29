package com.teambridge.converter;

import com.teambridge.dto.RoleDTO;
import com.teambridge.service.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class is to solve error for the need of converting String into DTO
 * Role dropdown is capturing role id value as String, this needs to be converted into RoleDTO
 * We do not need to call this method, SpringBoot will call it when it needs to convert
 * Our task is to structure this convert method
 */
@Component
public class RoleDTOConverter implements Converter<String,RoleDTO> {

    private final RoleService roleService;

    public RoleDTOConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    // "1" ==> RoleDTO

    @Override
    public RoleDTO convert(String source) { // "1" ==> 1
        return roleService.findById(Long.parseLong(source));
    }
}
