package com.courier.courierApp.repository;

import com.courier.courierApp.model.entity.Courier;
import com.courier.courierApp.model.enums.CourierStatus;
import com.courier.courierApp.model.enums.CourierType;
import com.courier.courierApp.model.enums.MailType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByReference(String reference);
    List<Courier> findByType(CourierType type);
    List<Courier> findByStatus(CourierStatus status);
    List<Courier> findByDepartmentId(Long departmentId);
    List<Courier> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT c FROM Courier c WHERE " +
           "(:sender IS NULL OR LOWER(c.sender) LIKE LOWER(CONCAT('%', :sender, '%'))) AND " +
           "(:recipient IS NULL OR LOWER(c.recipient) LIKE LOWER(CONCAT('%', :recipient, '%'))) AND " +
           "(:object IS NULL OR LOWER(c.object) LIKE LOWER(CONCAT('%', :object, '%'))) AND " +
           "(:startDate IS NULL OR c.date >= :startDate) AND " +
           "(:endDate IS NULL OR c.date <= :endDate) AND " +
           "(:departmentId IS NULL OR c.department.id = :departmentId)")
    List<Courier> searchCouriers(@Param("sender") String sender,
                                 @Param("recipient") String recipient,
                                 @Param("object") String object,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate,
                                 @Param("departmentId") Long departmentId);
    
    @Query("SELECT COUNT(c) FROM Courier c WHERE c.type = :type AND MONTH(c.date) = :month AND YEAR(c.date) = :year")
    Long countByTypeAndMonth(@Param("type") CourierType type, @Param("month") int month, @Param("year") int year);
    
    @Query("SELECT c.mailType, COUNT(c) FROM Courier c GROUP BY c.mailType ORDER BY COUNT(c) DESC")
    List<Object[]> countByMailType();
    
    @Query("SELECT c.department.name, COUNT(c) FROM Courier c GROUP BY c.department.id, c.department.name ORDER BY COUNT(c) DESC")
    List<Object[]> countByDepartment();
}
