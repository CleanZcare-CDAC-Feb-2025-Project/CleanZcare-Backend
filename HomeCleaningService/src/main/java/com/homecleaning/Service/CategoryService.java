package com.homecleaning.Service;

import com.homecleaning.DTO.CategoryRequestDTO;
import com.homecleaning.Entity.Category;
import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequestDTO request);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
	
}
