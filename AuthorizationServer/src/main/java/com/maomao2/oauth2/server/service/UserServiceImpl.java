package com.maomao2.oauth2.server.service;

import com.maomao2.oauth2.server.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    // 构建角色列表
    List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
    SysRole sysRole = new SysRole();
    sysRole.setRoleName("ROLE_USER");
    sysRole.setRoleName("ROLE_ADMIN");
    grantedAuths.add(sysRole);

    return new User("admin", passwordEncoder.encode("123456"), grantedAuths);
  }
}
