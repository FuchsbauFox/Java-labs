package ru.itmo.kotiki.cats.service;

import java.util.List;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.exception.ValidationException;

public interface CatService {

  CatDto save(int ownerId, CatDto catDto) throws ValidationException;

  void deleteById(int ownerId, int id);

  CatDto findById(int ownerId, int id);

  List<CatDto> findAllByOwner(int ownerId);
}
