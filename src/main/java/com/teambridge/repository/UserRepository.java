package com.teambridge.repository;

import com.teambridge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAllByIsNotDeleted();

    @Query("SELECT u FROM User u WHERE u.role.description = ?1")
    List<User> findByRoleDescriptionIgnoreCase(String role);
}
