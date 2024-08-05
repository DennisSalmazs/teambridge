package com.teambridge.converter;

import com.teambridge.dto.UserDTO;
import com.teambridge.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements Converter<String, UserDTO> {

    private final UserService userService;

    public UserDTOConverter(UserService userService) {
        this.userService = userService;
    }

    // "email" ==> UserDTO

    @Override
    public UserDTO convert(String source) {
        return userService.findByUserName(source);
    }
}
