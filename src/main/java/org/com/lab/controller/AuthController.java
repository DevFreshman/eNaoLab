package org.com.lab.controller;

import org.com.lab.dto.request.LoginRequest;
import org.com.lab.dto.request.RegisterRequest;
import org.com.lab.dto.response.LoginResponse;
import org.com.lab.services.AuthServices;
import org.example.javaframework.web.api.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    private final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @PostMapping("/register")
    public Response<Void> register(@RequestBody RegisterRequest registerRequest) {
        authServices.register(registerRequest);
        return Response.failure(HttpStatus.CREATED.toString(), "User registered successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authServices.login(loginRequest);
    }
}