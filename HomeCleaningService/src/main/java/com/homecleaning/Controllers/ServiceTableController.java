package com.homecleaning.Controllers;


import com.homecleaning.DTO.ServiceTableRequestDTO;
import com.homecleaning.Entity.ServiceTable;
import com.homecleaning.Service.ServiceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/services")
public class ServiceTableController {

    @Autowired
    private ServiceTableService service;

    @GetMapping
    public List<ServiceTable> getAllServices() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceTable getServiceById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> addService(@RequestBody ServiceTableRequestDTO request) {
        try {
            service.create(request);
            return ResponseEntity.ok("Service and sub-services saved successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving services: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ServiceTable updateService(@PathVariable Long id, @RequestBody ServiceTable updatedService) {
        updatedService.setId(id);
        return service.update(updatedService);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        service.delete(id);
    }
}
