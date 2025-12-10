package com.courier.courierApp.model.entity;

import com.courier.courierApp.model.enums.CourierStatus;
import com.courier.courierApp.model.enums.CourierType;
import com.courier.courierApp.model.enums.MailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "couriers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String reference;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourierType type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MailType mailType;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private String sender;
    
    @Column(nullable = false)
    private String recipient;
    
    @Column(nullable = false)
    private String object;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourierStatus status;
    
    @ManyToOne
    @JoinColumn(name = "registered_by_id", nullable = false)
    private User registeredBy;
    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @Column(name = "attachment_url")
    private String attachmentUrl;
    
    @Column(name = "attachment_name")
    private String attachmentName;
    
    @Column(name = "attachment_type")
    private String attachmentType;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
