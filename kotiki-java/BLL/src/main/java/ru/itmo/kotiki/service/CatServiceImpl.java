package ru.itmo.kotiki.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.converter.CatConverter;
import ru.itmo.kotiki.entity.CatDto;
import ru.itmo.kotiki.exception.ValidationException;


@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

  private final CatConverter catConverter;

  @Override
  public CatDto saveCat(CatDto catDto) throws ValidationException {
    return catConverter.saveCat(catDto);
  }

  @Override
  public void deleteCat(int id) {
    catConverter.deleteCat(id);
  }

  @Override
  public CatDto getCat(int id) {
    return catConverter.getCat(id);
  }

  @Override
  public List<CatDto> getCats() {
    return catConverter.getCats();
  }

}
