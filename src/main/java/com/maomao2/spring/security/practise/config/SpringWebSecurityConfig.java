package com.maomao2.spring.security.practise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .authorizeRequests()
        .antMatchers("/","/home").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login").permitAll()
        .and()
        .logout().permitAll();
  }

// public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
//     throws Exception {
//    authenticationManagerBuilder.inMemoryAuthentication()
//        .withUser("user").password("123").roles("USER");
// }

}
