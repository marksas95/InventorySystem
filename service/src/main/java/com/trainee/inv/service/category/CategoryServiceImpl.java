package com.trainee.inv.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	public CategoryRepository categoryRepository;
	
	@Override
	public Category create(String name) {
		Category category = new Category();
		category.setName(name);
	return categoryRepository.save(category);

	}

	@Override
	public Category update(Category category, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategory(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

}
