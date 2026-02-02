package com.example.RestAPI_practice.dtos.request;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
 private String employeenName;
}
