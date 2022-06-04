package ru.itmo.kotiki.cats.service;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.cats.repository.CatRepository;

@EnableRabbit
@Service
public class CatServiceImpl implements CatService {

  @Autowired
  private CatRepository catRepository;

  @Override
  @RabbitListener(queues = "saveQueue")
  public CatDto save(int ownerId, CatDto catDto) throws ValidationException {
    validateCatDto(catDto);
    Cat cat = catDto.toCat();
    cat.setOwner(ownerRepository.);
    return new CatDto(catRepository.save(cat));
  }

  @Override
  @RabbitListener(queues =  "deleteByIdQueue")
  public void deleteById(int ownerId, int id) {
    if (userRepository.findByUsername(username).getOwner().getId() != catRepository.getById(id).getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    catRepository.deleteById(id);
  }

  @Override
  @RabbitListener(queues = "findByIdQueue")
  public CatDto findById(int ownerId, int id) {
    Cat cat = catRepository.getById(id);
    if (userRepository.findByUsername(username).getOwner().getId() != cat.getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    return new CatDto(cat);
  }

  @Override
  @RabbitListener(queues = "findAllByOwnerQueue")
  public List<CatDto> findAllByOwner(int ownerId) {
    List<CatDto> catsDto = new ArrayList<>();
    for(Cat cat : catRepository.findAllByOwnerId(userRepository.findByUsername(username).getOwner().getId())) {
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
