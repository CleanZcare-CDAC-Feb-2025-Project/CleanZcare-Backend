package com.homecleaning.ServiceImmpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.homecleaning.DTO.ImageUploadRequest;
import com.homecleaning.DTO.RowSectionDTO;
import com.homecleaning.DTO.RowSectionDataRequest;
import com.homecleaning.DTO.SlideDataRequest;
import com.homecleaning.Entity.Category;
import com.homecleaning.Entity.RowSection;
import com.homecleaning.Entity.RowSectionData;
import com.homecleaning.Entity.SlideData;
import com.homecleaning.Repository.CategoryRepository;
import com.homecleaning.Repository.RowSectionRepository;
import com.homecleaning.Service.RowSectionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
@Service
@RequiredArgsConstructor
public class RowSectionServiceImpl implements RowSectionService {

    private final RowSectionRepository rowSectionRepository;
    private final CategoryRepository categoryRepository;
    private final Cloudinary cloudinary;

    private String uploadToCloudinary(MultipartFile file) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        return uploadResult.get("secure_url").toString();
    }

    @Override
    public RowSection createRowSection(RowSectionDTO request) throws Exception {
        RowSection rowSection = new RowSection();
        rowSection.setIndex(request.getIndex());
        rowSection.setType(request.getType());

        List<RowSectionData> rowDataList = new ArrayList<>();

        if (request.getData() != null) {
            RowSectionDataRequest rowDataReq = request.getData();

            RowSectionData rowSectionData = new RowSectionData();
            rowSectionData.setTitle(rowDataReq.getTitle() != null ? rowDataReq.getTitle() : "");
            rowSectionData.setRowSection(rowSection);

            List<SlideData> slides = new ArrayList<>();
            if (rowDataReq.getData() != null) {
                for (SlideDataRequest slideReq : rowDataReq.getData()) {
                    SlideData slide = new SlideData();

                    String slideTitle = slideReq.getTitle();
                    if (slideTitle == null || slideTitle.trim().isEmpty()) {
                        slide.setTitle("null");
                    } else {
                        slide.setTitle(slideTitle);
                    }

                    slide.setRating(slideReq.getRating() != null ? slideReq.getRating() : 0.0);
                    slide.setReviews(slideReq.getReviews() != null ? String.valueOf(slideReq.getReviews()) : "0");
                    slide.setPrice(slideReq.getPrice());

                    String currency = slideReq.getCurrency();
                    slide.setCurrency(currency != null ? currency : "INR");

                    if (slideReq.getCategory() != null) {
                        Category category = categoryRepository.findById(slideReq.getCategory())
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        slide.setCategory(category);
                    } else {
                        slide.setCategory(null);
                    }

                    if (slideReq.getImage() != null && !slideReq.getImage().isEmpty()) {
                        String imageUrl = uploadToCloudinary(slideReq.getImage());
                        slide.setImage(imageUrl);
                    } else {
                        slide.setImage(null);
                    }

                    slide.setRowSectionData(rowSectionData);
                    slides.add(slide);
                }
            }
            rowSectionData.setData(slides);
            rowDataList.add(rowSectionData);
        }

        if (request.getImages() != null && !request.getImages().isEmpty()) {
            RowSectionData rowSectionData = new RowSectionData();
            rowSectionData.setTitle("Images Only Section");
            rowSectionData.setRowSection(rowSection);

            List<SlideData> slides = new ArrayList<>();
            for (ImageUploadRequest imgReq : request.getImages()) {
                SlideData slide = new SlideData();

                if (imgReq.getCategory() != null && !imgReq.getCategory().isEmpty()) {
                    try {
                        Category category = categoryRepository.findById(Long.parseLong(imgReq.getCategory()))
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        slide.setCategory(category);
                    } catch (NumberFormatException ex) {
                        slide.setCategory(null);
                    }
                } else {
                    slide.setCategory(null);
                }

                if (imgReq.getFile() != null && !imgReq.getFile().isEmpty()) {
                    String imageUrl = uploadToCloudinary(imgReq.getFile());
                    slide.setImage(imageUrl);
                } else {
                    slide.setImage(null);
                }

                slide.setRowSectionData(rowSectionData);
                slides.add(slide);
            }
            rowSectionData.setData(slides);
            rowDataList.add(rowSectionData);
        }

        rowSection.setData(rowDataList);
        return rowSectionRepository.save(rowSection);
    }

    @Override
    public List<RowSection> getAllRowSections() {
        return rowSectionRepository.findAll();
    }

    @Override
    public RowSection getRowSectionById(Long id) {
        return rowSectionRepository.findById(id).orElse(null);
    }

    @Override
    public RowSection updateRowSection(Long id, RowSectionDTO request) throws Exception {
        Optional<RowSection> optionalRowSection = rowSectionRepository.findById(id);
        if (optionalRowSection.isEmpty()) {
            return null;
        }

        RowSection rowSection = optionalRowSection.get();
        rowSection.setIndex(request.getIndex());
        rowSection.setType(request.getType());

        List<RowSectionData> updatedRowDataList = new ArrayList<>();

        if (request.getData() != null) {
            RowSectionDataRequest rowDataReq = request.getData();

            RowSectionData rowSectionData = new RowSectionData();
            rowSectionData.setTitle(rowDataReq.getTitle() != null ? rowDataReq.getTitle() : "");
            rowSectionData.setRowSection(rowSection);

            List<SlideData> slides = new ArrayList<>();
            if (rowDataReq.getData() != null) {
                for (SlideDataRequest slideReq : rowDataReq.getData()) {
                    SlideData slide = new SlideData();

                    String slideTitle = slideReq.getTitle();
                    slide.setTitle((slideTitle == null || slideTitle.trim().isEmpty()) ? "null" : slideTitle);

                    slide.setRating(slideReq.getRating() != null ? slideReq.getRating() : 0.0);
                    slide.setReviews(slideReq.getReviews() != null ? String.valueOf(slideReq.getReviews()) : "0");
                    slide.setPrice(slideReq.getPrice());
                    slide.setCurrency(slideReq.getCurrency() != null ? slideReq.getCurrency() : "INR");

                    if (slideReq.getCategory() != null) {
                        Category category = categoryRepository.findById(slideReq.getCategory())
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        slide.setCategory(category);
                    } else {
                        slide.setCategory(null);
                    }

                    if (slideReq.getImage() != null && !slideReq.getImage().isEmpty()) {
                        String imageUrl = uploadToCloudinary(slideReq.getImage());
                        slide.setImage(imageUrl);
                    } else {
                        slide.setImage(null);
                    }

                    slide.setRowSectionData(rowSectionData);
                    slides.add(slide);
                }
            }
            rowSectionData.setData(slides);
            updatedRowDataList.add(rowSectionData);
        }

        if (request.getImages() != null && !request.getImages().isEmpty()) {
            RowSectionData rowSectionData = new RowSectionData();
            rowSectionData.setTitle("Images Only Section");
            rowSectionData.setRowSection(rowSection);

            List<SlideData> slides = new ArrayList<>();
            for (ImageUploadRequest imgReq : request.getImages()) {
                SlideData slide = new SlideData();

                if (imgReq.getCategory() != null && !imgReq.getCategory().isEmpty()) {
                    try {
                        Category category = categoryRepository.findById(Long.parseLong(imgReq.getCategory()))
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        slide.setCategory(category);
                    } catch (NumberFormatException ex) {
                        slide.setCategory(null);
                    }
                } else {
                    slide.setCategory(null);
                }

                if (imgReq.getFile() != null && !imgReq.getFile().isEmpty()) {
                    String imageUrl = uploadToCloudinary(imgReq.getFile());
                    slide.setImage(imageUrl);
                } else {
                    slide.setImage(null);
                }

                slide.setRowSectionData(rowSectionData);
                slides.add(slide);
            }
            rowSectionData.setData(slides);
            updatedRowDataList.add(rowSectionData);
        }

        rowSection.setData(updatedRowDataList);

        return rowSectionRepository.save(rowSection);
    }

    @Override
    public boolean deleteRowSection(Long id) {
        if (!rowSectionRepository.existsById(id)) {
            return false;
        }
        rowSectionRepository.deleteById(id);
        return true;
    }
}
