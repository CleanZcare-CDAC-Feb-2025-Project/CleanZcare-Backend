package com.homecleaning.Service;

import com.homecleaning.DTO.ServiceGroupRequestDTO;
import com.homecleaning.Entity.ServiceGroup;

import java.util.List;
import java.util.Optional;

public interface ServiceGroupService {
//    ServiceGroup saveServiceGroup(ServiceGroup serviceGroup);
    List<ServiceGroup> getAllServiceGroups();
    Optional<ServiceGroup> getServiceGroupById(Long id);
    ServiceGroup updateServiceGroup(Long id, ServiceGroup serviceGroup);
    void deleteServiceGroup(Long id);
	ServiceGroup createServiceGroup(ServiceGroupRequestDTO dto);
}
