package com.homecleaning.DTO;

import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowSectionDataRequest {
	@Column(nullable = true)
	private String title;
    private List<SlideDataRequest> data;
}
