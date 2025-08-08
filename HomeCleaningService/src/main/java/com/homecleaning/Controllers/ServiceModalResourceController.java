package com.homecleaning.Controllers;

import com.homecleaning.Entity.ServiceModalResource;
import com.homecleaning.Service.ServiceModalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ServiceModalResourceController {

    @Autowired
    private ServiceModalResourceService resourceService;

    @PostMapping
    public ServiceModalResource create(@RequestBody ServiceModalResource resource) {
        return resourceService.saveResource(resource);
    }

    @GetMapping
    public List<ServiceModalResource> getAll() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public ServiceModalResource getById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @PutMapping("/{id}")
    public ServiceModalResource update(@PathVariable Long id, @RequestBody ServiceModalResource resource) {
        return resourceService.updateResource(id, resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}
