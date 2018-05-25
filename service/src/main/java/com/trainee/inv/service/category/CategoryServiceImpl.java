package com.trainee.inv.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category create(String name) {
		boolean nameExist = checkIfCategoryNameExist(name);
		if (nameExist) {
			throw new IllegalArgumentException("Category Name Already Exists.");
		}
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);

	}

	@Override
	public Category findByName(String name) {
		Category category = categoryRepository.findByName(name);
		return category != null ? category : null;
	}

	@Override
	public Category update(Category category, String name) {
		boolean nameExist = checkIfCategoryNameExist(name);
		if (nameExist) {
			throw new IllegalArgumentException("Name already added.");
		}
		category.setName(name);
		return categoryRepository.save(category);
	}
	
	@Override
	public void delete(String name) {
		boolean nameExist = checkIfCategoryNameExist(name);
		if (!nameExist) {
			throw new IllegalArgumentException("No name in that category.");
		}
		Category category = categoryRepository.findByName(name);
		categoryRepository.delete(category);
	}


	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	private boolean checkIfCategoryNameExist(String name) {
		Category category = categoryRepository.findByName(name);
		return category != null;
	}
}
