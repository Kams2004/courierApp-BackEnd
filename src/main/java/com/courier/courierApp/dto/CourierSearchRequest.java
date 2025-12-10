package com.courier.courierApp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CourierSearchRequest {
    private String sender;
    private String recipient;
    private String object;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long departmentId;
}
