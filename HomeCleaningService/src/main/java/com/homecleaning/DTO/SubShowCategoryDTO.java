package com.homecleaning.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
public class SubShowCategoryDTO {
	 private String title;
	    private String category;
	    private List<String> imageCategories;
}
