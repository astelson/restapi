package com.mamatzhanov.restapi.model;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
}
