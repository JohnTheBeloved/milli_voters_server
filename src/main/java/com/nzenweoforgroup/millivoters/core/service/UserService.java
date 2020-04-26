package com.nzenweoforgroup.millivoters.core.service;

import java.util.Optional;

import com.nzenweoforgroup.millivoters.core.model.auth.User;

public interface UserService {
  void save(User user);

  Optional<User> findByUsername(String username);
}
