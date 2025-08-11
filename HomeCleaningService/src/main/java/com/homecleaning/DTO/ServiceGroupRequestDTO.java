package com.homecleaning.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceGroupRequestDTO {
    private String title;
    private int displayOrder;
    private MultipartFile icon;

    // List of existing service IDs to associate
    private List<Long> serviceIds;
}
