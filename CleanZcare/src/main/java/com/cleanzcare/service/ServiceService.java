package com.cleanzcare.service;

import com.cleanzcare.entity.ServiceEntity;
import java.util.List;

public interface ServiceService {
    List<ServiceEntity> getAllServices();
    ServiceEntity getServiceById(Long id);
    ServiceEntity saveService(ServiceEntity service);
    void deleteService(Long id);
}
