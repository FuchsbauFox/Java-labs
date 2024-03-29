package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Role findByRole(String role);
}
