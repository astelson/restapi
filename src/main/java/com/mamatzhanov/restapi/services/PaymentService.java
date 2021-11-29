package com.mamatzhanov.restapi.services;

import com.mamatzhanov.restapi.entities.AccountEntity;
import com.mamatzhanov.restapi.entities.PaymnetEntity;

public interface PaymentService {
    PaymnetEntity creditProcess(Double amount, AccountEntity account);
    PaymnetEntity debitProcess(Double amount, AccountEntity account);
}
