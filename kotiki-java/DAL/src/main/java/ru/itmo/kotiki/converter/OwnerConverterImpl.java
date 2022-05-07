package ru.itmo.kotiki.converter;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.entity.OwnerDto;
import ru.itmo.kotiki.exception.ValidationException;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.repository.OwnerRepository;

@Component
@AllArgsConstructor
public class OwnerConverterImpl implements OwnerConverter {

  private final OwnerRepository ownerRepository;

  @Override
  public OwnerDto saveOwner(OwnerDto ownerDto) throws ValidationException {
    validateOwnerDto(ownerDto);
    Owner owner = ownerRepository.save(fromOwnerDtoToOwner(ownerDto));
    return fromOwnerToOwnerDto(owner);
  }

  @Override
  public void deleteOwner(int id) {
    ownerRepository.deleteById(id);
  }

  @Override
  public OwnerDto getOwner(int id) {
    Owner owner = ownerRepository.getById(id);
    return fromOwnerToOwnerDto(owner);
  }

  @Override
  public List<OwnerDto> getOwners() {
    List<Owner> owners = ownerRepository.findAll();
    List<OwnerDto> ownersDto = new ArrayList<>();
    for (Owner owner :
        owners) {
      ownersDto.add(fromOwnerToOwnerDto(owner));
    }
    return ownersDto;
  }

  private void validateOwnerDto(OwnerDto ownerDto) throws ValidationException {
    if (isNull(ownerDto)) {
      throw new ValidationException("Object owner is null");
    }
  }

  private Owner fromOwnerDtoToOwner(OwnerDto ownerDto) {
    return new Owner(ownerDto.getId(),
        ownerDto.getName(),
        ownerDto.getDateOfBirth());
  }

  private OwnerDto fromOwnerToOwnerDto(Owner owner) {
    List<Cat> cats = owner.getCats();
    List<Integer> catsId = new ArrayList<>();
    for (Cat cat :
        cats) {
      catsId.add(cat.getId());
    }

    return OwnerDto.builder()
        .id(owner.getId())
        .name(owner.getName())
        .dateOfBirth(owner.getDateOfBirth())
        .catsId(catsId)
        .build();
  }
}
