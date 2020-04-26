package com.nzenweoforgroup.millivoters.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

import com.nzenweoforgroup.millivoters.core.model.auth.Role;
import com.nzenweoforgroup.millivoters.core.model.auth.User;
import com.nzenweoforgroup.millivoters.core.repository.RoleRepository;
import com.nzenweoforgroup.millivoters.core.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}