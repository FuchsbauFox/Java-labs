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
import ru.itmo.kotiki.entity.CatDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.service.CatService;


@RestController
@RequestMapping("/cat")
public class CatController {

  @Autowired
  private CatService catService;

  @PostMapping("/save")
  public CatDto saveCat(@RequestBody CatDto catDto) throws ValidationException {
    return catService.saveCat(catDto);
  }

  @DeleteMapping("/deleteById/{id}")
  public void deleteCat(@PathVariable Integer id) {
    catService.deleteCat(id);
  }

  @GetMapping(value = "/getById/{id}")
  public CatDto getCat(@PathVariable int id) {
    return catService.getCat(id);
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<CatDto> getCats() {
    return catService.getCats();
  }
}
