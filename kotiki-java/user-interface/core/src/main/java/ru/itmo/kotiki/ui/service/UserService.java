package ru.itmo.kotiki.ui.service;

import java.util.List;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.dto.UserDto;

public interface UserService {
  void registrationUser(UserDto userDto, OwnerDto ownerDto);

  UserDto findById(int id);

  UserDto findByUsername(String username);

  void deleteById(int id);

  List<UserDto> getUsers();
}
