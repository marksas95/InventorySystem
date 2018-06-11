package com.trainee.inv.service.damagequantityproduct;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.service.product.ProductService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class DamageQuantityProductServiceTest {

    @Autowired
    private DamageQuantityProductService damageQuantityProductService;
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void create() {
        int id = 13;
        Product product = productService.findById(id);
        int quantity = 100;
        DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.create(product, quantity);
        Assert.assertTrue(damageQuantityProduct.getProduct().equals(product)& damageQuantityProduct.getQuantity()==quantity);
    }

    @Test
    @Ignore
    public void updateQuantity() {
        int id = 13;
        int quantity = 500;
        DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.updateQuantity(id, quantity);
        Assert.assertTrue(damageQuantityProduct.getQuantity() == quantity);
    }

    @Test
    @Ignore
    public void findByProductId() {
        int id = 13;
        List<DamageQuantityProduct> damageQuantityProducts = damageQuantityProductService.findByProductId(id);
        damageQuantityProducts.forEach(o -> Assert.assertTrue(o.getProduct().getId() == id));
    }

    @Test
    @Ignore
    public void findById() {
        int id = 13;
        DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(id);
        Assert.assertTrue(damageQuantityProduct.getId()== id);
    }

    @Test
    @Ignore
    public void findAll() {
        List<DamageQuantityProduct> damageQuantityProducts = damageQuantityProductService.findAll();
        Assert.assertNotNull(damageQuantityProducts);
    }

    @Test
    @Ignore
    public void deleteById() {
        int id = 13;
        damageQuantityProductService.deleteById(id);
        Assert.assertNull(damageQuantityProductService.findById(id));
    }
}