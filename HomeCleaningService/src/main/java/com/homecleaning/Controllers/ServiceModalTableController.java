package com.homecleaning.Controllers;


import com.homecleaning.Entity.ServiceModalTable;
import com.homecleaning.Service.ServiceModalTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/serviceModalTables")
public class ServiceModalTableController {

    @Autowired
    private ServiceModalTableService service;

    @GetMapping
    public List<ServiceModalTable> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceModalTable getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ServiceModalTable create(@RequestBody ServiceModalTable s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public ServiceModalTable update(@PathVariable Long id, @RequestBody ServiceModalTable s) {
        s.setId(id);
        return service.update(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
