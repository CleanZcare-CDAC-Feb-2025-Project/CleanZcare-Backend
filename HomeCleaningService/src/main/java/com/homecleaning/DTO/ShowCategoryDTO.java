package com.homecleaning.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
public class ShowCategoryDTO {

	  private String title;
	    private String category;
	    private String page;
	    private List<SubShowCategoryDTO> subShowCategories; // optional, for image metadata
//	    private String extraImagesCategory; // ✅ Add this
	    private List<String> extraImageCategories;
 // <--- must match key in JSON

}

