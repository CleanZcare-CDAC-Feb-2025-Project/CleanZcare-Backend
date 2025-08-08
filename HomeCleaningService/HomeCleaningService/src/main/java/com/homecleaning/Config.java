package com.homecleaning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;

@Configuration
public class Config {

	@Bean
	public Cloudinary getCloudinary() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dcjryiycw",
				"api_key", "931639957153951",
				"api_secret", "j779L_gHHV7M9T0wAXmqVphNybo",
				"secure", true));
		return cloudinary;
	}
}
