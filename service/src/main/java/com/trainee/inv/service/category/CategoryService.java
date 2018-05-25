package com.trainee.inv.service.category;

import java.util.List;

import com.trainee.inv.repository.category.Category;

public interface CategoryService {

	Category create(String name);
	Category update(Category category, String name);
	Category findByName(String name);
	List<Category> findAll();
	void delete(String name);
	
}
