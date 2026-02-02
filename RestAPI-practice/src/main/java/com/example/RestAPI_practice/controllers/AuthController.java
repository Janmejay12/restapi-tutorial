package com.example.RestAPI_practice.controllers;

import com.example.RestAPI_practice.dtos.request.LoginRequest;
import com.example.RestAPI_practice.dtos.request.RegisterRequest;
import com.example.RestAPI_practice.dtos.response.RegisterResponse;
import com.example.RestAPI_practice.entities.User;
import com.example.RestAPI_practice.services.AuthService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponse> loginUser(@RequestBody LoginRequest loginrequest)
    {
        return ResponseEntity.ok(authService.register(loginrequest));

    }
}
