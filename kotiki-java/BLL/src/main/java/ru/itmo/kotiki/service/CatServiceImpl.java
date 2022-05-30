package ru.itmo.kotiki.service;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.repository.CatRepository;
import ru.itmo.kotiki.repository.UserRepository;

@Service
public class CatServiceImpl implements CatService {

  @Autowired
  private CatRepository catRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public CatDto save(String username, CatDto catDto) throws ValidationException {
    validateCatDto(catDto);
    Cat cat = catDto.toCat();
    cat.setOwner(userRepository.findByUsername(username).getOwner());
    return new CatDto(catRepository.save(cat));
  }

  @Override
  public void deleteById(String username, int id) {
    if (userRepository.findByUsername(username).getOwner().getId() != catRepository.getById(id).getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    catRepository.deleteById(id);
  }

  @Override
  public CatDto findById(String username, int id) {
    Cat cat = catRepository.getById(id);
    if (userRepository.findByUsername(username).getOwner().getId() != cat.getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    return new CatDto(cat);
  }

  @Override
  public List<CatDto> findAllByOwner(String username) {
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
