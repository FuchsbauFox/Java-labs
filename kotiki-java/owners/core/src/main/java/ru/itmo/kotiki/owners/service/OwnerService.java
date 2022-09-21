package ru.itmo.kotiki.owners.service;

import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.CatOwnerId;

public interface OwnerService {

  OwnerDto save(OwnerDto ownerDto);

  OwnerDto findById(int ownerId);

  void addCat(CatOwnerId catOwnerId);
}
