package com.homecleaning.Repository;

import com.homecleaning.Entity.ShowCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowCategoryRepository extends JpaRepository<ShowCategory, Long> {
}

