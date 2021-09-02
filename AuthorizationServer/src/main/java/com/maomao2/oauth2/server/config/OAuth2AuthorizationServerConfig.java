package com.maomao2.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  private TokenStore tokenStore = new InMemoryTokenStore();

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //添加客户端信息
    clients.inMemory()                  // 使用in-memory存储客户端信息
        .withClient("client")       // client_id
        .secret(passwordEncoder.encode("secret"))                   // client_secret
        .authorizedGrantTypes("authorization_code","implicit", "password")     // 该client允许的授权类型
        .authorities("READ_ONLY_CLIENT").scopes("read_profile_info")                      // 允许的授权范围
        .resourceIds("control_sys_security_resource_id").redirectUris("http://localhost:9099/login")
        .accessTokenValiditySeconds(5000).refreshTokenValiditySeconds(50000);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
  }
}