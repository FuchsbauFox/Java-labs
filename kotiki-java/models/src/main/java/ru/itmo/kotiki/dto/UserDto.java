package ru.itmo.kotiki.dto;

import java.util.ArrayList;
import java.util.List;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

public final class UserDto {

  private final int id;
  private final String username;
  private final String password;
  private final int ownerId;
  private final boolean enabled;
  private final List<String> roles;

  public UserDto(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.ownerId = user.getOwner().getId();
    this.enabled = user.isEnabled();
    this.roles = new ArrayList<>();
    for(Role role : user.getRoles()) {
      this.roles.add(role.getRole());
    }
  }

  public UserDto(int id, String username, String password, int ownerId, boolean enabled,
      List<String> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.ownerId = ownerId;
    this.enabled = enabled;
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public List<String> getRoles() {
    return roles;
  }
}
