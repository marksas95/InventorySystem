package com.trainee.inv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.damagequantityproduct.DamageQuantityProductService;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.product.ProductService;
import com.trainee.inv.service.reconcileproduct.ReconcileProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@Service
public class StockQuantityServiceImpl implements StockQuantityService{

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
	public void stockInGoodQuantityProduct(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for(GoodQuantityProduct o :goodQuantityProducts) {
			if(o.getId()==goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity += quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(), initialQuantity);
				break;
			}
		}
	}

	@Override
	public void stockOutGoodQuantityProduct(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for(GoodQuantityProduct o :goodQuantityProducts) {
			if(o.getId()==goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity -= quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(), initialQuantity);
				break;
			}
		}
	}
	
	@Override
	public void transferStocks(int warehouseIdFrom, int warehouseIdTo, int goodQuantityProductIdFrom, int goodQuantityProductIdTo, int quantity) {
		stockOutGoodQuantityProduct(warehouseIdFrom, goodQuantityProductIdFrom, quantity);
		stockInGoodQuantityProduct(warehouseIdTo, goodQuantityProductIdTo, quantity);
	}

	@Override
	public void reconcileProduct(int warehouseId, int goodQuantityProductId, int physicalQuantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for(GoodQuantityProduct o :goodQuantityProducts) {
			if(o.getId()==goodQuantityProductId) {
				int systemQuantity = o.getQuantity();
				ReconcileProduct reconcileProduct = new ReconcileProduct();
				reconcileProduct.setGoodQuantityProduct(goodQuantityProduct);
				reconcileProduct.setWarehouse(warehouse);
				reconcileProduct.setPhysicalCount(physicalQuantity);
				reconcileProduct.setSystemCount(systemQuantity);
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(), physicalQuantity);
				ReconcileProduct newReconcileProduct = reconcileProductService.create(reconcileProduct);
				break;
			}
		}
	}

	@Override
	public void stockInDamageQuantityProduct(int warehouseId, int damageQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(damageQuantityProductId);
		List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProduct();
		for(DamageQuantityProduct o :damageQuantityProducts) {
			if(o.getId()==damageQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity += quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(), initialQuantity);
				break;
			}
		}
	}

	@Override
	public void stockOutDamageQuantityProduct(int warehouseId, int damageQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		DamageQuantityProduct damageQuantityProduct = damageQuantityProductService.findById(damageQuantityProductId);
		List<DamageQuantityProduct> damageQuantityProducts = warehouse.getDamageQuantityProduct();
		for(DamageQuantityProduct o :damageQuantityProducts) {
			if(o.getId()==damageQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity -= quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.updateQuantity(o.getId(), initialQuantity);
				break;
			}
		}
	}
}


