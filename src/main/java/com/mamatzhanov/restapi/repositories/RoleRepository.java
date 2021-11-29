package com.mamatzhanov.restapi.repositories;

import com.mamatzhanov.restapi.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName (String name);
}
