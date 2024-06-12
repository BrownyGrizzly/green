package com.green.authservice.repositories;

import com.green.authservice.entities.Role;
import com.green.authservice.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
