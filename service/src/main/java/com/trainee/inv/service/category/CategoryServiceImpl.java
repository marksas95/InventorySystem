package com.trainee.inv.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category create(String name) {
		checkIfNameExists(name);
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}

	private void checkIfNameExists(String name) {
		if (categoryRepository.existsByName(name)) {
			throw new IllegalArgumentException("Category Name Already Exists.");
		}
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	//to be edit
	public Category update(int id, String name) {
		if(categoryRepository.existsByName(name)) {
			throw new IllegalArgumentException("Category Name Already Exists.");
		}
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		Category category = optionalCategory.get();
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id).get();
	}
}
