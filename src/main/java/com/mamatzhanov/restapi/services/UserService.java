package com.mamatzhanov.restapi.services;

import com.mamatzhanov.restapi.entities.AccountEntity;
import com.mamatzhanov.restapi.entities.Currency;
import com.mamatzhanov.restapi.entities.UserEntity;
import com.mamatzhanov.restapi.model.UserRequestDto;

public interface UserService {
    UserEntity findByUsername(String username);

    UserEntity addUser(UserRequestDto userDto);

    AccountEntity createAccountByUser(UserEntity user, Currency currency);
}
