package com.trainee.inv.resources.supplier;

public class UpdateSupplierForm {
	private String name;
	private int id;
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
		UpdateSupplierForm other = (UpdateSupplierForm) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateSupplierForm [name=" + name + ", id=" + id + "]";
	}
	
	
	
}
