package com.embi.core.service;

import java.util.Optional;

import com.embi.core.model.auth.User;

public interface UserService {
  void save(User user);

  Optional<User> findByUsername(String username);
}
