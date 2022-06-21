package ru.itmo.kotiki.ui.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.dto.UserDto;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;
import ru.itmo.kotiki.ui.repository.RoleRepository;
import ru.itmo.kotiki.ui.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public void registrationUser(UserDto userDto, OwnerDto ownerDto) {
    Owner owner = (Owner) rabbitTemplate.convertSendAndReceive("saveOwner", ownerDto);
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
  public UserDto findById(int id) {
    User user = userRepository.getById(id);
    return new UserDto(user);
  }

  @Override
  public UserDto findByUsername(String username) {
    User user = userRepository.findByUsername(username);
    return new UserDto(user);
  }

  @Override
  public void deleteById(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<UserDto> getUsers() {
    List<User> users = userRepository.findAll();
    List<UserDto> usersDto = new ArrayList<>();
    for(User user : users) {
      usersDto.add(new UserDto(user));
    }

    return usersDto;
  }
}
