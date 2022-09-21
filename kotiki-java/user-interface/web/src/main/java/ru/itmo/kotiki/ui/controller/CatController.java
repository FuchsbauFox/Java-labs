package ru.itmo.kotiki.ui.controller;

import org.h2.security.auth.AuthenticationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import ru.itmo.kotiki.entity.CatDtoOwnerId;
import ru.itmo.kotiki.entity.OwnerIdCatId;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.ui.security.JwtUserDetails;


@RestController
@RequestMapping("/myCats")
public class CatController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostMapping("/save")
  public CatDto save(@RequestBody CatDto catDto) throws ValidationException, AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return (CatDto) rabbitTemplate.convertSendAndReceive("saveCat", new CatDtoOwnerId(catDto, ownerId));
    }
    throw new AuthenticationException("403");
  }

  @DeleteMapping("/byId/{id}")
  public void deleteById(@PathVariable Integer id) throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      rabbitTemplate.convertSendAndReceive("deleteCatById", new OwnerIdCatId(ownerId, id));
    }
    throw new AuthenticationException("403");
  }

  @GetMapping(value = "/getById/{id}")
  public CatDto findById(@PathVariable int id) throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return (CatDto) rabbitTemplate.convertSendAndReceive("findCatById", new OwnerIdCatId(ownerId, id));
    }
    throw new AuthenticationException("403");
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public Object findAll() throws AuthenticationException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return rabbitTemplate.convertSendAndReceive("findAllCatsByOwner", ownerId);
    }
    throw new AuthenticationException("403");
  }
}
