package ru.itmo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Cat;
import ru.itmo.entity.Owner;
import ru.itmo.entity.impl.CatImpl;
import ru.itmo.entity.impl.OwnerImpl;
import ru.itmo.model.CatDb;
import ru.itmo.model.OwnerDb;
import ru.itmo.repository.OwnerRepository;
import ru.itmo.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

  private final OwnerRepository ownerRepository;

  @Autowired
  public OwnerServiceImpl(OwnerRepository repository) {
    this.ownerRepository = repository;
  }

  public List<Owner> getOwners() {
    List<OwnerDb> ownersDb = ownerRepository.findAll();
    List<Owner> owners = new ArrayList<>();
    for (OwnerDb owner :
        ownersDb) {
      List<Cat> cats = new ArrayList<>();
      for (CatDb cat :
          owner.getCats()) {
        cats.add(new CatImpl(
            cat.getId(),
            cat.getName(),
            cat.getDateOfBirth(),
            cat.getBreed(),
            cat.getColor(),
            cat.getOwner().getId()));
      }
      owners.add(new OwnerImpl(owner.getId(), owner.getName(), owner.getDateOfBirth(), cats));
    }
    return owners;
  }

  public Owner getOwner(int id) {
    OwnerDb ownerDb = ownerRepository.getById(id);
    List<Cat> cats = new ArrayList<>();
    for (CatDb cat :
        ownerDb.getCats()) {
      cats.add(new CatImpl(
          cat.getId(),
          cat.getName(),
          cat.getDateOfBirth(),
          cat.getBreed(),
          cat.getColor(),
          cat.getOwner().getId()));
    }
    return new OwnerImpl(ownerDb.getId(), ownerDb.getName(), ownerDb.getDateOfBirth(), cats);
  }

  public void saveOwner(Owner owner) {
    OwnerDb ownerDb = new OwnerDb(owner.getName(), owner.getDateOfBirth());
    ownerRepository.save(ownerDb);
  }

  public void deleteOwner(Owner owner) {
    OwnerDb ownerDb = new OwnerDb(owner.getName(), owner.getDateOfBirth());
    ownerRepository.delete(ownerDb);
  }

  public void updateOwner(Owner owner) {
    OwnerDb ownerDb = ownerRepository.getById(owner.getId());
    ownerDb.setName(owner.getName());
    ownerDb.setDateOfBirth(owner.getDateOfBirth());
    ownerRepository.save(ownerDb);
  }
}