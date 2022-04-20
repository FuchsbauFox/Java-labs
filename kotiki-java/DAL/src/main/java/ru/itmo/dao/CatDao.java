package ru.itmo.dao;

import ru.itmo.model.Cat;

public interface CatDao {

  Cat findCatById(int id);

  void save(Cat cat);

  void update(Cat cat);

  void delete(Cat cat);
}
