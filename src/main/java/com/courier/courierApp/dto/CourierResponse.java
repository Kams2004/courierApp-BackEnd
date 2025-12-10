package com.courier.courierApp.dto;

import com.courier.courierApp.model.enums.CourierStatus;
import com.courier.courierApp.model.enums.CourierType;
import com.courier.courierApp.model.enums.MailType;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CourierResponse {
    private Long id;
    private String reference;
    private CourierType type;
    private MailType mailType;
    private LocalDate date;
    private String sender;
    private String recipient;
    private String object;
    private CourierStatus status;
    private String registeredByName;
    private String departmentName;
    private String attachmentUrl;
    private String attachmentName;
    private String attachmentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
