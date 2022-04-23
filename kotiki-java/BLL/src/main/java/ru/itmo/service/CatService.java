package ru.itmo.service;

import java.util.List;
import ru.itmo.entity.Cat;

public interface CatService {

  List<Cat> getCats();

  Cat getCat(int id);

  void saveCat(Cat cat);

  void deleteCat(Cat cat);

  void updateCat(Cat cat);
}
