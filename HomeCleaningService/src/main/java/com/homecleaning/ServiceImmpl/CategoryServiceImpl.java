package com.homecleaning.ServiceImmpl;

import com.homecleaning.DTO.CategoryRequestDTO;
import com.homecleaning.Entity.Category;
import com.homecleaning.Entity.ServiceGroup;
import com.homecleaning.Repository.CategoryRepository;
import com.homecleaning.Repository.ServiceGroupRepository;
import com.homecleaning.Service.CategoryService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private final ServiceGroupRepository serviceGroupRepository;


    @Override
    public Category createCategory(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setTitle(dto.getTitle());
        category.setDescription(dto.getDescription());
        // Fetch service groups by ID
        if (dto.getGroup() != null && !dto.getGroup().isEmpty()) {
            List<ServiceGroup> groups = serviceGroupRepository.findAllById(dto.getGroup());
            category.setGroup(groups);
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existing = categoryRepository.findById(id);
        if (existing.isPresent()) {
            Category category = existing.get();
            category.setTitle(updatedCategory.getTitle());
            category.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


}
