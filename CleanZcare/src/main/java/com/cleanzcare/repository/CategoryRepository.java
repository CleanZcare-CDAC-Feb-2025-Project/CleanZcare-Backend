package com.cleanzcare.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanzcare.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
