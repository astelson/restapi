package com.mamatzhanov.restapi.model;

import com.mamatzhanov.restapi.entities.PaymnetEntity;
import lombok.Data;

@Data
public class Payment {
    private Long id;
    private Double amount;
    private Double balance;

    public static Payment toModel(PaymnetEntity paymnetEntity) {
        Payment payment = new Payment();
        payment.setId(paymnetEntity.getId());
        payment.setAmount(paymnetEntity.getAmount());
        payment.setBalance(paymnetEntity.getAccount().getBalance());
        return payment;
    }
}
