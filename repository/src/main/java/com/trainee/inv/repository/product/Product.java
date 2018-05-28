package com.trainee.inv.repository.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.supplier.Supplier;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String itemCode;
	private String description;
	private String unitOfMeasurement;
	private String serialNumber;
	private boolean isActive;
	private String remarks;
	private int minimumStocks;
	private boolean isVatable;
	@OneToOne
	private Category category;

	@OneToOne
	private Supplier supplier;

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
	public String toString() {
		return "Product [id=" + id + ", itemCode=" + itemCode + ", description=" + description + ", unitOfMeasurement="
				+ unitOfMeasurement + ", serialNumber=" + serialNumber + ", isActive=" + isActive + ", remarks="
				+ remarks + ", minimumStocks=" + minimumStocks + ", isVatable=" + isVatable + ", category=" + category
				+ ", supplier=" + supplier + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isVatable ? 1231 : 1237);
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + minimumStocks;
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
		result = prime * result + ((unitOfMeasurement == null) ? 0 : unitOfMeasurement.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isActive != other.isActive)
			return false;
		if (isVatable != other.isVatable)
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (minimumStocks != other.minimumStocks)
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		if (unitOfMeasurement == null) {
			if (other.unitOfMeasurement != null)
				return false;
		} else if (!unitOfMeasurement.equals(other.unitOfMeasurement))
			return false;
		return true;
	}
}
