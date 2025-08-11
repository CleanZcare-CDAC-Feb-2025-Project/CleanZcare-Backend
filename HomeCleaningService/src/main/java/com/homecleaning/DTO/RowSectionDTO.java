package com.homecleaning.DTO;

import lombok.AllArgsConstructor;
//RowSectionDTO.java
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowSectionDTO {
	 private int index;
	    private String type; // Sliders, Sliders1, Sliders2, Sliders3
	    private RowSectionDataRequest data;
	    private List<ImageUploadRequest> images; // for Sliders2
}
