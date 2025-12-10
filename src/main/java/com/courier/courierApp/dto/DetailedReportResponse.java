package com.courier.courierApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Map;

@Data
@Schema(description = "Rapport détaillé des courriers")
public class DetailedReportResponse {
    
    @Schema(description = "Nombre de courriers entrants par mois")
    private Long couriersEntrants;
    
    @Schema(description = "Nombre de courriers sortants par mois")
    private Long couriersSortants;
    
    @Schema(description = "Types de courriers les plus fréquents")
    private Map<String, Long> typesFrequents;
    
    @Schema(description = "Départements les plus actifs")
    private Map<String, Long> departementsActifs;
    
    @Schema(description = "Mois du rapport")
    private int mois;
    
    @Schema(description = "Année du rapport")
    private int annee;
}