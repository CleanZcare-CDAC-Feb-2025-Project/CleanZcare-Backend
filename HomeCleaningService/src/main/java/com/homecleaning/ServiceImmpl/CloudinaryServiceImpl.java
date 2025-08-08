package com.homecleaning.ServiceImmpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.homecleaning.Service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{
	@Autowired
	private Cloudinary cloudinary;

	@Override
	public Map upload(MultipartFile file) {
		try {
			return this.cloudinary.uploader().upload(file.getBytes(), Map.of());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error while uploading file");
		}
	}

}
