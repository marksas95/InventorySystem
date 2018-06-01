package com.trainee.inv.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
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
	@Autowired
	GoodQuantityProductRepository goodProductRepo;

	@Override
	public void stockInGoodQuantityProduct(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		if (!warehouse.isActive()) {
			throw new IllegalArgumentException("Invalid warehouse because it is inactive");
		}
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		if (!goodQuantityProduct.getProduct().isActive()) {
			throw new IllegalArgumentException("Invalid product because it is inactive");
		}
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for (GoodQuantityProduct o : goodQuantityProducts) {
			if (o.getId() == goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity += quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
						initialQuantity);
				break;
			}
		}
	}

	@Override
	public void stockOutGoodQuantityProduct(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		if (!warehouse.isActive()) {
			throw new IllegalArgumentException("Invalid warehouse because it is inactive");
		}
		if (!goodQuantityProduct.getProduct().isActive()) {
			throw new IllegalArgumentException("Invalid product because it is inactive");
		}
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for (GoodQuantityProduct o : goodQuantityProducts) {
			if (o.getId() == goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity -= quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
						initialQuantity);
				break;
			}
		}
	}

	@Override
	public void transferStocks(int warehouseIdFrom, int warehouseIdTo, int goodQuantityProductIdFrom,
			int goodQuantityProductIdTo, int quantity) {
		stockOutGoodQuantityProduct(warehouseIdFrom, goodQuantityProductIdFrom, quantity);
		stockInGoodQuantityProduct(warehouseIdTo, goodQuantityProductIdTo, quantity);
	}

	@Override
	public void reconcileProduct(int warehouseId, int goodQuantityProductId, int physicalQuantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for (GoodQuantityProduct o : goodQuantityProducts) {
			if (o.getId() == goodQuantityProductId) {
				int systemQuantity = o.getQuantity();
				ReconcileProduct reconcileProduct = new ReconcileProduct();
				reconcileProduct.setGoodQuantityProduct(goodQuantityProduct);
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
	public void stockInDamageQuantityProduct(int warehouseId, int damageQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		if (!warehouse.isActive()) {
			throw new IllegalArgumentException("Invalid warehouse because it is inactive");
		}
		DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(damageQuantityProductId);
		if (!damageQuantityProduct.getProduct().isActive()) {
			throw new IllegalArgumentException("Invalid product because it is inactive");
		}
		List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProduct();
		for (DamageQuantityProduct o : damageQuantityProducts) {
			if (o.getId() == damageQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity += quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
						initialQuantity);
				break;
			}
		}
	}

	@Override
	public void stockOutDamageQuantityProduct(int warehouseId, int damageQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		if (!warehouse.isActive()) {
			throw new IllegalArgumentException("Invalid warehouse because it is inactive");
		}
		DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(damageQuantityProductId);
		if (!damageQuantityProduct.getProduct().isActive()) {
			throw new IllegalArgumentException("Invalid product because it is inactive");
		}
		List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProduct();
		for (DamageQuantityProduct o : damageQuantityProducts) {
			if (o.getId() == damageQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity -= quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(),
						initialQuantity);
				break;
			}
		}
	}

	@Override
	public List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks() {
		List<Warehouse> warehouses = warehouseService.findAll();
		List<GoodQuantityProduct> goodQuantityProductsThatReachedMinimumStocks = null;
		for (Warehouse o : warehouses) {
			List<GoodQuantityProduct> goodQuantityProducts = o.getGoodQuantityProducts();
			for (GoodQuantityProduct u : goodQuantityProducts) {
				if (u.getQuantity() < u.getProduct().getMinimumStocks()) {
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

}
