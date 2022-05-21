package ru.itmo.kotiki.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.security.JwtUserDetails;
import ru.itmo.kotiki.service.CatService;


@RestController
@RequestMapping("/myCat")
public class CatController {

  @Autowired
  private CatService catService;

  @PostMapping("/save")
  public CatDto save(@RequestBody CatDto catDto) throws ValidationException {
    JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int ownerId = userDetails.getOwnerId();
    return catService.save(ownerId, catDto);
  }

  @DeleteMapping("/byId/{id}")
  public void deleteById(@PathVariable Integer id) {
    JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int ownerId = userDetails.getOwnerId();
    catService.deleteById(ownerId, id);
  }

  @GetMapping(value = "/getById/{id}")
  public CatDto findById(@PathVariable int id) {
    JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int ownerId = userDetails.getOwnerId();
    return catService.findById(ownerId, id);
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<CatDto> findAll() {
    JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int ownerId = userDetails.getOwnerId();
    return catService.findAllByOwner(ownerId);
  }
}
