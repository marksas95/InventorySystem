package com.trainee.inv.service.warehouse;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.product.ProductService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseServiceTest {

    @Autowired
    WarehouseService warehouseService;
    @Autowired
    GoodQuantityProductService goodQuantityProductService;
    @Autowired
    ProductService productService;

    @Test
    public void createTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setActive(true);
        warehouse.setAddress("Pavia");
        warehouse.setDescription("Pavia warehouse");
        warehouse.setName("Pavia Warehouse");
        Warehouse createdWarehouse = warehouseService.create(warehouse);

        Assert.notNull(createdWarehouse.getId());
        Assert.isTrue(warehouse.getName()==createdWarehouse.getName());
    }

    @Test
    @Ignore
    public void createTestNameExists() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("LIon");
        warehouse.setDescription("Old");
        warehouse.setAddress("Hibao-an");
        List<GoodQuantityProduct> list = new ArrayList<GoodQuantityProduct>();
        GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(1);
        list.add(goodQuantityProduct);
        warehouse.setGoodQuantityProducts(list);

        try {
            warehouseService.create(warehouse);
        } catch (IllegalArgumentException e) {
            Assert.isTrue(e.getMessage().equals("Warehouse Name Already Exist."));
        }
    }

    @Test
    @Ignore
    public void updateTest() {
        Warehouse warehouse = warehouseService.findById(10);
//		warehouse.setName("SteelTecssh");
//		warehouse.setAddress("Cebu Citsyss");
//		warehouse.setDescription("Stseel sSets");
        List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
        GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(12);
        GoodQuantityProduct goodQuantityProduct2 = goodQuantityProductService.findById(11);
        System.out.println(goodQuantityProduct);
        System.out.println(goodQuantityProduct2);
        goodQuantityProducts.add(goodQuantityProduct);

        warehouse.setGoodQuantityProducts(goodQuantityProducts);
        warehouseService.update(warehouse);
    }

    @Test
    @Ignore
    public void findByNameTest() {
        String name = "Lazada";
        Warehouse warehouse = warehouseService.findByName(name);
        Assert.isTrue(warehouse.getName().equals(name));

    }

    @Test
    public void findByIsActiveTest() {
        boolean isActive = true;
        List<Warehouse> warehouses = warehouseService.findByIsActive(isActive);
        warehouses.forEach(o -> Assert.isTrue(o.isActive()==isActive));
    }

    @Test
    @Ignore
    public void findByIdTest() {
        int id = 1;
        Warehouse warehouse = warehouseService.findById(id);
        Assert.isTrue(warehouse.getId()==id);
        System.out.println(warehouse);
    }

    @Test
    public void findAllTest() {
        List<Warehouse> warehouses = warehouseService.findAll();
        Assert.notNull(warehouses);
    }

    @Test
    @Ignore
    public void deleteTest() {

        int id = 17;
        warehouseService.delete(id);
        Assert.isNull(warehouseService.findById(id));
    }

    @Test
    public void searchByNameTest() {
        boolean isActive = true;
        String name = "Lazada";
        List<Warehouse> warehouses = warehouseService.searchByName(name, isActive);
        warehouses.forEach(o -> Assert.isTrue(o.getName().contains(name)));
    }

    @Test
    public void searchByAddress() {
        boolean isActive = true;
        String address = "Iloilo city";
        List<Warehouse> warehouses = warehouseService.searchByName(address, isActive);
        warehouses.forEach(o -> Assert.isTrue(o.getAddress().contains(address)));
    }

    @Test
    public void searchByDescription() {
        boolean isActive = true;
        String description = "blob";
        List<Warehouse> warehouses = warehouseService.searchByName(description, isActive);
        warehouses.forEach(o -> Assert.isTrue(o.getDescription().contains(description)));
    }
}