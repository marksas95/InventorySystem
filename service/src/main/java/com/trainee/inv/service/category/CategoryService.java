package com.trainee.inv.service.category;

import java.util.List;

import com.trainee.inv.repository.category.Category;

public interface CategoryService {

	Category create(String name);

	Category update(int id, String name);

	Category findByName(String name);
	
	Category findById(int id);

	List<Category> findAll();

	void delete(int id);
	
	

}
