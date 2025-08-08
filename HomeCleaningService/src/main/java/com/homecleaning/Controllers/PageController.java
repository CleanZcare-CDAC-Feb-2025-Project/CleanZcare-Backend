package com.homecleaning.Controllers;


import com.homecleaning.Entity.Page;
import com.homecleaning.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
@CrossOrigin(origins = "*")
public class PageController {

	
    @Autowired
    private PageService pageService;

    @PostMapping
    public ResponseEntity<Page> createPage(@RequestBody Page page) {
        return ResponseEntity.ok(pageService.createPage(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id) {
        return ResponseEntity.ok(pageService.getPageById(id));
    }

    @GetMapping
    public ResponseEntity<List<Page>> getAllPages() {
        return ResponseEntity.ok(pageService.getAllPages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page page) {
        return ResponseEntity.ok(pageService.updatePage(id, page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return ResponseEntity.noContent().build();
    }
}
