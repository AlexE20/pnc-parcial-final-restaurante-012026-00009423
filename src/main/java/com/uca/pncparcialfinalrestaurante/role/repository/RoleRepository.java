package com.uca.pncparcialfinalrestaurante.role.repository;

import com.uca.pncparcialfinalrestaurante.role.entity.Role;
import com.uca.pncparcialfinalrestaurante.role.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
