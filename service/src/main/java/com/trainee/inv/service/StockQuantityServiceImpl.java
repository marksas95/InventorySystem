package com.trainee.inv.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;
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
        ifPresentAddQuantity(quantity, goodQuantityProduct);
    }

    private Optional<GoodQuantityProduct> getGoodQuantityProductOfProductInWarehouse(int productId, Warehouse warehouse) {
        return warehouse.getGoodQuantityProducts()
                .stream()
                .filter(o -> o.getProduct().getId() == productId)
                .findFirst();
    }

    private void ifPresentAddQuantity(int quantity, Optional<GoodQuantityProduct> goodQuantityProduct) {
        goodQuantityProduct.ifPresent(o -> goodQuantityProductService.updateQuantity(o.getId(), o.getQuantity()+quantity));
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
                    checkIfQuantityIsValid(quantity,o.getQuantity());
                    goodQuantityProductService.updateQuantity(o.getId(),o.getQuantity()-quantity);
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
        stockOutFromWarehouse(warehouseIdFrom, productId, quantity);
        stockInToWarehouse(warehouseIdTo, productId, quantity);
    }

    private void stockInToWarehouse(int warehouseIdTo, int productId, int quantity) {
        Warehouse warehouseTo = checkIfWarehouseAndProductIstActive(warehouseIdTo, productId);
        Optional<GoodQuantityProduct> goodQuantityProductTo = getGoodQuantityProductOfProductInWarehouse(productId, warehouseTo);
        ifNotPresentCreateNewGoodQuantityProduct(productId, quantity, warehouseTo, goodQuantityProductTo);
        ifPresentAddQuantity(quantity, goodQuantityProductTo);
    }

    private void stockOutFromWarehouse(int warehouseIdFrom, int productId, int quantity) {
        Warehouse warehouseFrom = checkIfWarehouseAndProductIstActive(warehouseIdFrom, productId);
        Optional<GoodQuantityProduct> goodQuantityProductFrom = getGoodQuantityProductOfProductInWarehouse(productId, warehouseFrom);
        ifPresentSubtractQuantity(quantity, goodQuantityProductFrom);
        ifNotPresentThrowIllegalArgumentException(goodQuantityProductFrom);
    }

    //for change
    @Override
    public void reconcileProduct(int warehouseId, int ProductId, int physicalQuantity) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        List<GoodQuantityProduct> goodQuantityProductsWithProductId = goodQuantityProductService.findByProductId(ProductId);
        List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
        for (GoodQuantityProduct o : goodQuantityProductsWithProductId) {
            if (goodQuantityProducts.contains(o)) {
                int systemQuantity = o.getQuantity();
                ReconcileProduct reconcileProduct = new ReconcileProduct();
                reconcileProduct.setGoodQuantityProduct(o);
                reconcileProduct.setWarehouse(warehouse);
                reconcileProduct.setPhysicalCount(physicalQuantity);
                reconcileProduct.setSystemCount(systemQuantity);
                GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
                        physicalQuantity);
                ReconcileProduct newReconcileProduct = reconcileProductService.create(reconcileProduct);
                break;
            }
        }
    }

    @Override
    public void stockInDamageQuantityProduct(int warehouseIdFrom, int warehouseIdTo, int productId, int quantity) {
        Warehouse warehouseTo = warehouseService.findById(warehouseIdTo);

        if (!warehouseTo.isActive()) {
            throw new IllegalArgumentException("Invalid warehouseTo because it is inactive");
        }

        stockOutGoodQuantityProduct(warehouseIdFrom, productId, quantity);

        Integer value = null;
        List<DamageQuantityProduct> damageQuantityProducts = warehouseTo.getDamageQuantityProducts();
        for (DamageQuantityProduct o : damageQuantityProducts) {
            if (o.getProduct().getId() == productId) {
                value = o.getQuantity();
                value += quantity;
                checkIfQuantityIsValid(quantity, value);
                DamageQuantityProduct newDamageQuantityProduct = damageQuantityProductService.updateQuantity(o.getId(),
                        value);
                break;
            }
        }
        if (value == null || damageQuantityProducts == null) {
            Product product = productService.findById(productId);
            DamageQuantityProduct newDamageQuantityProduct = damageQuantityProductService.create(product, quantity);
            damageQuantityProducts.add(newDamageQuantityProduct);
            // Apply warehouse service a add damageproduct and goodproduct
            warehouseTo.setDamageQuantityProducts(damageQuantityProducts);
            warehouseService.update(warehouseTo);
        }
    }

    //for change
    @Override
    public void stockOutDamageQuantityProduct(int warehouseId, int productId, int quantity) {

        Product product = productService.findById(productId);

        Warehouse warehouse = checkIfWarehouseAndProductIstActive(warehouseId, productId);
        List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProducts();
        List<DamageQuantityProduct> damageQuantityProductsWithProductId = damageQuantityProductService
                .findByProductId(productId);
        Integer value = null;
        for (DamageQuantityProduct o : damageQuantityProductsWithProductId) {
            if (damageQuantityProducts.contains(o)) {
                value = o.getQuantity();
                value -= quantity;
                checkIfQuantityIsValid(quantity, value);
                DamageQuantityProduct newDamageQuantityProduct = damageQuantityProductService.updateQuantity(o.getId(),
                        value);
                break;
            }
        }
        if (value == null) {
            throw new IllegalArgumentException("Invalid product no stocks in designated warehouse");
        }

//		Warehouse warehouse = warehouseService.findById(warehouseId);
//		if (!warehouse.isActive()) {
//			throw new IllegalArgumentException("Invalid warehouse because it is inactive");
//		}
//		DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(damageQuantityProductId);
//		if (!damageQuantityProduct.getProduct().isActive()) {
//			throw new IllegalArgumentException("Invalid product because it is inactive");
//		}
//		List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProduct();
//		for (DamageQuantityProduct o : damageQuantityProducts) {
//			if (o.getId() == damageQuantityProductId) {
//				int initialQuantity = o.getQuantity();
//				checkIfQuantityIsValid(quantity, initialQuantity);
//				initialQuantity -= quantity;
//				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
//						initialQuantity);
//				break;
//			}
//		}
    }

    @Override
    public List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks() {
        List<Warehouse> warehouses = warehouseService.findAll();
        List<GoodQuantityProduct> goodQuantityProductsThatReachedMinimumStocks = null;
        for (Warehouse o : warehouses) {
            List<GoodQuantityProduct> goodQuantityProducts = o.getGoodQuantityProducts();
            for (GoodQuantityProduct u : goodQuantityProducts) {
                if (u.getQuantity() <= u.getProduct().getMinimumStocks()) {
                    if (goodQuantityProductsThatReachedMinimumStocks == null) {
                        goodQuantityProductsThatReachedMinimumStocks = new ArrayList<>();
                    }
                    goodQuantityProductsThatReachedMinimumStocks.add(u);
                }
            }
        }
        return goodQuantityProductsThatReachedMinimumStocks;
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

    // @Override
    // public void stockOutGoodQuantityProduct(int warehouseId, int
    // goodQuantityProductId, int quantity) {
    // Warehouse warehouse = checkIfWarehouseAndProductIstActive(warehouseId,
    // goodQuantityProductId);
    // List<GoodQuantityProduct> goodQuantityProducts =
    // warehouse.getGoodQuantityProducts();
    // for (GoodQuantityProduct o : goodQuantityProducts) {
    // if (o.getId() == goodQuantityProductId) {
    // int initialQuantity = o.getQuantity();
    // checkIfQuantityIsValid(quantity, initialQuantity);
    // initialQuantity -= quantity;
    // GoodQuantityProduct newGoodQuantityProduct =
    // goodQuantityProductService.updateQuantity(o.getId(),
    // initialQuantity);
    // break;
    // }
    // }
    // }

}
