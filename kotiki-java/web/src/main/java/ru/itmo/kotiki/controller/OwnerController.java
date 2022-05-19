package ru.itmo.kotiki.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.entity.OwnerDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

  @Autowired
  private OwnerService ownerService;

  @PostMapping("/save")
  public OwnerDto saveOwner(@RequestBody OwnerDto ownerDto) throws ValidationException {
    return ownerService.saveOwner(ownerDto);
  }

  @DeleteMapping("/byId/{id}")
  public void deleteOwner(@PathVariable Integer id) {
    ownerService.deleteOwner(id);
  }

  @GetMapping(value = "/getById/{id}")
  public OwnerDto getOwner(@PathVariable int id) {
    return ownerService.getOwner(id);
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<OwnerDto> getOwners() {
    return ownerService.getOwners();
  }

}
