package ru.itmo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.entity.Cat;
import ru.itmo.service.CatService;

@RestController
@RequestMapping("/cat")
public class CatController {

  private final CatService service;

  public CatController(CatService service) {
    this.service = service;
  }

  @GetMapping(value = "/getCats", produces = "application/json")
  public List<Cat> getCats() {
    return service.getCats();
  }

  @GetMapping(value = "/getCat/{id}", produces = "application/json")
  public Cat getCat(@PathVariable int id) {
    return service.getCat(id);
  }
}
