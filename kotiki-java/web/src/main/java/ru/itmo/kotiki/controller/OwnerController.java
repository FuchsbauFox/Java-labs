package ru.itmo.kotiki.controller;

import java.util.List;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

  private final OwnerService ownerService;

  @PostMapping("/save")
  public OwnerDto saveCat(@RequestBody OwnerDto ownerDto) throws ValidationException {
    return ownerService.saveOwner(ownerDto);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteCat(@PathVariable Integer id) {
    ownerService.deleteOwner(id);
  }

  @GetMapping(value = "/getOwner/{id}")
  public OwnerDto getCat(@PathVariable int id) {
    return ownerService.getOwner(id);
  }

  @GetMapping(value = "/getOwners", produces = "application/json")
  public List<OwnerDto> getCats() {
    return ownerService.getOwners();
  }

}
