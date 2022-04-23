package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.model.CatDb;

@Repository
public interface CatRepository extends JpaRepository<CatDb, Integer> {

}
