package com.nzenweoforgroup.millivoters.core.repository;

import com.nzenweoforgroup.millivoters.core.model.auth.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}