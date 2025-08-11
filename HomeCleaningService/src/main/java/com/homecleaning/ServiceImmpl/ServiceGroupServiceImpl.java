package com.homecleaning.ServiceImmpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.homecleaning.DTO.ServiceGroupRequestDTO;
import com.homecleaning.Entity.Category;
import com.homecleaning.Entity.ServiceGroup;
import com.homecleaning.Entity.ServiceTable;
import com.homecleaning.Repository.CategoryRepository;
import com.homecleaning.Repository.ServiceGroupRepository;
import com.homecleaning.Repository.ServiceTableRepository;
import com.homecleaning.Service.ServiceGroupService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceGroupServiceImpl implements ServiceGroupService {

	@Autowired
	private ServiceGroupRepository repository;
	private ServiceTableRepository serviceRepository;
	private CategoryRepository categoryRepository;
	private Cloudinary cloudinary;

	@Override
	public ServiceGroup createServiceGroup(ServiceGroupRequestDTO dto) {
    ServiceGroup serviceGroup = new ServiceGroup();
    serviceGroup.setTitle(dto.getTitle());
    serviceGroup.setDisplayOrder(dto.getDisplayOrder());

    // Handle Many-to-Many: attach existing services by ID
    if (dto.getServiceIds() != null && !dto.getServiceIds().isEmpty()) {
        List<ServiceTable> services = serviceRepository.findAllById(dto.getServiceIds());
        serviceGroup.setServices(services);

        // Optional: maintain bidirectional relationship
        for (ServiceTable service : services) {
            service.getServiceGroups().add(serviceGroup);
        }
    }

    MultipartFile iconFile = dto.getIcon();

    // Upload icon to Cloudinary if present
    if (iconFile != null && !iconFile.isEmpty()) {
        try {
            Map uploadResult = cloudinary.uploader().upload(iconFile.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            serviceGroup.setIconPath(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload icon to Cloudinary", e);
        }
    }

    return repository.save(serviceGroup);
}


	@Override
	public List<ServiceGroup> getAllServiceGroups() {
		return repository.findAll();
	}

	@Override
	public Optional<ServiceGroup> getServiceGroupById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ServiceGroup updateServiceGroup(Long id, ServiceGroup serviceGroup) {
		serviceGroup.setId(id);
		return repository.save(serviceGroup);
	}

	@Override
	public void deleteServiceGroup(Long id) {
		repository.deleteById(id);
	}


	@Override
	public List<ServiceGroup> getServiceGroupByCategory(String category) {
	    Category cat = categoryRepository.findByTitle(category);
	    return repository.getServiceGroupByCategory(cat);
	}


}
