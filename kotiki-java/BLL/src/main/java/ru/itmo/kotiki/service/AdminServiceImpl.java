package ru.itmo.kotiki.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.UserDto;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.repository.RoleRepository;
import ru.itmo.kotiki.repository.UserRepository;
import ru.itmo.kotiki.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private OwnerRepository ownerRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public void registrationUser(UserDto userDto) {
    Owner owner = ownerRepository.save(new Owner(userDto.getName(), userDto.getDateOfBirth()));

    List<Role> roles = new ArrayList<>();
    for(String role : userDto.getRoles()) {
      roles.add(roleRepository.findByRole(role));
    }
    userRepository.save(new User(
        userDto.getUsername(),
        userDto.getPassword(),
        userDto.isEnabled(),
        roles,
        owner));
  }

  @Override
  public UserDto findByUserId(int id) {
    User user = userRepository.getById(id);
    return new UserDto(user, user.getOwner());
  }

  @Override
  public UserDto findByOwnerId(int id) {
    Owner owner = ownerRepository.getById(id);
    return new UserDto(owner.getUser(), owner);
  }

  @Override
  public UserDto findByUsername(String username) {
    User user = userRepository.findByUsername(username);
    return new UserDto(user, user.getOwner());
  }

  @Override
  public void deleteByUserId(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public void deleteByOwnerId(int id) {
    ownerRepository.getById(id);
  }


  @Override
  public List<UserDto> getUsers() {
    List<User> users = userRepository.findAll();
    List<UserDto> usersDto = new ArrayList<>();
    for(User user : users) {
      usersDto.add(new UserDto(user, user.getOwner()));
    }

    return usersDto;
  }
}
