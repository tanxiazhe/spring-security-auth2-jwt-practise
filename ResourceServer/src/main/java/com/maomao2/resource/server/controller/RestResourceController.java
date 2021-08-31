package com.maomao2.resource.server.controller;

import com.maomao2.resource.server.resource.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestResourceController {
  @RequestMapping("/api/users/me")
  public ResponseEntity<UserProfile> profile() {

    //Build some dummy data to return for testing
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserProfile profile = new UserProfile();
    try {
      User user = (User) principal;
      String email = user.getUsername() + "@maomao2.com";
      profile.setName(user.getUsername());
      profile.setEmail(email);
    } catch (Exception e) {
      profile.setName(principal.toString());
    }
    return ResponseEntity.ok(profile);
  }
}