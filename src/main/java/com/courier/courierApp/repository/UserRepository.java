package com.courier.courierApp.repository;

import com.courier.courierApp.model.entity.User;
import com.courier.courierApp.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByDepartmentId(Long departmentId);
    List<User> findByRole(UserRole role);
    boolean existsByEmail(String email);
}
