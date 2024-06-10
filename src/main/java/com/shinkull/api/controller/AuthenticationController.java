package com.shinkull.api.controller;


import com.shinkull.model.LocalUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.shinkull.api.model.LoginBody;
import com.shinkull.api.model.LoginResponse;
import com.shinkull.api.model.RegistrationBody;
import com.shinkull.exception.UserAlreadyExistsException;
import com.shinkull.service.UserService;

import jakarta.validation.Valid;

/**
 * Rest Controller for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  /** The user service. */
  private UserService userService;

  /**
   * Spring injected constructor.
   * @param userService
   */
  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Post Mapping to handle registering users.
   * @param registrationBody The registration information.
   * @return Response to front end.
   */
  @PostMapping("/register")
  public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
    try {
      userService.registerUser(registrationBody);
      return ResponseEntity.ok().build();
    } catch (UserAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  /**
   * Post Mapping to handle user logins to provide authentication token.
   * @param loginBody The login information.
   * @return The authentication token if successful.
   */
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
    String jwt = userService.loginUser(loginBody);
    if (jwt == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } else {
      LoginResponse response = new LoginResponse();
      response.setJwt(jwt);
      return ResponseEntity.ok(response);
    }
  }
  @GetMapping("/me")
  public LocalUser getLogedInUserProfile(@AuthenticationPrincipal LocalUser user){
    return user;

  }

}
