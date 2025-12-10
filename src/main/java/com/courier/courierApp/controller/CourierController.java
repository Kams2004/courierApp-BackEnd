package com.courier.courierApp.controller;

import com.courier.courierApp.dto.CourierRequest;
import com.courier.courierApp.dto.CourierResponse;
import com.courier.courierApp.dto.CourierSearchRequest;
import com.courier.courierApp.model.enums.CourierStatus;
import com.courier.courierApp.service.CourierService;
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
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
@Tag(name = "Courriers", description = "Gestion des courriers entrants et sortants")
public class CourierController {
    
    private final CourierService courierService;
    
    @PostMapping
    @Operation(summary = "Créer un nouveau courrier", description = "Enregistre un nouveau courrier entrant ou sortant")
    public ResponseEntity<CourierResponse> create(
            @Valid @RequestBody CourierRequest request, 
            @Parameter(description = "ID de l'utilisateur créateur") @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courierService.create(request, userId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un courrier par ID", description = "Récupère les détails d'un courrier spécifique")
    public ResponseEntity<CourierResponse> getById(
            @Parameter(description = "ID du courrier") @PathVariable Long id) {
        return ResponseEntity.ok(courierService.getById(id));
    }
    
    @GetMapping
    @Operation(summary = "Lister tous les courriers", description = "Récupère la liste de tous les courriers")
    public ResponseEntity<List<CourierResponse>> getAll() {
        return ResponseEntity.ok(courierService.getAll());
    }
    
    @PatchMapping("/{id}/status")
    @Operation(summary = "Mettre à jour le statut", description = "Modifie le statut d'un courrier")
    public ResponseEntity<CourierResponse> updateStatus(
            @Parameter(description = "ID du courrier") @PathVariable Long id, 
            @Parameter(description = "Nouveau statut") @RequestParam CourierStatus status) {
        return ResponseEntity.ok(courierService.updateStatus(id, status));
    }
    
    @PostMapping("/search")
    @Operation(summary = "Rechercher des courriers", description = "Recherche des courriers selon différents critères")
    public ResponseEntity<List<CourierResponse>> search(@RequestBody CourierSearchRequest request) {
        return ResponseEntity.ok(courierService.search(request));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un courrier", description = "Supprime définitivement un courrier")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID du courrier à supprimer") @PathVariable Long id) {
        courierService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Courriers par département", description = "Récupère tous les courriers d'un département spécifique")
    public ResponseEntity<List<CourierResponse>> getByDepartment(
            @Parameter(description = "ID du département") @PathVariable Long departmentId) {
        return ResponseEntity.ok(courierService.getByDepartment(departmentId));
    }
    
    @PostMapping("/{id}/attachment")
    @Operation(summary = "Attacher fichier", description = "Attache un fichier à un courrier")
    public ResponseEntity<CourierResponse> attachFile(
            @Parameter(description = "ID du courrier") @PathVariable Long id,
            @Parameter(description = "URL du fichier") @RequestParam String fileUrl,
            @Parameter(description = "Nom du fichier") @RequestParam String fileName,
            @Parameter(description = "Type du fichier") @RequestParam String fileType) {
        return ResponseEntity.ok(courierService.attachFile(id, fileUrl, fileName, fileType));
    }
}
