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
	CategoryRepository categoryRepository;

	@Override
	public Category create(String name) {
		boolean existsByName = categoryRepository.existsByName(name);
		if (existsByName) {
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
	public Category update(int id, String name) {
		boolean existsById = categoryRepository.existsById(id);
		if (!existsById) {
			throw new IllegalArgumentException("Name already added.");
		}
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		Category category = optionalCategory.get();
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	public void delete(int id) {
		boolean existsById = categoryRepository.existsById(id);
		if (!existsById) {
			throw new IllegalArgumentException("No name in that category.");
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		return optionalCategory.get();
	}
}
