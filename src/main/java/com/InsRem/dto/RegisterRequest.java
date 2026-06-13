package com.InsRem.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 10,max = 10)
    private String mobile;

    @Size(min = 6)
    private String password;
}