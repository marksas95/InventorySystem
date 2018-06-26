package com.trainee.inv.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.damagequantityproduct.DamageQuantityProductService;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.product.ProductService;
import com.trainee.inv.service.reconcileproduct.ReconcileProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@Service
public class StockQuantityServiceImpl implements StockQuantityService {

    @Autowired
    WarehouseService warehouseService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodQuantityProductService goodQuantityProductService;
    @Autowired
    DamageQuantityProductService damageQuantityProductService;
    @Autowired
    ReconcileProductService reconcileProductService;


    @Override
    public void stockInGoodQuantityProduct(int warehouseId, int productId, int quantity) {
        Warehouse warehouse = checkIfWarehouseAndProductIstActive(warehouseId, productId);
        Optional<GoodQuantityProduct> goodQuantityProduct = getGoodQuantityProductOfProductInWarehouse(productId, warehouse);
        ifNotPresentCreateNewGoodQuantityProduct(productId, quantity, warehouse, goodQuantityProduct);
        ifPresentAddGoodQuantity(quantity, goodQuantityProduct);
    }

    private Optional<GoodQuantityProduct> getGoodQuantityProductOfProductInWarehouse(int productId, Warehouse warehouse) {
        return warehouse.getGoodQuantityProducts()
                .stream()
                .filter(o -> o.getProduct().getId() == productId)
                .findFirst();
    }

    private void ifPresentAddGoodQuantity(int quantity, Optional<GoodQuantityProduct> goodQuantityProduct) {
        goodQuantityProduct.ifPresent(o -> goodQuantityProductService.updateQuantity(o.getId(), o.getQuantity() + quantity));
    }

    private void ifNotPresentCreateNewGoodQuantityProduct(int productId, int quantity, Warehouse warehouse, Optional<GoodQuantityProduct> goodQuantityProduct) {
        if (!goodQuantityProduct.isPresent()) {
            warehouse.getGoodQuantityProducts().add(goodQuantityProductService
                    .create(productService.findById(productId), quantity));
            warehouseService.update(warehouse);
        }
    }

    @Override
    public void stockOutGoodQuantityProduct(int warehouseId, int productId, int quantity) {
        Warehouse warehouse = checkIfWarehouseAndProductIstActive(warehouseId, productId);
        Optional<GoodQuantityProduct> goodQuantityProduct = getGoodQuantityProductOfProductInWarehouse(productId, warehouse);
        ifPresentSubtractQuantity(quantity, goodQuantityProduct);
        ifNotPresentThrowIllegalArgumentException(goodQuantityProduct);
    }

    private void ifNotPresentThrowIllegalArgumentException(Optional<GoodQuantityProduct> goodQuantityProduct) {
        goodQuantityProduct.orElseThrow(() -> new IllegalArgumentException("Invalid product no stocks in designated warehouse"));
    }

    private void ifPresentSubtractQuantity(int quantity, Optional<GoodQuantityProduct> goodQuantityProduct) {
        goodQuantityProduct.ifPresent(o -> {
            checkIfQuantityIsValid(quantity, o.getQuantity());
            goodQuantityProductService.updateQuantity(o.getId(), o.getQuantity() - quantity);
        });
    }

    private void checkIfQuantityIsValid(int quantity, int initialQuantity) {
        if (initialQuantity < quantity) {
            throw new IllegalArgumentException("Stock out quantity is greater than the available number of items");
        }
    }

    private Warehouse checkIfWarehouseAndProductIstActive(int warehouseId, int productId) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        Product product = productService.findById(productId);
        if (!warehouse.isActive()) {
            throw new IllegalArgumentException("Invalid warehouse because it is inactive");
        }
        if (!product.isActive()) {
            throw new IllegalArgumentException("Invalid product because it is inactive");
        }
        return warehouse;
    }

    @Override
    public void transferStocks(int warehouseIdFrom, int warehouseIdTo, int productId, int quantity) {
        Warehouse warehouseFrom = checkIfWarehouseAndProductIstActive(warehouseIdFrom, productId);
        stockOutFromWarehouse(productId, quantity, warehouseFrom);
        Warehouse warehouseTo = checkIfWarehouseAndProductIstActive(warehouseIdTo, productId);
        stockInToWarehouse(productId, quantity, warehouseTo);

    }

    private void stockInToWarehouse(int productId, int quantity, Warehouse warehouseTo) {
        Optional<GoodQuantityProduct> goodQuantityProductTo = getGoodQuantityProductOfProductInWarehouse(productId, warehouseTo);
        ifNotPresentCreateNewGoodQuantityProduct(productId, quantity, warehouseTo, goodQuantityProductTo);
        ifPresentAddGoodQuantity(quantity, goodQuantityProductTo);
    }

    private void stockOutFromWarehouse(int productId, int quantity, Warehouse warehouseFrom) {
        Optional<GoodQuantityProduct> goodQuantityProductFrom = getGoodQuantityProductOfProductInWarehouse(productId, warehouseFrom);
        ifPresentSubtractQuantity(quantity, goodQuantityProductFrom);
        ifNotPresentThrowIllegalArgumentException(goodQuantityProductFrom);
    }


    //for change
    @Override
    public void reconcileProduct(int warehouseId, int productId, int physicalQuantity) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        warehouse.getGoodQuantityProducts().stream()
                .filter(o -> o.getProduct().getId() == productId)
                .findFirst().ifPresent(o -> {
            createReconcileProduct(physicalQuantity, warehouse, o);
            goodQuantityProductService.updateQuantity(o.getId(), physicalQuantity);
        });

    }

    private void createReconcileProduct(int physicalQuantity, Warehouse warehouse, GoodQuantityProduct o) {
        ReconcileProduct reconcileProduct = new ReconcileProduct();
        reconcileProduct.setGoodQuantityProduct(o);
        reconcileProduct.setWarehouse(warehouse);
        reconcileProduct.setPhysicalCount(physicalQuantity);
        reconcileProduct.setSystemCount(o.getQuantity());
        reconcileProductService.create(reconcileProduct);
    }

    @Override
    public void stockInDamageQuantityProduct(int warehouseIdFrom, int warehouseIdTo, int productId, int quantity) {
        Warehouse warehouseFrom = checkIfWarehouseAndProductIstActive(warehouseIdFrom, productId);
        stockOutFromWarehouse(productId, quantity, warehouseFrom);

        Warehouse warehouseTo = checkIfWarehouseAndProductIstActive(warehouseIdTo, productId);
        Optional<DamageQuantityProduct> damageQuantityProduct = getDamageQuantityProductInWarehouse(productId, warehouseTo);
        ifNotPresentCreateNewDamageQuantityProduct(productId, quantity, warehouseTo, damageQuantityProduct);
        ifPresentAddDamageQuantity(quantity, damageQuantityProduct);

    }

    private void ifNotPresentCreateNewDamageQuantityProduct(int productId, int quantity, Warehouse warehouseTo, Optional<DamageQuantityProduct> damageQuantityProduct) {
        if (!damageQuantityProduct.isPresent()) {
            warehouseTo.getDamageQuantityProducts().add(damageQuantityProductService
                    .create(productService.findById(productId), quantity));
            warehouseService.update(warehouseTo);
        }
    }

    private void ifPresentAddDamageQuantity(int quantity, Optional<DamageQuantityProduct> damageQuantityProductInWarehouseTo) {
        damageQuantityProductInWarehouseTo.ifPresent(o ->
                damageQuantityProductService.updateQuantity(o.getId(), o.getQuantity() + quantity));
    }

    private Optional<DamageQuantityProduct> getDamageQuantityProductInWarehouse(int productId, Warehouse warehouseTo) {
        return warehouseTo.getDamageQuantityProducts().stream().filter(o -> o.getProduct().getId() == productId).findFirst();
    }

    //for change
    @Override
    public void stockOutDamageQuantityProduct(int warehouseId, int productId, int quantity) {

        Warehouse warehouse = checkIfWarehouseAndProductIstActive(warehouseId, productId);
        Optional<DamageQuantityProduct> damageQuantityProductInWarehouse = getDamageQuantityProductInWarehouse(productId, warehouse);

        damageQuantityProductInWarehouse.ifPresent(o -> {
                    checkIfQuantityIsValid(quantity, o.getQuantity());
                    damageQuantityProductService.updateQuantity(o.getId(), o.getQuantity() - quantity);
                });

        damageQuantityProductInWarehouse.orElseThrow(() -> new IllegalArgumentException("Invalid product no stocks in designated warehouse"));
    }

    @Override
    public List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks() {
        List<Warehouse> warehouses = warehouseService.findAll();
        List<GoodQuantityProduct> goodQuantityProducts = null;
        for(Warehouse o : warehouses){
            o.getGoodQuantityProducts().stream().filter(w -> (w.getQuantity() <= w.getProduct().getMinimumStocks())).collect(Collectors.toList());
        }
       return goodQuantityProducts;
    }

    @Override
    public List<GoodQuantityProduct> sortMinimumStockByProductDescription() {
        List<GoodQuantityProduct> sortMinimumByProductDescription = findByGoodQuantityProductThatReachedMinimumStocks();
        Collections.sort(sortMinimumByProductDescription, new Comparator<GoodQuantityProduct>() {

            @Override
            public int compare(GoodQuantityProduct o1, GoodQuantityProduct o2) {
                // TODO Auto-generated method stub
                return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription());
            }

        });
        return sortMinimumByProductDescription;
    }

}
