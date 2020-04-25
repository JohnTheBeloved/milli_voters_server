package com.embi.core.service;

import com.embi.core.model.auth.User;
import com.embi.core.service.UserAuthenticationService;
import com.embi.core.service.UserServiceImpl;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class TokenAuthenticationService implements UserAuthenticationService {
  
  @Autowired
  TokenService tokens;
  
  @Autowired
  UserServiceImpl users;

  @Override
  public Optional<String> login(final String username, final String password) {
    return users
      .findByUsername(username)
      .filter(user -> Objects.equals(password, user.getPassword()))
      .map(user -> tokens.expiring(ImmutableMap.of("username", username)));
  }

  @Override
  public Optional<User> findByToken(final String token) {
    return Optional
      .of(tokens.verify(token))
      .map(map -> map.get("username"))
      .flatMap(users::findByUsername);
  }

  @Override
  public void logout(final User user) {
    // Nothing to doy
  }
}