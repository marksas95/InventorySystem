package com.trainee.inv.repository.warehouse;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;

@Entity
public class Warehouse {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private String name;
	private String address;
	private String description;
	private boolean isActive;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<GoodQuantityProduct> goodQuantityProducts;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<GoodQuantityProduct> getGoodQuantityProducts() {
		return goodQuantityProducts;
	}

	public void setGoodQuantityProducts(List<GoodQuantityProduct> goodQuantityProducts) {
		this.goodQuantityProducts = goodQuantityProducts;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Warehouse other = (Warehouse) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", address=" + address + ", isActive=" + isActive
				+ ", goodQuantityProducts=" + goodQuantityProducts + "]";
	}

	

}
