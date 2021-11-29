package com.mamatzhanov.restapi.controllers;

import com.mamatzhanov.restapi.entities.AccountEntity;
import com.mamatzhanov.restapi.entities.UserEntity;
import com.mamatzhanov.restapi.exceptions.InsufficientBalanceException;
import com.mamatzhanov.restapi.model.Payment;
import com.mamatzhanov.restapi.security.jwt.JwtTokenProvider;
import com.mamatzhanov.restapi.services.PaymentService;
import com.mamatzhanov.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("payment")
    public Object paymnetProcessing(HttpServletRequest request) {
        String jwt = jwtTokenProvider.resolveToken(request);
        UserEntity user = userService.findByUsername(jwtTokenProvider.getUsername(jwt));
        List<AccountEntity> accounts = user.getAccounts();
        Payment payment = null;
        try {
            payment = Payment.toModel(paymentService.debitProcess(1.1, accounts.get(0)));
            return ResponseEntity.ok(payment);
        } catch (InsufficientBalanceException e) {
           return e.getMessage();
        }
    }
}
