package com.homecleaning.ServiceImmpl;

import com.homecleaning.Entity.ServiceModalResource;
import com.homecleaning.Repository.ServiceModalResourceRepository;
import com.homecleaning.Service.ServiceModalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceModalResourceServiceImpl implements ServiceModalResourceService {

    @Autowired
    private ServiceModalResourceRepository resourceRepository;

    @Override
    public ServiceModalResource saveResource(ServiceModalResource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<ServiceModalResource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public ServiceModalResource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceModalResource updateResource(Long id, ServiceModalResource resource) {
        Optional<ServiceModalResource> existing = resourceRepository.findById(id);
        if (existing.isPresent()) {
            ServiceModalResource updated = existing.get();
            updated.setImageType(resource.getImageType());
            updated.setImageOrder(resource.getImageOrder());
            updated.setImageUrl(resource.getImageUrl());
            updated.setCaption(resource.getCaption());
            updated.setServiceModule(resource.getServiceModule());
            return resourceRepository.save(updated);
        }
        return null;
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
