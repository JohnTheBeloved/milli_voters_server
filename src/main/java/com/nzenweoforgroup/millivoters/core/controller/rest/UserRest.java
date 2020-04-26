package com.nzenweoforgroup.millivoters.core.controller.rest;


import java.security.Principal;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;

import com.nzenweoforgroup.millivoters.core.model.auth.TokenResponse;
import com.nzenweoforgroup.millivoters.core.model.auth.User;
import com.nzenweoforgroup.millivoters.core.service.UserAuthenticationService;
import com.nzenweoforgroup.millivoters.core.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.NonNull;

@RestController
public class UserRest {

  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @NonNull
  @Autowired
  UserAuthenticationService authentication;

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }

  @PostMapping("/users/register")
  User register(@RequestBody User user) {
    //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userService
      .save(user);
    return userService.findByUsername(user.getUsername())
    .orElseThrow(() -> new RuntimeException("Error occured retreiving user data"));
  }

  @PostMapping("/users/login")
  TokenResponse login(@RequestBody User user) {
    TokenResponse tokenResponse = new TokenResponse();
    String accessToken = authentication
      .login(user.getUsername(), user.getPassword())
      .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    tokenResponse.setAccessToken(accessToken);
    tokenResponse.setTokenType("bearer");
    return tokenResponse;
  }

  @GetMapping("/users/current")
  User getCurrent(@AuthenticationPrincipal final User user) {
    return user;
  }

  @GetMapping("/users/logout")
  boolean logout(@AuthenticationPrincipal final User user) {
    authentication.logout(user);
    return true;
  }


}