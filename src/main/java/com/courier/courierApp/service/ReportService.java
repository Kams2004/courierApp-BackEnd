package com.courier.courierApp.service;

import com.courier.courierApp.dto.DetailedReportResponse;
import com.courier.courierApp.dto.ReportResponse;
import com.courier.courierApp.model.enums.CourierType;
import com.courier.courierApp.model.enums.MailType;
import com.courier.courierApp.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    
    private final CourierRepository courierRepository;
    
    public ReportResponse getMonthlyReport(int month, int year) {
        Long totalEntrants = courierRepository.countByTypeAndMonth(CourierType.ENTRANT, month, year);
        Long totalSortants = courierRepository.countByTypeAndMonth(CourierType.SORTANT, month, year);
        
        Map<String, Long> mailTypeStats = new HashMap<>();
        List<Object[]> mailTypeResults = courierRepository.countByMailType();
        for (Object[] result : mailTypeResults) {
            mailTypeStats.put(((MailType) result[0]).name(), (Long) result[1]);
        }
        
        Map<String, Long> departmentStats = new HashMap<>();
        List<Object[]> departmentResults = courierRepository.countByDepartment();
        for (Object[] result : departmentResults) {
            departmentStats.put((String) result[0], (Long) result[1]);
        }
        
        return new ReportResponse(totalEntrants, totalSortants, mailTypeStats, departmentStats);
    }
    
    public DetailedReportResponse getDetailedMonthlyReport(int month, int year) {
        DetailedReportResponse report = new DetailedReportResponse();
        report.setMois(month);
        report.setAnnee(year);
        
        // Courriers entrants/sortants par mois
        report.setCouriersEntrants(courierRepository.countByTypeAndMonth(CourierType.ENTRANT, month, year));
        report.setCouriersSortants(courierRepository.countByTypeAndMonth(CourierType.SORTANT, month, year));
        
        // Types de courriers les plus fréquents
        Map<String, Long> typesFrequents = new HashMap<>();
        List<Object[]> mailTypeResults = courierRepository.countByMailType();
        for (Object[] result : mailTypeResults) {
            typesFrequents.put(((MailType) result[0]).name(), (Long) result[1]);
        }
        report.setTypesFrequents(typesFrequents);
        
        // Départements les plus actifs
        Map<String, Long> departementsActifs = new HashMap<>();
        List<Object[]> departmentResults = courierRepository.countByDepartment();
        for (Object[] result : departmentResults) {
            departementsActifs.put((String) result[0], (Long) result[1]);
        }
        report.setDepartementsActifs(departementsActifs);
        
        return report;
    }
}
