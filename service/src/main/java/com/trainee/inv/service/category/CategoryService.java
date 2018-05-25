package com.trainee.inv.service.category;

import com.trainee.inv.repository.category.Category;

public interface CategoryService {

	Category create(String name);
	Category update(Category category, String name);
	Category getCategory(String name);
	boolean delete(Category category);
	
}
