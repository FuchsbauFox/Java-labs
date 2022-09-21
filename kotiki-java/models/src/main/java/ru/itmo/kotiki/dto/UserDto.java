package ru.itmo.kotiki.dto;

import java.util.ArrayList;
import java.util.List;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

public final class UserDto {
  private final String username;
  private final String password;
  private final boolean enabled;
  private final List<String> roles;

  public UserDto(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.enabled = user.isEnabled();
    this.roles = new ArrayList<>();
    for(Role role : user.getRoles()) {
      this.roles.add(role.getRole());
    }
  }

  public UserDto(String username, String password, boolean enabled,
      List<String> roles) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public List<String> getRoles() {
    return roles;
  }
}
