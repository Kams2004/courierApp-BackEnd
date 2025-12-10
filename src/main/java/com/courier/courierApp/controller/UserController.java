package com.courier.courierApp.controller;

import com.courier.courierApp.dto.UserRequest;
import com.courier.courierApp.dto.UserResponse;
import com.courier.courierApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs de l'application")
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Operation(summary = "Créer un utilisateur", description = "Enregistre un nouvel utilisateur dans le système")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un utilisateur par ID", description = "Récupère les informations d'un utilisateur spécifique")
    public ResponseEntity<UserResponse> getById(
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
    
    @GetMapping
    @Operation(summary = "Lister tous les utilisateurs", description = "Récupère la liste de tous les utilisateurs")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un utilisateur", description = "Met à jour les informations d'un utilisateur existant")
    public ResponseEntity<UserResponse> update(
            @Parameter(description = "ID de l'utilisateur") @PathVariable Long id, 
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur", description = "Supprime définitivement un utilisateur")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de l'utilisateur à supprimer") @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Utilisateurs par département", description = "Récupère tous les utilisateurs d'un département spécifique")
    public ResponseEntity<List<UserResponse>> getByDepartment(
            @Parameter(description = "ID du département") @PathVariable Long departmentId) {
        return ResponseEntity.ok(userService.getByDepartment(departmentId));
    }
}
