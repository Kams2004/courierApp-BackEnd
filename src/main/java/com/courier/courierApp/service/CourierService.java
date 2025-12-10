package com.courier.courierApp.service;

import com.courier.courierApp.dto.CourierRequest;
import com.courier.courierApp.dto.CourierResponse;
import com.courier.courierApp.dto.CourierSearchRequest;
import com.courier.courierApp.exception.ResourceNotFoundException;
import com.courier.courierApp.model.entity.Courier;
import com.courier.courierApp.model.entity.Department;
import com.courier.courierApp.model.entity.User;
import com.courier.courierApp.model.enums.CourierStatus;
import com.courier.courierApp.repository.CourierRepository;
import com.courier.courierApp.repository.DepartmentRepository;
import com.courier.courierApp.repository.UserRepository;
import com.courier.courierApp.util.ReferenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourierService {
    
    private final CourierRepository courierRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final ReferenceGenerator referenceGenerator;
    
    public CourierResponse create(CourierRequest request, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Department department = departmentRepository.findById(request.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        
        Courier courier = new Courier();
        courier.setReference(referenceGenerator.generateReference(request.getType()));
        courier.setType(request.getType());
        courier.setMailType(request.getMailType());
        courier.setDate(request.getDate());
        courier.setSender(request.getSender());
        courier.setRecipient(request.getRecipient());
        courier.setObject(request.getObject());
        courier.setStatus(CourierStatus.EN_ATTENTE);
        courier.setRegisteredBy(user);
        courier.setDepartment(department);
        
        return toResponse(courierRepository.save(courier));
    }
    
    public CourierResponse getById(Long id) {
        Courier courier = courierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Courier not found"));
        return toResponse(courier);
    }
    
    public List<CourierResponse> getAll() {
        return courierRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
    
    public CourierResponse updateStatus(Long id, CourierStatus status) {
        Courier courier = courierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Courier not found"));
        courier.setStatus(status);
        return toResponse(courierRepository.save(courier));
    }
    
    public List<CourierResponse> search(CourierSearchRequest request) {
        return courierRepository.searchCouriers(
            request.getSender(),
            request.getRecipient(),
            request.getObject(),
            request.getStartDate(),
            request.getEndDate(),
            request.getDepartmentId()
        ).stream().map(this::toResponse).collect(Collectors.toList());
    }
    
    public List<CourierResponse> getByDepartment(Long departmentId) {
        return courierRepository.findByDepartmentId(departmentId).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
    
    public CourierResponse attachFile(Long courierId, String fileUrl, String fileName, String fileType) {
        Courier courier = courierRepository.findById(courierId)
            .orElseThrow(() -> new ResourceNotFoundException("Courier not found"));
        
        courier.setAttachmentUrl(fileUrl);
        courier.setAttachmentName(fileName);
        courier.setAttachmentType(fileType);
        
        return toResponse(courierRepository.save(courier));
    }
    
    public void delete(Long id) {
        courierRepository.deleteById(id);
    }
    
    private CourierResponse toResponse(Courier courier) {
        CourierResponse response = new CourierResponse();
        response.setId(courier.getId());
        response.setReference(courier.getReference());
        response.setType(courier.getType());
        response.setMailType(courier.getMailType());
        response.setDate(courier.getDate());
        response.setSender(courier.getSender());
        response.setRecipient(courier.getRecipient());
        response.setObject(courier.getObject());
        response.setStatus(courier.getStatus());
        response.setRegisteredByName(courier.getRegisteredBy().getFirstName() + " " + courier.getRegisteredBy().getLastName());
        response.setDepartmentName(courier.getDepartment().getName());
        response.setAttachmentUrl(courier.getAttachmentUrl());
        response.setAttachmentName(courier.getAttachmentName());
        response.setAttachmentType(courier.getAttachmentType());
        response.setCreatedAt(courier.getCreatedAt());
        response.setUpdatedAt(courier.getUpdatedAt());
        return response;
    }
}
