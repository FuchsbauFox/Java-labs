package ru.itmo.service;

import java.util.List;
import ru.itmo.entity.Owner;

public interface OwnerService {

  List<Owner> getOwners();

  Owner getOwner(int id);

  void saveOwner(Owner owner);

  void deleteOwner(Owner owner);

  void updateOwner(Owner owner);
}
