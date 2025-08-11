package com.homecleaning.Service;

import com.homecleaning.DTO.ServiceTableRequestDTO;
import com.homecleaning.Entity.ServiceTable;
import java.util.List;

public interface ServiceTableService {
    List<ServiceTable> getAll();
    ServiceTable getById(Long id);
    ServiceTable create(ServiceTableRequestDTO request);
    ServiceTable update(ServiceTable service);
    void delete(Long id);
}
