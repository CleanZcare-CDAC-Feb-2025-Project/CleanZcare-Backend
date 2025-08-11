package com.homecleaning.Service;

import com.homecleaning.Entity.ShowCategory;

import java.util.List;

public interface ShowCategoryService {
    List<ShowCategory> getAll();
    ShowCategory getById(Long id);
    ShowCategory create(ShowCategory showCategory);
    ShowCategory update(ShowCategory showCategory);
    void delete(Long id);
	void save(ShowCategory mainShowCategory);
}
