package com.courier.courierApp.service;

import com.courier.courierApp.dto.DepartmentRequest;
import com.courier.courierApp.exception.ResourceNotFoundException;
import com.courier.courierApp.model.entity.Department;
import com.courier.courierApp.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    
    public Department create(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setCode(request.getCode());
        department.setDescription(request.getDescription());
        return departmentRepository.save(department);
    }
    
    public Department getById(Long id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
    
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
    
    public Department update(Long id, DepartmentRequest request) {
        Department department = getById(id);
        department.setName(request.getName());
        department.setCode(request.getCode());
        department.setDescription(request.getDescription());
        return departmentRepository.save(department);
    }
    
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
