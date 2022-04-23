package ru.itmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.model.OwnerDb;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDb, Integer> {

}
