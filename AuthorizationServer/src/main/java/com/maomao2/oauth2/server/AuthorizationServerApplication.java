package com.maomao2.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthorizationServerApplication {

  public static void main(String[] args) {

    SpringApplication.run(AuthorizationServerApplication.class, args);
  }
}
