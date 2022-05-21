package ru.itmo.kotiki.service;

import java.util.List;
import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.exception.ValidationException;

public interface CatService {

  CatDto save(String username, CatDto catDto) throws ValidationException;

  void deleteById(String username, int id);

  CatDto findById(String username, int id);

  List<CatDto> findAllByOwner(String username);
}
