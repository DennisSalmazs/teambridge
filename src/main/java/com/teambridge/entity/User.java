package com.teambridge.entity;

import com.teambridge.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
//@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;

    private String phone;
    private String passWord;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Role role;

}
