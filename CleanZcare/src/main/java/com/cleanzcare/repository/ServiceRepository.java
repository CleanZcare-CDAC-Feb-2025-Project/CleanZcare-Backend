package com.cleanzcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cleanzcare.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
