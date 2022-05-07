package ru.itmo.kotiki.converter;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.entity.CatDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.repository.CatRepository;
import ru.itmo.kotiki.repository.OwnerRepository;

@Component
public class CatConverterImpl implements CatConverter {

  @Autowired
  private CatRepository catRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Override
  public CatDto saveCat(CatDto catDto) throws ValidationException {
    validateCatDto(catDto);
    Cat cat = catRepository.save(fromCatDtoToCat(catDto));
    return fromCatToCatDto(cat);
  }

  @Override
  public void deleteCat(int id) {
    catRepository.deleteById(id);
  }

  @Override
  public CatDto getCat(int id) {
    Cat cat = catRepository.getById(id);
    return fromCatToCatDto(cat);
  }

  @Override
  public List<CatDto> getCats() {
    List<Cat> cats = catRepository.findAll();
    List<CatDto> catsDto = new ArrayList<>();
    for (Cat cat :
        cats) {
      catsDto.add(fromCatToCatDto(cat));
    }
    return catsDto;
  }

  private static CatDto fromCatToCatDto(Cat cat) {
    CatDto catDto = new CatDto();

    catDto.setId(cat.getId());
    catDto.setName(cat.getName());
    catDto.setDateOfBirth(cat.getDateOfBirth());
    catDto.setBreed(cat.getBreed());
    catDto.setColor(cat.getColor().toString());
    catDto.setOwnerId(cat.getOwner().getId());

    return catDto;
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
    if (catDto.getId() <= 0) {
      throw new ValidationException("Impossible Id");
    }
    if (catDto.getOwnerId() <= 0) {
      throw new ValidationException("Impossible ownerId");
    }
  }

  private Cat fromCatDtoToCat(CatDto catDto) {
    Cat cat = new Cat(catDto.getId(),
        catDto.getName(),
        catDto.getDateOfBirth(),
        catDto.getBreed(),
        Color.valueOf(catDto.getColor()));
    cat.setOwner(ownerRepository.getById(catDto.getOwnerId()));

    return cat;
  }
}
