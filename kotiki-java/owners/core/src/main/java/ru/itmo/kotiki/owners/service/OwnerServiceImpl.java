package ru.itmo.kotiki.owners.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.CatOwnerId;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.owners.repository.OwnerRepository;

@EnableRabbit
@Service
public class OwnerServiceImpl implements OwnerService {

  @Autowired
  public OwnerRepository ownerRepository;

  @Override
  @RabbitListener(queues = "saveOwner")
  public OwnerDto save(OwnerDto ownerDto) {
    Owner owner = ownerDto.toOwner();
    return new OwnerDto(ownerRepository.save(owner));
  }

  @Override
  @RabbitListener(queues = "findOwnerByIdQueue")
  public OwnerDto findById(int ownerId) {
    return new OwnerDto(ownerRepository.getById(ownerId));
  }

  @Override
  @RabbitListener(queues = "addCatQueue")
  public void addCat(CatOwnerId catOwnerId) {
    ownerRepository.getById(catOwnerId.getOwnerId()).addCat(catOwnerId.getCat());
  }

}
