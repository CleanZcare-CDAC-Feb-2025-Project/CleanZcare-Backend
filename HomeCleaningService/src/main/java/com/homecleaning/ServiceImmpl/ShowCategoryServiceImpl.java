package com.homecleaning.ServiceImmpl;

import com.homecleaning.Entity.ShowCategory;
import com.homecleaning.Repository.ShowCategoryRepository;
import com.homecleaning.Service.ShowCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowCategoryServiceImpl implements ShowCategoryService {

    @Autowired
    private ShowCategoryRepository repository;

    @Override
    public List<ShowCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public ShowCategory getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ShowCategory create(ShowCategory showCategory) {
        return repository.save(showCategory);
    }

    @Override
    public ShowCategory update(ShowCategory showCategory) {
        return repository.save(showCategory);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

	@Override
	public void save(ShowCategory mainShowCategory) {
		repository.save(mainShowCategory);
		
	}
}
