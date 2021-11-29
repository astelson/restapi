package com.mamatzhanov.restapi.services.implementation;

import com.mamatzhanov.restapi.entities.*;
import com.mamatzhanov.restapi.model.UserRequestDto;
import com.mamatzhanov.restapi.repositories.AccountRepository;
import com.mamatzhanov.restapi.repositories.RoleRepository;
import com.mamatzhanov.restapi.repositories.UserRepository;
import com.mamatzhanov.restapi.services.PaymentService;
import com.mamatzhanov.restapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    private final PaymentService paymentService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository, PaymentService paymentService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional
    public UserEntity addUser(UserRequestDto userDto) {
        UserEntity user = new UserEntity();
        RoleEntity userRole = roleRepository.findByName("ROLE_USER").get();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public AccountEntity createAccountByUser(UserEntity user, Currency currency) {
        AccountEntity account = new AccountEntity();
        account.setCurrency(currency);
        account.setUser(user);
        account.setStatus(Status.ACTIVE);
        account.setBalance(0.00);
        return accountRepository.save(account);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
