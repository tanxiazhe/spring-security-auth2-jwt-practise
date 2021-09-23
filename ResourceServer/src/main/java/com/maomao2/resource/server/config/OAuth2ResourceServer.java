package com.maomao2.resource.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/api/**").authenticated()
        .antMatchers("/").permitAll();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer oauthServer) {
    oauthServer.resourceId("control_sys_security_resource_id").tokenServices(tokenServices());
  }

  @Bean
  RemoteTokenServices tokenServices() {
    RemoteTokenServices services = new RemoteTokenServices();
    services.setCheckTokenEndpointUrl("http://localhost:7081/oauth/check_token");
    services.setClientId("client");
    services.setClientSecret("secret");
    return services;
  }
}
