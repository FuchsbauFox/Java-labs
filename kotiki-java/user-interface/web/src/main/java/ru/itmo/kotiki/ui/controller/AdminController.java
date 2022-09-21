package ru.itmo.kotiki.ui.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.dto.UserDto;
import ru.itmo.kotiki.ui.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UserService userService;

  @PostMapping("/registrationUser")
  public void registrationUser(@RequestBody UserDto userDto, @RequestBody OwnerDto ownerDto) {
    userService.registrationUser(userDto, ownerDto);
  }

  @GetMapping(value = "/byUserId/{id}")
  public UserDto getOwner(@PathVariable int id) {
    return userService.findById(id);
  }

  @GetMapping(value = "/byOwnerId/{id}")
  public UserDto findByOwnerId(@PathVariable int id) {
    return userService.findById(id);
  }

  @GetMapping(value = "/byUsername/{username}")
  public UserDto findByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @DeleteMapping("/byUserId/{id}")
  public void deleteByUserId(@PathVariable int id) {
    userService.deleteById(id);
  }

  @DeleteMapping("/byOwnerId/{id}")
  public void deleteByOwnerId(@PathVariable int id) {
    userService.deleteById(id);
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<UserDto> getOwners() {
    return userService.getUsers();
  }
}
