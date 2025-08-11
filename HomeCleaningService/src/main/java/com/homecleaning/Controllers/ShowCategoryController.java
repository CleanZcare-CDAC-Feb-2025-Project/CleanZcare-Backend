// Optimized and corrected ShowCategoryController.java
package com.homecleaning.Controllers;

import com.homecleaning.DTO.ShowCategoryDTO;
import com.homecleaning.DTO.SubShowCategoryDTO;
import com.homecleaning.Entity.*;
import com.homecleaning.Repository.ServiceModalResourceRepository;
import com.homecleaning.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/show-categories")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ShowCategoryController {

	@Autowired
	private ShowCategoryService showCategoryService;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private PageService pageService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ServiceModalResourceRepository modalResourceRepository;

	@GetMapping
	public List<ShowCategory> getAllShowCategories() {
		return showCategoryService.getAll();
	}

	@GetMapping("/{id}")
	public ShowCategory getShowCategoryById(@PathVariable Long id) {
		return showCategoryService.getById(id);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void createShowCategory(@RequestPart("data") ShowCategoryDTO dto,
			@RequestPart(value = "showCategoryImages", required = false) MultipartFile mainImage,
			@RequestPart(value = "extraImages", required = false) List<MultipartFile> extraImages,
			@RequestPart(value = "extraModalBanner", required = false) MultipartFile extraModalBanner,
			MultipartHttpServletRequest request) {
        //checking existence of the page
		Page page = pageService.getPageById(Long.parseLong(dto.getPage()));
		//checking for existence of the category
		Category category = dto.getCategory() != null
				? categoryService.getCategoryById(Long.parseLong(dto.getCategory()))
				: null;
        //creating showCategory object
		ShowCategory main = new ShowCategory();
		//assigning properties
		page.getShowCategoryList().add(main);
		main.getPages().add(page);
		main.setCategory(category);
		main.setTitle(dto.getTitle());
        //uploading front page image and setting to showCategory object
		if (mainImage != null) {
			Map uploadResult = cloudinaryService.upload(mainImage);
			main.setIconUrl((String) uploadResult.get("secure_url"));
		}
        //checking for  sub categories and adding them
		List<ShowCategory> subCategories = new ArrayList<>();
		if (dto.getSubShowCategories() != null) {
			for (int i = 0; i < dto.getSubShowCategories().size(); i++) {
				SubShowCategoryDTO subDTO = dto.getSubShowCategories().get(i);
				ShowCategory sub = new ShowCategory();
				sub.setTitle(subDTO.getTitle());
				sub.setCategory(category);
				sub.setParentCategory(main);
//				(null);
				MultipartFile submainImage = request.getFile("subShowCategories[" + i + "].mainImage");
				MultipartFile modalBanner = request.getFile("subShowCategories[" + i + "].modalBanner");
				List<MultipartFile> modalImages = request.getFiles("subShowCategories[" + i + "].images");
				List<String> imageCats = subDTO.getImageCategories();
				if (submainImage != null) {
					Map uploadResult = cloudinaryService.upload(submainImage);
					sub.setIconUrl((String) uploadResult.get("secure_url"));
				}
				List<ServiceModalResource> resources = new ArrayList<>();
				if (modalBanner != null)
					resources.add(createResource(modalBanner, ImageType.BANNER, i, null));

				if (modalImages != null) {
					for (int j = 0; j < modalImages.size(); j++) {
						MultipartFile image = modalImages.get(j);
						Long catId = Long.parseLong(imageCats.get(j));
						Category imgCat = categoryService.getCategoryById(catId);
						resources.add(createResource(image, ImageType.MODALIMAGES, j, imgCat));
					}
				}

				ServiceModalTable modalTable = new ServiceModalTable();
				modalTable.setModalResource(resources);
				sub.setServiceModalTable(modalTable);
				subCategories.add(sub);
			}
		}
		main.setSubCategories(subCategories);
        
		// checking for external image and 
		List<ServiceModalResource> extraResources = new ArrayList<>();
		System.out.println(extraModalBanner);
		if (extraModalBanner != null) {
			System.out.println("called");
			extraResources.add(createResource(extraModalBanner, ImageType.BANNER, 0, null));
		}
			

		if (extraImages != null && dto.getExtraImageCategories() != null) {
			for (int i = 0; i < extraImages.size(); i++) {
				MultipartFile img = extraImages.get(i);
				Long catId = Long.parseLong(dto.getExtraImageCategories().get(i));
				Category cat = categoryService.getCategoryById(catId);
				extraResources.add(createResource(img, ImageType.MODALIMAGES, i, cat));
			}
		}

		ServiceModalTable extraTable = new ServiceModalTable();
		extraTable.setTitle(dto.getTitle());
		extraTable.setModalResource(extraResources);
		main.setServiceModalTable(extraTable);

		System.out.println(main);
		showCategoryService.save(main);
	}

	private ServiceModalResource createResource(MultipartFile file, ImageType type, int order, Category category) {
		Map upload = cloudinaryService.upload(file);
		ServiceModalResource resource = new ServiceModalResource();
		resource.setImageType(type);
		resource.setImageOrder(order);
		resource.setImageUrl((String) upload.get("secure_url"));
		resource.setPublicId((String) upload.get("public_id"));
		resource.setFormat((String) upload.get("format"));

		if (upload.get("width") != null)
			resource.setWidth((int) upload.get("width"));
		if (upload.get("height") != null)
			resource.setHeight((int) upload.get("height"));
		if (upload.get("bytes") != null)
			resource.setBytes(((Number) upload.get("bytes")).longValue());

		if (upload.get("created_at") != null) {
			String createdAtStr = upload.get("created_at").toString().replace("Z", "");
			resource.setUploadedAt(LocalDateTime.parse(createdAtStr));
		}

		resource.setCategory(category);
		return resource;
	}

	@PutMapping("/{id}")
	public ShowCategory updateShowCategory(@PathVariable Long id, @RequestBody ShowCategory updatedCategory) {
		updatedCategory.setId(id);
		return showCategoryService.update(updatedCategory);
	}

	@DeleteMapping("/{id}")
	public void deleteShowCategory(@PathVariable Long id) {
		showCategoryService.delete(id);
	}
}