package com.homecleaning.DTO;

import lombok.AllArgsConstructor;
//SlideDataRequest.java
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SlideDataRequest {
	private String title="afasfasfdsfsd";
    private MultipartFile image; // File to upload to Cloudinary
    private Double rating;
    private Integer reviews;
    private BigDecimal price;
    private String currency="INR"; // INR, USD...
    private Long category; // categoryId
}
