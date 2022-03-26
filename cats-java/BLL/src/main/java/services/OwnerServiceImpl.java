package services;

import dao.OwnerDaoImpl;
import interfaces.OwnerDao;
import interfaces.OwnerService;
import models.Owner;

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
