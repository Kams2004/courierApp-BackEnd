package com.courier.courierApp.controller;

import com.courier.courierApp.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "Fichiers", description = "Gestion des fichiers attachés aux courriers")
public class FileController {
    
    private final FileUploadService fileUploadService;
    
    @PostMapping("/upload")
    @Operation(summary = "Upload fichier", description = "Upload un fichier vers Cloudinary")
    public ResponseEntity<Map<String, String>> uploadFile(
            @Parameter(description = "Fichier à uploader") @RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileUploadService.uploadFile(file);
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            response.put("filename", file.getOriginalFilename());
            response.put("contentType", file.getContentType());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erreur lors de l'upload: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/download/{courierId}")
    @Operation(summary = "Télécharger fichier", description = "Télécharge le fichier attaché à un courrier")
    public ResponseEntity<Map<String, String>> getDownloadInfo(
            @Parameter(description = "ID du courrier") @PathVariable Long courierId) {
        return ResponseEntity.ok(fileUploadService.getFileInfo(courierId));
    }
}