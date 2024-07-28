package com.teambridge.dto;

import com.teambridge.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firsName;
    private String lastName;
    private String userName;
    private String phone;
    private String passWord;
    private Gender gender;
    private RoleDTO role;
}
