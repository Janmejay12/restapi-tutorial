package com.example.RestAPI_practice.services;

import com.example.RestAPI_practice.dtos.request.LoginRequest;
import com.example.RestAPI_practice.dtos.request.RegisterRequest;
import com.example.RestAPI_practice.dtos.response.RegisterResponse;
import com.example.RestAPI_practice.entities.User;
import com.example.RestAPI_practice.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        if(userRepository.findByUsername(user.getUserName()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");

        userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserName(user.getUserName());
        registerResponse.setMessage("User registered succesfully...");
        return registerResponse;
    }

    
}
