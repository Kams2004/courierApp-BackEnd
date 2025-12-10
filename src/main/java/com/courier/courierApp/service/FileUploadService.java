package com.courier.courierApp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.courier.courierApp.model.entity.Courier;
import com.courier.courierApp.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    
    private final Cloudinary cloudinary;
    private final CourierRepository courierRepository;
    
    public String uploadFile(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
                "folder", "courier-files",
                "resource_type", "auto"
            )
        );
        return (String) uploadResult.get("secure_url");
    }
    
    public void deleteFile(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    
    public Map<String, String> getFileInfo(Long courierId) {
        Courier courier = courierRepository.findById(courierId)
            .orElseThrow(() -> new RuntimeException("Courrier non trouvé"));
        
        Map<String, String> fileInfo = new HashMap<>();
        fileInfo.put("url", courier.getAttachmentUrl());
        fileInfo.put("filename", courier.getAttachmentName());
        fileInfo.put("contentType", courier.getAttachmentType());
        return fileInfo;
    }
}