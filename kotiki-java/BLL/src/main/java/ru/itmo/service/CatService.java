package ru.itmo.service;

import ru.itmo.model.Cat;

public interface CatService {

  Cat findCat(int id);

  void saveCat(Cat cat);

  void deleteCat(Cat cat);

  void updateCat(Cat cat);
}