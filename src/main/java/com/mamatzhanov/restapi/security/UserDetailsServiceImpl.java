package com.mamatzhanov.restapi.security;

import com.mamatzhanov.restapi.entities.UserEntity;
import com.mamatzhanov.restapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username:" + username + " doesn't exists"));
        SecurityUser securityUser = SecurityUserFactory.create(user);
        log.info("User with username: {} successfully loaded", username);
        return securityUser;
    }
}
