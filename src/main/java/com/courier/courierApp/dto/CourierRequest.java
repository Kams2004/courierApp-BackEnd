package com.courier.courierApp.dto;

import com.courier.courierApp.model.enums.CourierType;
import com.courier.courierApp.model.enums.MailType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "Requête pour créer ou modifier un courrier")
public class CourierRequest {
    @NotNull
    @Schema(description = "Type de courrier", example = "ENTRANT")
    private CourierType type;
    
    @NotNull
    @Schema(description = "Type de mail", example = "LETTRE")
    private MailType mailType;
    
    @NotNull
    @Schema(description = "Date de réception/expédition", example = "2024-01-15")
    private LocalDate date;
    
    @NotBlank
    @Schema(description = "Expéditeur du courrier", example = "Société ABC")
    private String sender;
    
    @NotBlank
    @Schema(description = "Destinataire du courrier", example = "Service Commercial")
    private String recipient;
    
    @NotBlank
    @Schema(description = "Objet du courrier", example = "Demande de devis")
    private String object;
    
    @NotNull
    @Schema(description = "ID du département", example = "1")
    private Long departmentId;
}
