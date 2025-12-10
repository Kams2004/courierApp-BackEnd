package com.courier.courierApp.service;

import com.courier.courierApp.dto.AuthResponse;
import com.courier.courierApp.dto.LoginRequest;
import com.courier.courierApp.dto.UserRequest;
import com.courier.courierApp.dto.UserResponse;
import com.courier.courierApp.exception.ResourceNotFoundException;
import com.courier.courierApp.model.entity.Department;
import com.courier.courierApp.model.entity.User;
import com.courier.courierApp.repository.DepartmentRepository;
import com.courier.courierApp.repository.UserRepository;
import com.courier.courierApp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        
        String jwtToken = jwtService.generateToken(user);
        UserResponse userResponse = mapToUserResponse(user);
        
        return new AuthResponse(jwtToken, userResponse);
    }
    
    public UserResponse register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setFonction(request.getFonction());
        
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé"));
            user.setDepartment(department);
        }
        
        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }
    
    private UserResponse mapToUserResponse(User user) {
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