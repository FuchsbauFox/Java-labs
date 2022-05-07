package ru.itmo.kotiki.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.converter.OwnerConverter;
import ru.itmo.kotiki.entity.OwnerDto;
import ru.itmo.kotiki.exception.ValidationException;

@Service
public class OwnerServiceImpl implements OwnerService {

  @Autowired
  private OwnerConverter ownerConverter;

  @Override
  public OwnerDto saveOwner(OwnerDto ownerDto) throws ValidationException {
    return ownerConverter.saveOwner(ownerDto);
  }

  @Override
  public void deleteOwner(int id) {
    ownerConverter.deleteOwner(id);
  }

  @Override
  public OwnerDto getOwner(int id) {
    return ownerConverter.getOwner(id);
  }

  @Override
  public List<OwnerDto> getOwners() {
    return ownerConverter.getOwners();
  }
}
