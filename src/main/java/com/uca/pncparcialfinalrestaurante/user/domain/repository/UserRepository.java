package com.uca.pncparcialfinalrestaurante.user.domain.repository;

import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
