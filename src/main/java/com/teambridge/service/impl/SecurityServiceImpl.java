package com.teambridge.service.impl;

import com.teambridge.entity.User;
import com.teambridge.entity.UserPrincipal;
import com.teambridge.repository.UserRepository;
import com.teambridge.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to execute during authentication process
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameAndIsDeleted(username, false);
        return new UserPrincipal(user);
    }
}
