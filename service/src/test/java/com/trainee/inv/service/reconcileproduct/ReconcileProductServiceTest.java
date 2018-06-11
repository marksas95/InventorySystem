package com.trainee.inv.service.reconcileproduct;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.warehouse.WarehouseService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReconcileProductServiceTest {

    @Autowired
    private ReconcileProductService reconcileProductService;
    @Autowired
    private GoodQuantityProductService goodQuantityProductService;
    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void create() {

        int idGoodQuantityProduct = 1;
        int idWarehouse = 20;
        int physicalCount = 100;
        int systemCount = 101;

        GoodQuantityProduct goodQuantityProduct  = goodQuantityProductService.findById(idGoodQuantityProduct);
        Warehouse warehouse = warehouseService.findById(idWarehouse);

        ReconcileProduct reconcileProduct = new ReconcileProduct();
        reconcileProduct.setGoodQuantityProduct(goodQuantityProduct);
        reconcileProduct.setWarehouse(warehouse);
        reconcileProduct.setPhysicalCount(physicalCount);
        reconcileProduct.setSystemCount(systemCount);
        reconcileProductService.create(reconcileProduct);
        Assert.isTrue(reconcileProduct.getGoodQuantityProduct().equals(goodQuantityProduct)
                        & reconcileProduct.getWarehouse().equals(warehouse)
                        & reconcileProduct.getPhysicalCount() == physicalCount
                        & reconcileProduct.getSystemCount() == systemCount);
    }

    @Test
    @Ignore
    public void update() {
        int id = 24;
        int physicalCount = 20;
        int systemCount = 50;

        ReconcileProduct reconcileProduct = reconcileProductService.findById(id);
        reconcileProduct.setPhysicalCount(physicalCount);
        reconcileProduct.setSystemCount(systemCount);
        ReconcileProduct updatedReconcileProduct = reconcileProductService.update(reconcileProduct);
        Assert.isTrue(updatedReconcileProduct.getPhysicalCount() == physicalCount
                        & updatedReconcileProduct.getSystemCount() == systemCount);
    }

    @Test
    @Ignore
    public void deleteById() {
        int id = 24;
        reconcileProductService.deleteById(id);
        Assert.isNull(reconcileProductService.findById(id));
    }

    @Test
    @Ignore
    public void findById() {
        int id = 24;
        ReconcileProduct reconcileProduct = reconcileProductService.findById(id);
        Assert.isTrue(reconcileProduct.getId() == id);
    }

    @Test
    @Ignore
    public void findAll() {
        List<ReconcileProduct> reconcileProducts = reconcileProductService.findAll();
        Assert.notNull(reconcileProducts);
    }

    @Test
    @Ignore
    public void findByGoodQuantityProductId() {
        int id = 25;
        List<ReconcileProduct> reconcileProducts = reconcileProductService.findByGoodQuantityProductId(id);
        reconcileProducts.forEach( o -> Assert.isTrue(o.getGoodQuantityProduct().getId() == id));
    }

    @Test
    @Ignore
    public void findByWarehouseId() {
        int id = 10;
        List<ReconcileProduct> reconcileProducts = reconcileProductService.findByWarehouseId(id);
        reconcileProducts.forEach(o -> Assert.isTrue(o.getWarehouse().getId() == id));
    }
}