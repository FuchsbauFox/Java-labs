package ru.itmo.kotiki.converter;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.accessory.Color;
import ru.itmo.kotiki.entity.CatDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.repository.CatRepository;
import ru.itmo.kotiki.repository.OwnerRepository;

@Component
@AllArgsConstructor
public class CatConverterImpl implements CatConverter {

  private final CatRepository catRepository;
  private final OwnerRepository ownerRepository;

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

  private void validateCatDto(CatDto catDto) throws ValidationException {
    if (isNull(catDto)) {
      throw new ValidationException("Object cat is null");
    }
  }

  public Cat fromCatDtoToCat(CatDto catDto) {
    Cat cat = new Cat(catDto.getId(),
        catDto.getName(),
        catDto.getDateOfBirth(),
        catDto.getBreed(),
        Color.valueOf(catDto.getColor()));
    cat.setOwner(ownerRepository.getById(catDto.getOwnerId()));

    return cat;
  }

  public static CatDto fromCatToCatDto(Cat cat) {
    return CatDto.builder()
        .id(cat.getId())
        .name(cat.getName())
        .dateOfBirth(cat.getDateOfBirth())
        .breed(cat.getBreed())
        .color(cat.getColor().toString())
        .ownerId(cat.getOwner().getId())
        .build();
  }
}
