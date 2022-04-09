package inter;

import model.Owner;

public interface OwnerDao {

  Owner findOwnerById(int id);

  void save(Owner owner);

  void update(Owner owner);

  void delete(Owner owner);
}
