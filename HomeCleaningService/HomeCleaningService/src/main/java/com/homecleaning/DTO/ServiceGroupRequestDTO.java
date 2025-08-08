package com.homecleaning.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceGroupRequestDTO {
    private String title;
    private int displayOrder;
    private MultipartFile icon;

    // Getters and Setters
}
