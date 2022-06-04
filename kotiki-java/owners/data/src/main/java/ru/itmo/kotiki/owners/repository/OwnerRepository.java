package ru.itmo.kotiki.owners.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
