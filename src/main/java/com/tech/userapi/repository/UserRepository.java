package com.tech.userapi.repository;

import com.tech.userapi.repository.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, org.springframework.data.repository.Repository<User, UUID> {
    Optional<User> findByUsername(String user);
}
