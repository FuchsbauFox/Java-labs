package ru.itmo.kotiki.owners.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.owners.repository.OwnerRepository;

@EnableRabbit
@Service
public class OwnerServiceImpl implements OwnerService {

  @Autowired
  public OwnerRepository ownerRepository;

  @Override
  @RabbitListener(queues = "findOwnerByIdQueue")
  public OwnerDto findById(int ownerId) {
    return new OwnerDto(ownerRepository.getById(ownerId));
  }

}
