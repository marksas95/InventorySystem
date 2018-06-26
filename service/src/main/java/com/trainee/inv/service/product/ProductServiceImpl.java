package com.trainee.inv.service.product;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.repository.warehouse.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    GoodQuantityProductRepository goodQuantityProductRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public Product create(Product product) {
        checkIfItemCodeAndSerialNumberExists(product);
        return productRepository.save(product);
    }

    private void checkIfItemCodeAndSerialNumberExists(Product product) {
        boolean existsByItemCode = productRepository.existsByItemCode(product.getItemCode());
        boolean existsBySerialNumber = productRepository.existsBySerialNumber(product.getSerialNumber());
        if (existsByItemCode) {
            throw new IllegalArgumentException("Invalid operation, ItemCode already exists.");
        } else if (existsBySerialNumber) {
            throw new IllegalArgumentException("Invalid operation, Serial Number already exists.");
        }
    }

    @Override
    //To be Edit
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByIsActive(boolean isActive) {
        return productRepository.findByIsActive(isActive);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchByItemCode(String itemCode) {
        return productRepository.findAll().stream().filter(o -> o.getItemCode().toLowerCase().contains(itemCode)).collect(Collectors.toList());
    }

    @Override
    public List<Product> searchByUnitOfMeasurement(String unitOfMeasurement) {
        return productRepository.findAll().stream().filter(o -> o.getUnitOfMeasurement().toLowerCase().contains(unitOfMeasurement)).collect(Collectors.toList());
    }


    @Override
    public List<Product> searchByDescription(String description) {
        return productRepository.findAll().stream().filter(o -> o.getDescription().toLowerCase().contains(description)).collect(Collectors.toList());
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }


    @Override
    public List<Product> findByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> findBySupplierId(int id) {
        return productRepository.findBySupplierId(id);

    }

    @Override
    public void delete(int id) {
       goodQuantityProductRepository.findAllByProductId(id).stream().forEach(o ->  goodQuantityProductRepository.deleteById(o.getId()));
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> sortByName() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {

            @Override
            public int compare(Product product1, Product product2) {
                return product1.getName().compareTo(product2.getName());
            }

        });
        return products;
    }

    @Override
    public List<Product> sortByItemCode() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {

            @Override
            public int compare(Product o, Product n) {
                return o.getItemCode().compareTo(n.getItemCode());
            }
        });
        return products;
    }

    @Override
    public List<Product> sortByDescription() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {

            @Override
            public int compare(Product o, Product n) {
                return o.getDescription().compareTo(n.getDescription());
            }
        });
        return products;
    }

    @Override
    public List<Product> sortByMinimumStock() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {

            @Override
            public int compare(Product o1, Product o2) {
                return o1.getMinimumStocks() - o2.getMinimumStocks();
            }

        });
        return products;
    }

}
