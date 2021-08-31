package com.maomao2.resource.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

   @Bean
    public  PasswordEncoder passwordEncoder(){
       return  new BCryptPasswordEncoder();
   }

   public RemoteTokenServices remoteTokenServices(){
     RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
     remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
      remoteTokenServices.setClientId("client");
      remoteTokenServices.setClientSecret(passwordEncoder().encode("secret"));
     return remoteTokenServices;
   }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("control_sys_security_resource_id").stateless(true);
    }
}