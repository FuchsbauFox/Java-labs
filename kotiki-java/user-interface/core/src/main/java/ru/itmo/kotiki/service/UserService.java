package ru.itmo.kotiki.service;

import java.util.List;
import ru.itmo.kotiki.dto.UserDto;

public interface UserService {
  void registrationUser(UserDto userDto);

  UserDto findByUserId(int id);

  UserDto findByOwnerId(int id);

  UserDto findByUsername(String username);

  void deleteByUserId (int id);

  void deleteByOwnerId (int id);

  List<UserDto> getUsers();
}
