package com.courier.courierApp.controller;

import com.courier.courierApp.dto.AuthResponse;
import com.courier.courierApp.dto.LoginRequest;
import com.courier.courierApp.dto.UserRequest;
import com.courier.courierApp.dto.UserResponse;
import com.courier.courierApp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Gestion de l'authentification et des comptes utilisateurs")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary = "Connexion", description = "Authentification d'un utilisateur avec email et mot de passe")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/register")
    @Operation(summary = "Inscription", description = "Création d'un nouveau compte utilisateur")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}