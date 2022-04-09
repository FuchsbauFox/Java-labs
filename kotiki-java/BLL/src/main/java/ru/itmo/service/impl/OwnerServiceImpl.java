package ru.itmo.service.impl;

import ru.itmo.dao.impl.OwnerDaoImpl;
import ru.itmo.dao.OwnerDao;
import ru.itmo.model.Owner;
import ru.itmo.service.OwnerService;

public class OwnerServiceImpl implements OwnerService {

  private final OwnerDao ownerDao;

  public OwnerServiceImpl() {
    ownerDao = new OwnerDaoImpl();
  }

  public Owner findOwner(int id) {
    return ownerDao.findOwnerById(id);
  }

  public void saveOwner(Owner owner) {
    ownerDao.save(owner);
  }

  public void deleteOwner(Owner owner) {
    ownerDao.delete(owner);
  }

  public void updateOwner(Owner owner) {
    ownerDao.update(owner);
  }
}
