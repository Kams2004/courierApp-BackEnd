package com.courier.courierApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Requête de connexion")
public class LoginRequest {
    @NotBlank
    @Email
    @Schema(description = "Email de l'utilisateur", example = "admin@entreprise.com")
    private String email;
    
    @NotBlank
    @Schema(description = "Mot de passe", example = "password123")
    private String password;
}