package com.courier.courierApp.controller;

import com.courier.courierApp.dto.DepartmentRequest;
import com.courier.courierApp.model.entity.Department;
import com.courier.courierApp.service.DepartmentService;
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
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Départements", description = "Gestion des départements de l'entreprise")
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    @PostMapping
    @Operation(summary = "Créer un département", description = "Enregistre un nouveau département")
    public ResponseEntity<Department> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(request));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un département par ID", description = "Récupère les informations d'un département spécifique")
    public ResponseEntity<Department> getById(
            @Parameter(description = "ID du département") @PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }
    
    @GetMapping
    @Operation(summary = "Lister tous les départements", description = "Récupère la liste de tous les départements")
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un département", description = "Met à jour les informations d'un département existant")
    public ResponseEntity<Department> update(
            @Parameter(description = "ID du département") @PathVariable Long id, 
            @Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(departmentService.update(id, request));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un département", description = "Supprime définitivement un département")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID du département à supprimer") @PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
