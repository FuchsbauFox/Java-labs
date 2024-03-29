package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);
}
