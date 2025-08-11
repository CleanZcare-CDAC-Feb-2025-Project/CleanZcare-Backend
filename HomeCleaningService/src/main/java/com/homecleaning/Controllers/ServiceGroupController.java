package com.homecleaning.Controllers;

import com.homecleaning.DTO.ServiceGroupRequestDTO;
import com.homecleaning.Entity.ServiceGroup;
import com.homecleaning.Service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/service-groups")
public class ServiceGroupController {

    @Autowired
    private ServiceGroupService serviceGroupService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<ServiceGroup> addServiceGroup(@ModelAttribute ServiceGroupRequestDTO dto) {
        ServiceGroup createdGroup = serviceGroupService.createServiceGroup(dto);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ServiceGroup>> getAllServiceGroups() {
        return ResponseEntity.ok(serviceGroupService.getAllServiceGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceGroup> getServiceGroupById(@PathVariable Long id) {
        return serviceGroupService.getServiceGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ServiceGroup>> getServiceGroupByCategory(@PathVariable String category) {
        List<ServiceGroup> groups = serviceGroupService.getServiceGroupByCategory(category);
        if (groups.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceGroup> updateServiceGroup(@PathVariable Long id, @RequestBody ServiceGroup serviceGroup) {
        return ResponseEntity.ok(serviceGroupService.updateServiceGroup(id, serviceGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceGroup(@PathVariable Long id) {
        serviceGroupService.deleteServiceGroup(id);
        return ResponseEntity.noContent().build();
    }
}
