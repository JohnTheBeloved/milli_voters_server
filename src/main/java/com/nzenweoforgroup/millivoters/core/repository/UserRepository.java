package com.nzenweoforgroup.millivoters.core.repository;

import java.util.Optional;

import com.nzenweoforgroup.millivoters.core.model.auth.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}