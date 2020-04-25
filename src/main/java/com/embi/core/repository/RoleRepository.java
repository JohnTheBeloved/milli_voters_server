package com.embi.core.repository;

import com.embi.core.model.auth.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}