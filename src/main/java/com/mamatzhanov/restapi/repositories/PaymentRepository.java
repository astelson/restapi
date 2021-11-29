package com.mamatzhanov.restapi.repositories;

import com.mamatzhanov.restapi.entities.PaymnetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymnetEntity, Long> {
}
