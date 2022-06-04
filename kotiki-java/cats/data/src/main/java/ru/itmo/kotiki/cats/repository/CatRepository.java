package ru.itmo.kotiki.cats.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
  List<Cat> findAllByOwnerId(int ownerId);
}