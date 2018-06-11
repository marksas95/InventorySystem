package com.trainee.inv.service.product;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.service.category.CategoryService;
import com.trainee.inv.service.supplier.SupplierService;

import javax.persistence.Temporal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;

    @Test
//	@Ignore
    public void createProductTest() {
        Category category = categoryService.findByName("Electronics");
        Product product = new Product();

        String name = "Lapto222222p";
        String description = "i7 Gaming Laptop";
        String itemCode = "00011111";
        String remarks = "brand new";
        String serialNumber = "111111";
        boolean isActive = true;
        String unitOfMeasurement = "PCS";
        boolean isVatable = false;

        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setItemCode(itemCode);
        product.setMinimumStocks(15);
        product.setRemarks(remarks);
        product.setSerialNumber(serialNumber);
        product.setActive(isActive);
        product.setUnitOfMeasurement(unitOfMeasurement);
        product.setVatable(isVatable);

        Product createdProduct = productService.create(product);
        Assert.isTrue(createdProduct.getId() != 0);
        System.out.println(createdProduct);
    }

    @Test
    @Ignore
    public void createProductTestWithExsistingItemCode() {
        Category category = categoryService.findByName("Electronics");
        Product product = new Product();

        product.setName("Laptop");
        product.setCategory(category);
        product.setDescription("i7 Gaming Laptop");
        product.setItemCode("000");
        product.setMinimumStocks(15);
        product.setRemarks("brand new");
        product.setSerialNumber("111");
        product.setActive(true);
        product.setUnitOfMeasurement("PCS");
        product.setVatable(false);
        try {
            Product createdProduct = productService.create(product);
        } catch (IllegalArgumentException e) {
            Assert.isTrue(e.getMessage().equals("Invalid field, ItemCode already exists."));
        }
    }

    @Test
    @Ignore
    public void createProductTestWithExsistingSerialNumber() {
        Category category = categoryService.findByName("Electronics");
        Product product = new Product();

        product.setName("Laptop");
        product.setCategory(category);
        product.setDescription("i7 Gaming Laptop");
        product.setItemCode("001");
        product.setMinimumStocks(15);
        product.setRemarks("brand new");
        product.setSerialNumber("111");
        product.setActive(true);
        product.setUnitOfMeasurement("PCS");
        product.setVatable(false);
        try {
            Product createdProduct = productService.create(product);
        } catch (IllegalArgumentException e) {
            Assert.isTrue(e.getMessage().equals("Invalid field, SerialNumer already exists."));
        }
    }

    @Test
    @Ignore
    public void sortByMinimumStock() {
        List<Product> products = productService.sortByMinimumStock();
        System.out.println(products);
    }

    @Test
    @Ignore
    public void sortByDescription() {
        List<Product> products = productService.sortByDescription();
        System.out.println(products);
    }

    @Test
    @Ignore
    public void sortByItemCode() {
        List<Product> products = productService.sortByItemCode();
        System.out.println(products);
    }

    @Test
    @Ignore
    public void sortByName() {
        List<Product> products = productService.sortByName();
        System.out.println(products);
    }

    @Test
    @Ignore
    public void updateProductTest() {
        int id = 6;

        Product product = productService.findById(id);
        String name = "www";
        String description = "Whole System";
        String itemCode = "pppp";
        int minimumStocks = 30;
        String remarks = "wala lang";
        String serialNumber = "0000000000000s";
        String unitOfMeasurement = "PCSs";
        boolean isVatable = false;

        product.setName(name);
        product.setDescription(description);
        product.setItemCode(itemCode);
        product.setMinimumStocks(minimumStocks);
        product.setRemarks(remarks);
        product.setSerialNumber(serialNumber);
        product.setUnitOfMeasurement(unitOfMeasurement);
        product.setVatable(isVatable);
        Product updatedProduct = productService.update(product);

        Assert.isTrue(product.equals(updatedProduct));
    }

    @Test
    // @Ignore
    public void searchByItemCodeTest() {
        String itemCode = "001";
        boolean isActive = true;
        List<Product> list = productService.searchByItemCode(itemCode, isActive);
        Assert.notNull(list);
        list.forEach(o -> Assert.isTrue(o.getItemCode().contains(itemCode)));
        System.out.println(list);
    }

    @Test
    @Ignore
    public void searchByItemCodeFalseTest() {
        String itemCode = "wesfr";
        boolean isActive = true;
        List<Product> list = productService.searchByItemCode(itemCode, isActive);
        Assert.isNull(list);
        System.out.println(list);
    }

    @Test
    @Ignore
    public void searchByUnitOfMeasurementTest() {
        String unitOfMeasurement = "PCS";
        boolean isActive = true;
        List<Product> list = productService.searchByUnitOfMeasurement(unitOfMeasurement, isActive);
        Assert.isNull(list);
        list.forEach(o -> Assert.isTrue(o.getUnitOfMeasurement().contains(unitOfMeasurement)));
        System.out.println(list);
    }

    @Test
    @Ignore
    public void searchByDescriptionTest() {
        String description = "PCS";
        boolean isActive = true;
        List<Product> list = productService.searchByDescription(description, isActive);
        Assert.isNull(list);
        list.forEach(o -> Assert.isTrue(o.getDescription().contains(description)));
        System.out.println(list);
    }

    @Test
    @Ignore
    public void findAllProductsTest() {
        List<Product> findProducts = productService.findAll();
        Assert.notNull(findProducts);
    }

    @Test
    @Ignore
    public void findByName() {
        String name = "Howie";
        Product findByNameProduct = productService.findByName(name);
        Assert.notNull(findByNameProduct);
        Assert.isTrue(findByNameProduct.getName().equals(name));
    }

    @Test
    @Ignore
    public void findById() {
        int id = 4;
        Product findByNameProduct = productService.findById(id);
        Assert.notNull(findByNameProduct);
        Assert.isTrue(findByNameProduct.getId() == id);
    }

    @Test
    @Ignore
    public void findByIsActiveTest() {
        boolean isActive = true;
        List<Product> list = productService.findByIsActive(isActive);
        list.forEach(o ->  Assert.isTrue(o.isActive() == isActive ));

    }

    @Test
    @Ignore
    public void findByCategoryIdTest() {
        int id = 13;
        List<Product> list = productService.findByCategoryId(id);
        list.forEach(o -> Assert.isTrue(o.getCategory().getId() == id));
    }

    @Test
    @Ignore
    public void findBySupplierIdTest() {
        int id = 13;
        List<Product> list = productService.findBySupplierId(id);
        list.forEach(o -> Assert.isTrue(o.getSupplier().getId() ==  id));
    }

    @Test
    @Ignore
    public void findAll(){
        List<Product> list = productService.findAll();
        Assert.notNull(list);
    }
    @Test
    @Ignore
    public void deleteTest() {
        int id = 2;
        productService.delete(id);
        Assert.isNull(productService.findById(id));
    }

}
