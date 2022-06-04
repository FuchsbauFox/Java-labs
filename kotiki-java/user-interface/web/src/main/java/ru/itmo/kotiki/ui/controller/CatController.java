package ru.itmo.kotiki.ui.controller;

import java.util.List;
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
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.ui.rabbitmq.RabbitMqSender;
import ru.itmo.kotiki.ui.security.JwtUserDetails;


@RestController
@RequestMapping("/myCats")
public class CatController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private RabbitMqSender rabbitMqSender;

  @PostMapping("/save")
  public CatDto save(@RequestBody CatDto catDto) throws ValidationException, AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();

      return rabbitTemplate.convertSendAndReceive("saveCat", ownerId, catDto);
      return catService.save(ownerId, catDto);
    }
    throw new AuthenticationException("403");
  }

  @DeleteMapping("/byId/{id}")
  public void deleteById(@PathVariable Integer id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      catService.deleteById(ownerId, id);
    }
  }

  @GetMapping(value = "/getById/{id}")
  public CatDto findById(@PathVariable int id) throws AuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return catService.findById(ownerId, id);
    }
    throw new AuthenticationException("403");
  }

  @GetMapping(value = "/getAll", produces = "application/json")
  public List<CatDto> findAll() throws AuthenticationException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return catService.findAllByOwner(ownerId);
    }
    throw new AuthenticationException("403");
  }
}
