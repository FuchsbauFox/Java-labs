package ru.itmo.kotiki.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.h2.security.auth.AuthenticationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.ui.security.JwtUserDetails;

@RestController
@RequestMapping("/myAccount")
public class OwnerController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping(value = "/getMyInfo")
  public OwnerDto findOwner() throws AuthenticationException, JsonProcessingException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      int ownerId = jwtUserDetails.getOwnerId();
      return (OwnerDto) rabbitTemplate.convertSendAndReceive("findOwnerById", ownerId);
    }
    throw new AuthenticationException("403");
  }
}
