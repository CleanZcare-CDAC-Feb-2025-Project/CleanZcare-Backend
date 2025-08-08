package com.homecleaning.Service;

import com.homecleaning.Entity.ServiceModalResource;
import java.util.List;

public interface ServiceModalResourceService {
    ServiceModalResource saveResource(ServiceModalResource resource);
    List<ServiceModalResource> getAllResources();
    ServiceModalResource getResourceById(Long id);
    ServiceModalResource updateResource(Long id, ServiceModalResource resource);
    void deleteResource(Long id);
}
