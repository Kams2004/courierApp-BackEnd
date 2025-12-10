package com.courier.courierApp.service;

import com.courier.courierApp.dto.UserRequest;
import com.courier.courierApp.dto.UserResponse;
import com.courier.courierApp.exception.ResourceNotFoundException;
import com.courier.courierApp.model.entity.Department;
import com.courier.courierApp.model.entity.User;
import com.courier.courierApp.repository.DepartmentRepository;
import com.courier.courierApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserResponse create(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setFonction(request.getFonction());
        
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            user.setDepartment(department);
        }
        
        return toResponse(userRepository.save(user));
    }
    
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }
    
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
    
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setFonction(request.getFonction());
        
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            user.setDepartment(department);
        }
        
        return toResponse(userRepository.save(user));
    }
    
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<UserResponse> getByDepartment(Long departmentId) {
        return userRepository.findByDepartmentId(departmentId).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
    
    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setFonction(user.getFonction());
        response.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getId() : null);
        response.setDepartmentName(user.getDepartment() != null ? user.getDepartment().getName() : null);
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
