package ru.itmo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.entity.Owner;
import ru.itmo.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

  private final OwnerService service;

  public OwnerController(OwnerService service) {
    this.service = service;
  }

  @GetMapping(value = "/getCats", produces = "application/json")
  public List<Owner> getOwners() {
    return service.getOwners();
  }

  @GetMapping(value = "/getCat/{id}", produces = "application/json")
  public Owner getOwner(@PathVariable int id) {
    return service.getOwner(id);
  }
}
