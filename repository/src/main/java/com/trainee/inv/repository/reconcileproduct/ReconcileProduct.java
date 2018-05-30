package com.trainee.inv.repository.reconcileproduct;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;

@Entity
public class ReconcileProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@CreatedDate
	private Date createdDate;

	@ManyToOne
	private GoodQuantityProduct goodQuantityProduct;

	private int physicalCount;
	private int systemCount;

	@ManyToOne
	private Warehouse warehouse;

	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public GoodQuantityProduct getGoodQuantityProduct() {
		return goodQuantityProduct;
	}

	public void setGoodQuantityProduct(GoodQuantityProduct goodQuantityProduct) {
		this.goodQuantityProduct = goodQuantityProduct;
	}

	public int getPhysicalCount() {
		return physicalCount;
	}

	public void setPhysicalCount(int physicalCount) {
		this.physicalCount = physicalCount;
	}

	public int getSystemCount() {
		return systemCount;
	}

	public void setSystemCount(int systemCount) {
		this.systemCount = systemCount;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
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
		ReconcileProduct other = (ReconcileProduct) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReconcileProduct [id=" + id + ", creationDate=" + createdDate + ", goodQuantityProduct="
				+ goodQuantityProduct + ", physicalCount=" + physicalCount + ", systemCount=" + systemCount
				+ ", warehouse=" + warehouse + ", remarks=" + remarks + "]";
	}

}
