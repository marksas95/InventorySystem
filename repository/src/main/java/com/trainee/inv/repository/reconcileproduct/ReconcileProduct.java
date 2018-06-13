package com.trainee.inv.repository.reconcileproduct;


import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ReconcileProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreatedDate
    private long createdDate;

    @ManyToOne
    private GoodQuantityProduct goodQuantityProduct;

    @ManyToOne
    private Warehouse warehouse;

    private String remarks;
    private int physicalCount;
    private int systemCount;

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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
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
