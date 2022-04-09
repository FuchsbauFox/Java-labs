package inter;

import model.Owner;

public interface OwnerService {

  Owner findOwner(int id);

  void saveOwner(Owner owner);

  void deleteOwner(Owner owner);

  void updateOwner(Owner owner);
}
