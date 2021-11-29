package com.mamatzhanov.restapi.services.implementation;

import com.mamatzhanov.restapi.entities.AccountEntity;
import com.mamatzhanov.restapi.entities.PaymnetEntity;
import com.mamatzhanov.restapi.entities.Status;
import com.mamatzhanov.restapi.exceptions.InsufficientBalanceException;
import com.mamatzhanov.restapi.repositories.AccountRepository;
import com.mamatzhanov.restapi.repositories.PaymentRepository;
import com.mamatzhanov.restapi.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public PaymnetEntity creditProcess(Double amount, AccountEntity account) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        PaymnetEntity paymnet = new PaymnetEntity();
        paymnet.setStatus(Status.CREDIT);
        paymnet.setCurrency(account.getCurrency());
        paymnet.setAmount(amount);
        paymnet.setAccount(account);
        return paymentRepository.save(paymnet);
    }

    @Override
    @Transactional
    public PaymnetEntity debitProcess(Double amount, AccountEntity account) {
        if ((account.getBalance() - amount) <= 0 ) {
            throw new InsufficientBalanceException("Insufficient Balance by account: " + account.getId());
        }
        account.setBalance(Math.round((account.getBalance() - amount) * 100.00 ) / 100.00);
        accountRepository.save(account);
        PaymnetEntity paymnet = new PaymnetEntity();
        paymnet.setStatus(Status.DEBIT);
        paymnet.setCurrency(account.getCurrency());
        paymnet.setAmount(amount);
        paymnet.setAccount(account);
        return paymentRepository.save(paymnet);
    }
}
