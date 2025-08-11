package com.homecleaning.Repository;

import com.homecleaning.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String category);
}
