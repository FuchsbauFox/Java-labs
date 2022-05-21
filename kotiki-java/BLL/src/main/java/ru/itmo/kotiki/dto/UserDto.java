package ru.itmo.kotiki.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;

public class UserDto {
  private String username;
  private String password;
  private boolean enabled;
  private List<String> roles;

  private String name;
  private Date dateOfBirth;

  public UserDto fromUserAndOwner(User user, Owner owner) {
    username = user.getUsername();
    password = user.getPassword();
    enabled = user.isEnabled();
    roles = new ArrayList<>();
    for(Role role : user.getRoles()) {
      roles.add(role.getRole());
    }
    name = owner.getName();
    dateOfBirth = owner.getDateOfBirth();
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
