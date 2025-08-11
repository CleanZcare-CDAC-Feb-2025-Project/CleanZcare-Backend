package com.homecleaning.Repository;

import com.homecleaning.Entity.Category;
import com.homecleaning.Entity.ServiceGroup;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceGroupRepository extends JpaRepository<ServiceGroup, Long> {
    List<ServiceGroup> getServiceGroupByCategory(Category category);
}

