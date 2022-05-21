package ru.itmo.kotiki.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

public final class UserDto {
  private final String username;
  private final String password;
  private final boolean enabled;
  private final List<String> roles;

  private final String name;
  private final Date dateOfBirth;

  public UserDto(User user, Owner owner) {
    username = user.getUsername();
    password = user.getPassword();
    enabled = user.isEnabled();
    roles = new ArrayList<>();
    for(Role role : user.getRoles()) {
      roles.add(role.getRole());
    }
    name = owner.getName();
    dateOfBirth = owner.getDateOfBirth();
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

  public String getName() {
    return name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }
}
