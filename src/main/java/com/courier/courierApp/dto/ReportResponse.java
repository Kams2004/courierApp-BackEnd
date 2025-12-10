package com.courier.courierApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Long totalEntrants;
    private Long totalSortants;
    private Map<String, Long> mailTypeStats;
    private Map<String, Long> departmentStats;
}
