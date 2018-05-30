package com.trainee.inv.resource.category;

import com.trainee.inv.repository.category.Category;

public class UpdateCategoryForm {

	private Category category;
	private String name;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
