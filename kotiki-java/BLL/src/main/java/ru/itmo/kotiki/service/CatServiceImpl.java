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
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.service.CatService;

@Service
public class CatServiceImpl implements CatService {

  @Autowired
  private CatRepository catRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Override
  public CatDto save(int ownerId, CatDto catDto) throws ValidationException {
    validateCatDto(catDto);
    Cat cat = catDto.toCat();
    cat.setOwner(ownerRepository.getById(ownerId));
    return catDto.fromCat(catRepository.save(cat));
  }

  @Override
  public void deleteById(int ownerId, int id) {
    if (ownerId != catRepository.getById(id).getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    catRepository.deleteById(id);
  }

  @Override
  public CatDto findById(int ownerId, int id) {
    Cat cat = catRepository.getById(id);
    if (ownerId != cat.getOwner().getId()){
      throw new ValidationException("Cat " + id + " not found");
    }
    CatDto catDto = new CatDto();
    return catDto.fromCat(cat);
  }

  @Override
  public List<CatDto> findAllByOwner(int ownerId) {
    List<CatDto> catsDto = new ArrayList<>();
    for(Cat cat : catRepository.findAllByOwnerId(ownerId)) {
      CatDto catDto = new CatDto();
      catsDto.add(catDto.fromCat(cat));
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
