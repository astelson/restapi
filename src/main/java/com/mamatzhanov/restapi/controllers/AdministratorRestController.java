package com.mamatzhanov.restapi.controllers;

import com.mamatzhanov.restapi.entities.AccountEntity;
import com.mamatzhanov.restapi.entities.Currency;
import com.mamatzhanov.restapi.entities.UserEntity;
import com.mamatzhanov.restapi.model.UserRequestDto;
import com.mamatzhanov.restapi.services.PaymentService;
import com.mamatzhanov.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdministratorRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("addUser")
    public ResponseEntity<HttpStatus> addUser (@RequestBody UserRequestDto userDto) {
        AccountEntity account = userService.createAccountByUser(userService.addUser(userDto), Currency.USD);
        paymentService.creditProcess(8.00, account);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
