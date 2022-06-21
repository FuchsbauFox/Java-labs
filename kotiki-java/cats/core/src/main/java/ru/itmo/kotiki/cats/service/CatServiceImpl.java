package ru.itmo.kotiki.cats.service;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.CatDtoOwnerId;
import ru.itmo.kotiki.entity.CatOwnerId;
import ru.itmo.kotiki.entity.OwnerIdCatId;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.cats.repository.CatRepository;

@EnableRabbit
@Service
public class CatServiceImpl implements CatService {

  @Autowired
  private CatRepository catRepository;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Override
  @RabbitListener(queues = "saveCatQueue")
  public CatDto save(CatDtoOwnerId catDto) throws ValidationException {
    validateCatDto(catDto.getCatDto());
    Cat cat = catDto.getCatDto().toCat();
    rabbitTemplate.convertSendAndReceive("addCat", new CatOwnerId(cat, catDto.getOwnerId()));
    return new CatDto(catRepository.save(cat));
  }

  @Override
  @RabbitListener(queues =  "deleteByIdQueue")
  public void deleteById(OwnerIdCatId ids) {
    if (ids.getOwnerId() != catRepository.getById(ids.getCatId()).getOwner().getId()){
      throw new ValidationException("Cat " + ids.getCatId() + " not found");
    }
    catRepository.deleteById(ids.getCatId());
  }

  @Override
  @RabbitListener(queues = "findByIdQueue")
  public CatDto findById(OwnerIdCatId ids) {
    Cat cat = catRepository.getById(ids.getCatId());
    if (ids.getOwnerId() != cat.getOwner().getId()){
      throw new ValidationException("Cat " + ids.getCatId() + " not found");
    }
    return new CatDto(cat);
  }

  @Override
  @RabbitListener(queues = "findAllByOwnerQueue")
  public List<CatDto> findAllByOwner(int ownerId) {
    List<CatDto> catsDto = new ArrayList<>();
    for(Cat cat : catRepository.findAllByOwnerId(ownerId)) {
      catsDto.add(new CatDto(cat));
    }
    return catsDto;
  }

  private boolean checkColor(String color) {
    for (Color c : Color.values()) {
      if (c.name().equals(color)) {
        return true;
      }
    }
    return false;
  }

  private void validateCatDto(CatDto catDto) throws ValidationException {
    if (isNull(catDto)) {
      throw new ValidationException("Object cat is null");
    }
    if (!checkColor(catDto.getColor())) {
      throw new ValidationException("Color not found");
    }
  }

}
