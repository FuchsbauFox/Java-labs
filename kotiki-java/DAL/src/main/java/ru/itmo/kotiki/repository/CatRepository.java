package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
