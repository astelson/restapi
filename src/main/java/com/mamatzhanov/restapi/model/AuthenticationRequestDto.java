package com.mamatzhanov.restapi.model;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
