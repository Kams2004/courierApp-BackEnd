package com.courier.courierApp.util;

import com.courier.courierApp.model.enums.CourierType;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ReferenceGenerator {
    
    public String generateReference(CourierType type) {
        String prefix = type == CourierType.ENTRANT ? "ENT" : "SOR";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return prefix + "-" + timestamp;
    }
}
