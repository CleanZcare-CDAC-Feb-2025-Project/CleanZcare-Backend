package com.cleanzcare.service;

import java.util.List;

import com.cleanzcare.entity.Category;

public interface CategoryService {
	Category addCategory(Category category);
    List<Category> getAllCategories();
    public Category getCategoryById(Long id);
    boolean deleteCategory(Long id);
    Category updateCategory(Long id, Category updatedCategory);
}
