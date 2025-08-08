package com.homecleaning.Service;

import com.homecleaning.Entity.ServiceModalTable;
import java.util.List;

public interface ServiceModalTableService {
    List<ServiceModalTable> getAll();
    ServiceModalTable getById(Long id);
    ServiceModalTable create(ServiceModalTable modal);
    ServiceModalTable update(ServiceModalTable modal);
    void delete(Long id);
}

