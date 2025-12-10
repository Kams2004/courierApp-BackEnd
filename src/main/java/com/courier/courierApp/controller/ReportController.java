package com.courier.courierApp.controller;

import com.courier.courierApp.dto.DetailedReportResponse;
import com.courier.courierApp.dto.ReportResponse;
import com.courier.courierApp.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Rapports", description = "Génération de rapports et statistiques")
public class ReportController {
    
    private final ReportService reportService;
    
    @GetMapping("/monthly")
    @Operation(summary = "Rapport mensuel", description = "Génère un rapport détaillé pour un mois donné")
    public ResponseEntity<ReportResponse> getMonthlyReport(
            @Parameter(description = "Mois (1-12)") @RequestParam int month, 
            @Parameter(description = "Année") @RequestParam int year) {
        return ResponseEntity.ok(reportService.getMonthlyReport(month, year));
    }
    
    @GetMapping("/detailed")
    @Operation(summary = "Rapport détaillé", description = "Rapport complet avec statistiques détaillées")
    public ResponseEntity<DetailedReportResponse> getDetailedReport(
            @Parameter(description = "Mois (1-12)") @RequestParam int month,
            @Parameter(description = "Année") @RequestParam int year) {
        return ResponseEntity.ok(reportService.getDetailedMonthlyReport(month, year));
    }
}
