package com.tech.userapi.repository;

import com.tech.userapi.repository.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends
        JpaRepository<User, UUID>, Repository<User, UUID> {
    Optional<User> findByEmail(String email);
}
