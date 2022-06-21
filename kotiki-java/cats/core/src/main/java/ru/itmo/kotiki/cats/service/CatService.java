package ru.itmo.kotiki.cats.service;

import java.util.List;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.entity.CatDtoOwnerId;
import ru.itmo.kotiki.entity.OwnerIdCatId;
import ru.itmo.kotiki.exception.ValidationException;

public interface CatService {

  CatDto save(CatDtoOwnerId catDto) throws ValidationException;

  void deleteById(OwnerIdCatId ids);

  CatDto findById(OwnerIdCatId ids);

  List<CatDto> findAllByOwner(int ownerId);
}
