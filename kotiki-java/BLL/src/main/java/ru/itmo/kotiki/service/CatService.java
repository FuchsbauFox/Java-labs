package ru.itmo.kotiki.service;

import java.util.List;
import ru.itmo.kotiki.entity.CatDto;
import ru.itmo.kotiki.exception.ValidationException;

public interface CatService {

  CatDto saveCat(CatDto cat) throws ValidationException;

  void deleteCat(int id);

  CatDto getCat(int id);

  List<CatDto> getCats();
}
