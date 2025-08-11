package com.homecleaning.ServiceImmpl;

import com.homecleaning.Entity.Page;
import com.homecleaning.Repository.PageRepository;
import com.homecleaning.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private PageRepository pageRepository;

	@Override
	public Page createPage(Page page) {
		return pageRepository.save(page);
	}

	@Override
	public Page updatePage(Long id, Page page) {
		Page existing = pageRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Page not found with id: " + id));
		existing.setTitle(page.getTitle());
		existing.setSlug(page.getSlug());
		existing.setShowCategory(page.isShowCategory());
		existing.setImageUrl(page.getImageUrl());
		existing.setDescription(page.getDescription());
		return pageRepository.save(existing);
	}

	@Override
	public Page getPageById(Long id) {
		return pageRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found with id: " + id));
	}

	@Override
	public List<Page> getAllPages() {
		return pageRepository.findAll();
	}

	@Override
	public void deletePage(Long id) {
		pageRepository.deleteById(id);
	}

	@Override
	public boolean existPage(String page) {
		return pageRepository.existsById(Long.parseLong(page));
	}

	@Override
	public Page getPageByName(String name) {
		return pageRepository.findByTitle(name);
	}
}
