package com.homecleaning.Service;

import com.homecleaning.Entity.Page;

import java.util.List;

public interface PageService {
	Page createPage(Page page);

	Page updatePage(Long id, Page page);

	Page getPageById(Long id);

	List<Page> getAllPages();

	void deletePage(Long id);

	boolean existPage(String page);
}
