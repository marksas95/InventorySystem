package com.trainee.inv.repository.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.supplier.Supplier;

@Entity
public class Product implements Comparable<Product> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String itemCode;
	private String description;
	private String unitOfMeasurement;
	private String serialNumber;
	private boolean isActive;
	private String remarks;
	private int minimumStocks;
	private boolean isVatable;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Supplier supplier;

	

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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMinimumStocks() {
		return minimumStocks;
	}

	public void setMinimumStocks(int minimumStocks) {
		this.minimumStocks = minimumStocks;
	}

	public boolean isVatable() {
		return isVatable;
	}

	public void setVatable(boolean isVatable) {
		this.isVatable = isVatable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", itemCode=" + itemCode + ", description=" + description + ", unitOfMeasurement="
				+ unitOfMeasurement + ", serialNumber=" + serialNumber + ", isActive=" + isActive + ", remarks="
				+ remarks + ", minimumStocks=" + minimumStocks + ", isVatable=" + isVatable + ", category=" + category
				+ ", supplier=" + supplier + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.getName());
	}

}
