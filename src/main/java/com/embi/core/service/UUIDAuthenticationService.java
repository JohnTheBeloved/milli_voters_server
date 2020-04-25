
package com.embi.core.service;

import java.util.Optional;
import java.util.UUID;

import com.embi.core.model.auth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {
  @NonNull
  @Autowired
  UserService userService;

  @Override
  public Optional<String> login(final String username, final String password) {
    final String uuid = UUID.randomUUID().toString();
    final User user = new User();
      
    user.setUuid(uuid);
    user.setUsername(username);
    user.setPassword(password);

    userService.save(user);
    return Optional.of(uuid);
  }

  @Override
  public Optional<User> findByToken(final String token) {
    return userService.findByUsername(token);
  }

  @Override
  public void logout(final User user) {

  }
}