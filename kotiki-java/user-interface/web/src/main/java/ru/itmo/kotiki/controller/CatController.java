package ru.itmo.kotiki.controller;

import java.util.List;
import org.h2.security.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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


@RestController
@RequestMapping("/myCat")
public class CatController {

  @Autowired
  private CatService catService;

  @PostMapping("/save")
  public CatDto save(@RequestBody CatDto catDto) throws ValidationException, AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUsername = authentication.getName();
      return catService.save(currentUsername, catDto);
    }
    throw new AuthenticationException("403");
  }

  @DeleteMapping("/byId/{id}")
  public void deleteById(@PathVariable Integer id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUsername = authentication.getName();
      catService.deleteById(currentUsername, id);
    }
  }

  @GetMapping(value = "/getById/{id}")
  public CatDto findById(@PathVariable int id) throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUsername = authentication.getName();
      return catService.findById(currentUsername, id);
    }
    throw new AuthenticationException("403");
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<CatDto> findAll() throws AuthenticationException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUsername = authentication.getName();
      return catService.findAllByOwner(currentUsername);
    }
    throw new AuthenticationException("403");
  }
}
