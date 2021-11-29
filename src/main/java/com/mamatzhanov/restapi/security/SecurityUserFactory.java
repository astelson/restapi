package com.mamatzhanov.restapi.security;

import com.mamatzhanov.restapi.entities.RoleEntity;
import com.mamatzhanov.restapi.entities.Status;
import com.mamatzhanov.restapi.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SecurityUserFactory {

    public SecurityUserFactory() {
    }

    public static SecurityUser create(UserEntity user) {
        return new SecurityUser(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
                );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> userRoles) {
        return userRoles.stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
