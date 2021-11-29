package com.mamatzhanov.restapi.repositories;

import com.mamatzhanov.restapi.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
