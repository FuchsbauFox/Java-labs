package ru.itmo.service.impl;

import ru.itmo.dao.impl.CatDaoImpl;
import ru.itmo.dao.CatDao;
import ru.itmo.model.Cat;
import ru.itmo.service.CatService;

public class CatServiceImpl implements CatService {

  private final CatDao catDao = new CatDaoImpl();

  public CatServiceImpl() {
  }

  public Cat findCat(int id) {
    return catDao.findCatById(id);
  }

  public void saveCat(Cat cat) {
    catDao.save(cat);
  }

  public void deleteCat(Cat cat) {
    catDao.delete(cat);
  }

  public void updateCat(Cat cat) {
    catDao.update(cat);
  }
}
