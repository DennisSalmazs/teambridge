package com.teambridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        List<UserDetails> users = new ArrayList<>();

        User user1 = new User("mike",passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_Admin")));

        User user2 = new User("ozzy",passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("ROLE_Manager")));

        users.add(user1);
        users.add(user2);

        return new InMemoryUserDetailsManager(users);
    }
}
