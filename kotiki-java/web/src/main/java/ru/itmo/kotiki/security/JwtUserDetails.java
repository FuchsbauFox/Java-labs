package ru.itmo.kotiki.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.kotiki.model.Role;

public class JwtUserDetails implements UserDetails {

  private final int id;
  private final String username;
  private final String password;
  private final int ownerId;
  private final boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public JwtUserDetails(int id, String username, String password, int ownerId,
      List<Role> roles, boolean enabled) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.ownerId = ownerId;
    this.enabled = enabled;
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    for(Role role : roles) {
      authorityList.add(new SimpleGrantedAuthority(role.getName()));
    }
    this.authorities = authorityList;
  }

  public int getId() {
    return id;
  }
  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public int getOwnerId() {
    return ownerId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
