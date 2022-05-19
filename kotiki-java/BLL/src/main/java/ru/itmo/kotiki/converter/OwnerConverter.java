package ru.itmo.kotiki.converter;

import java.util.List;
import ru.itmo.kotiki.entity.OwnerDto;
import ru.itmo.kotiki.exception.ValidationException;

public interface OwnerConverter {

  OwnerDto saveOwner(OwnerDto ownerDto) throws ValidationException;

  void deleteOwner(int id);

  OwnerDto getOwner(int id);

  List<OwnerDto> getOwners();
}
