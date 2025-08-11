package com.homecleaning.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadRequest {
    private MultipartFile file;
    private String category; // categoryId
}
