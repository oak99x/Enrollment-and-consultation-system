package com.ms.microservice.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
}
