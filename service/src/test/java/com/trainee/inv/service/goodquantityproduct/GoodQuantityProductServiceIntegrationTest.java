package com.trainee.inv.service.goodquantityproduct;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;
import com.trainee.inv.repository.warehouse.WarehouseRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodQuantityProductServiceIntegrationTest {

    @Autowired
    private GoodQuantityProductService goodQuantityProductService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    @Ignore
    public void createTest() {
        Optional<Product> findById = productRepository.findById(2);
        Product product = findById.get();
        int quantity = 100;
        GoodQuantityProduct save = goodQuantityProductService.create(product, 0);
        Assert.notNull(save, "Return object is null!");
        System.out.println(save);
    }

    @Test
    public void updateQuantity() {
        int id = 13;
        int quantity = 500;
        GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.updateQuantity(id, quantity);
        org.junit.Assert.assertTrue(goodQuantityProduct.getQuantity() == quantity);
    }

    @Test
    public void findByProductId() {
        int id = 13;
        List<GoodQuantityProduct> goodQuantityProducts = goodQuantityProductService.findByProductId(id);
        goodQuantityProducts.forEach(o -> org.junit.Assert.assertTrue(o.getProduct().getId() == id));
    }

    @Test
    public void findById() {
        int id = 13;
        GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(id);
        org.junit.Assert.assertTrue(goodQuantityProduct.getId()== id);
    }

    @Test
    public void findAll() {
        List<GoodQuantityProduct> goodQuantityProducts = goodQuantityProductService.findAll();
        org.junit.Assert.assertNotNull(goodQuantityProducts);
    }

    @Test
    public void deleteById() {
        int id = 13;
        goodQuantityProductService.deleteById(id);
        org.junit.Assert.assertNull(goodQuantityProductService.findById(id));
    }
}