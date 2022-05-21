package ru.itmo.kotiki.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

  public List<Cat> findAllByOwnerId(int ownerId);

  public List<Cat> findAllByBreed(String breed);

  public List<Cat> findAllByColor(Color color);
}
