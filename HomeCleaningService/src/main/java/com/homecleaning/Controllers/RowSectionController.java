package com.homecleaning.Controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import com.homecleaning.DTO.RowSectionDTO;
import com.homecleaning.Service.RowSectionService;

@RestController
@RequestMapping("/api/row-sections")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RowSectionController {

    private final RowSectionService rowSectionService;

    // Create
    @PostMapping
    public ResponseEntity<?> createRowSection(@ModelAttribute RowSectionDTO request) {
        try {
            return ResponseEntity.ok(rowSectionService.createRowSection(request));
        } catch (MultipartException e) {
            System.out.println("MultipartException: " + e);
            return ResponseEntity.badRequest().body("File upload error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return ResponseEntity.internalServerError().body("Error: " + e);
        }
    }

    // Read all
    @GetMapping
    public ResponseEntity<?> getAllRowSections() {
        try {
            return ResponseEntity.ok(rowSectionService.getAllRowSections());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return ResponseEntity.internalServerError().body("Error: " + e);
        }
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRowSectionById(@PathVariable Long id) {
        try {
            var rowSection = rowSectionService.getRowSectionById(id);
            if (rowSection == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(rowSection);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return ResponseEntity.internalServerError().body("Error: " + e);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRowSection(@PathVariable Long id, @ModelAttribute RowSectionDTO request) {
        try {
            var updated = rowSectionService.updateRowSection(id, request);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (MultipartException e) {
            System.out.println("MultipartException: " + e);
            return ResponseEntity.badRequest().body("File upload error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return ResponseEntity.internalServerError().body("Error: " + e);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRowSection(@PathVariable Long id) {
        try {
            boolean deleted = rowSectionService.deleteRowSection(id);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok("RowSection deleted successfully");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return ResponseEntity.internalServerError().body("Error: " + e);
        }
    }
}
