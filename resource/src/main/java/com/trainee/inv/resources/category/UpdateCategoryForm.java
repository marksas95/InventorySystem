package com.trainee.inv.resources.category;

import com.trainee.inv.repository.category.Category;

public class UpdateCategoryForm {

	private int id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateCategoryForm other = (UpdateCategoryForm) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateCategoryForm [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
