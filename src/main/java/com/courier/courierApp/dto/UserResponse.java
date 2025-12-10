package com.courier.courierApp.dto;

import com.courier.courierApp.model.enums.UserRole;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String fonction;
    private Long departmentId;
    private String departmentName;
    private LocalDateTime createdAt;
}
