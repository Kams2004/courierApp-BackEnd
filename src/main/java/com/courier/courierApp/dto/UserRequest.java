package com.courier.courierApp.dto;

import com.courier.courierApp.model.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Requête pour créer ou modifier un utilisateur")
public class UserRequest {
    @NotBlank
    @Schema(description = "Prénom de l'utilisateur", example = "Jean")
    private String firstName;
    
    @NotBlank
    @Schema(description = "Nom de famille de l'utilisateur", example = "Dupont")
    private String lastName;
    
    @NotBlank
    @Email
    @Schema(description = "Adresse email de l'utilisateur", example = "jean.dupont@entreprise.com")
    private String email;
    
    @NotBlank
    @Schema(description = "Mot de passe de l'utilisateur", example = "motdepasse123")
    private String password;
    
    @NotNull
    @Schema(description = "Rôle de l'utilisateur", example = "USER")
    private UserRole role;
    
    @Schema(description = "Fonction de l'utilisateur", example = "Gestionnaire")
    private String fonction;
    
    @Schema(description = "ID du département", example = "1")
    private Long departmentId;
}
