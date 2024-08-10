package com.teambridge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private LocalDateTime insertDateTime;

    @Column(updatable = false, nullable = false)
    private Long insertUserId;

    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;

    @Column(nullable = false)
    private Long lastUpdateUserId;

    private Boolean isDeleted = false;

    @PrePersist
    private void onPrePersists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        insertDateTime = LocalDateTime.now();
        lastUpdateDateTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal(); // identity of currently logged-in use
            insertUserId = ((UserPrincipal) principal).getId();
            lastUpdateUserId = ((UserPrincipal) principal).getId();
        }
    }

    @PreUpdate
    private void onPreUpdate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        lastUpdateDateTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal(); // identity of currently logged-in use
            lastUpdateUserId = ((UserPrincipal) principal).getId();
        }
    }
}
