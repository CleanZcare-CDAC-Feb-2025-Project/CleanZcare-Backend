package com.homecleaning.ServiceImmpl;


import com.homecleaning.DTO.ServiceTableDto;
import com.homecleaning.DTO.ServiceTableRequestDTO;
import com.homecleaning.Entity.ServiceTable;
import com.homecleaning.Service.ServiceTableService;

import com.homecleaning.Repository.ServiceTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTableServiceImpl implements ServiceTableService {

    @Autowired
    private ServiceTableRepository repository;

    @Override
    public List<ServiceTable> getAll() {
        return repository.findAll();
    }

    @Override
    public ServiceTable getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ServiceTable create(ServiceTableRequestDTO request) {
    ServiceTable parent = new ServiceTable();
    parent.setTitle(request.getParentService().getTitle());
    parent.setPrice(Double.parseDouble(request.getParentService().getPrice()));
    parent.setDuration(request.getParentService().getDuration());
    parent.setDescription(request.getParentService().getDescription());

    List<ServiceTable> subServices = new ArrayList<>();
    for (ServiceTableDto dto : request.getSubServices()) {
        ServiceTable sub = new ServiceTable();
        sub.setTitle(dto.getTitle());
        sub.setPrice(Double.parseDouble(dto.getPrice()));
        sub.setDuration(dto.getDuration());
        sub.setDescription(dto.getDescription());
        sub.setParentService(parent); // Link to parent
        subServices.add(sub);
    }

    parent.setSubServices(subServices); // Link children to parent

    return repository.save(parent); // saves all due to cascade
}


    @Override
    public ServiceTable update(ServiceTable service) {
        return repository.save(service);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
