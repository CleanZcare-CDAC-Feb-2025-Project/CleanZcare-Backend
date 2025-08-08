package com.homecleaning.ServiceImmpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.homecleaning.DTO.ServiceGroupRequestDTO;
import com.homecleaning.Entity.ServiceGroup;
import com.homecleaning.Repository.ServiceGroupRepository;
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
	private Cloudinary cloudinary;

	@Override
	public ServiceGroup createServiceGroup(ServiceGroupRequestDTO dto) {
		ServiceGroup serviceGroup = new ServiceGroup();
		serviceGroup.setTitle(dto.getTitle());
		serviceGroup.setDisplayOrder(dto.getDisplayOrder());

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

}
