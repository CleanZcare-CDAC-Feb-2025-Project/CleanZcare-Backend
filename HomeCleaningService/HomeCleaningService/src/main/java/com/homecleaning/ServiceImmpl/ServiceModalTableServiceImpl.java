package com.homecleaning.ServiceImmpl;

import com.homecleaning.Entity.ServiceModalTable;
import com.homecleaning.Repository.ServiceModalTableRepository;
import com.homecleaning.Service.ServiceModalTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceModalTableServiceImpl implements ServiceModalTableService {

    @Autowired
    private ServiceModalTableRepository repository;

    @Override
    public List<ServiceModalTable> getAll() {
        return repository.findAll();
    }

    @Override
    public ServiceModalTable getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ServiceModalTable create(ServiceModalTable modal) {
        return repository.save(modal);
    }

    @Override
    public ServiceModalTable update(ServiceModalTable modal) {
        return repository.save(modal);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
