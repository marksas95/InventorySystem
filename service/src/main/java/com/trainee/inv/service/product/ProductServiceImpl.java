package com.trainee.inv.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired

	ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		boolean nameExist = checkIfProductNameExist(product.getName());
		if (nameExist) {
			throw new IllegalArgumentException("Product Name Already Exist");
		}
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		boolean nameExist = checkIfProductNameExist(product.getName());
		boolean idExist = checkIfProductIdExist(product.getId());
		if (nameExist) {
			throw new IllegalArgumentException("Product Name already added.");
		}

		if (!idExist) {
			throw new IllegalArgumentException("Product Id must be in database.");
		}
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
	public List<Product> searchByItemCode(String itemCode, boolean isActive) {
		List<Product> list = findByIsActive(isActive);
		List<Product> returnList = null;
		for (Product o : list) {
			if (o.getItemCode().contains(itemCode)) {
				if (returnList == null) {
					returnList = new ArrayList<Product>();
				}
				returnList.add(o);
			}
		}
		return returnList;
	}

	@Override
	public List<Product> searchByUnitOfMeasurement(String unitOfMeasurement, boolean isActive) {
		List<Product> list = findByIsActive(isActive);
		List<Product> returnList = null;
		for (Product o : list) {
			if (o.getUnitOfMeasurement().contains(unitOfMeasurement)) {
				if (returnList == null) {
					returnList = new ArrayList<Product>();
				}
				returnList.add(o);
			}
		}
		return returnList;
	}

	@Override
	public List<Product> searchByDescription(String description, boolean isActive) {
		List<Product> list = findByIsActive(isActive);
		List<Product> returnList = null;
		for (Product o : list) {
			if (o.getDescription().contains(description)) {
				if (returnList == null) {
					returnList = new ArrayList<Product>();
				}
				returnList.add(o);
			}
		}
		return returnList;
	}


	@Override
	public Product findByName(String name) {
		// Product product = productRepository.findByName(name);
		// return product != null ? product : null;
		return null;
	}

	@Override
	public Product findById(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		return optionalProduct.get();
	}
	
	private boolean checkIfProductIdExist(int id) {
		Optional<Product> optionalObject = productRepository.findById(id);
		Product product = optionalObject.get();
		return product != null;
	}

	private boolean checkIfProductNameExist(String name) {
		Product product = productRepository.findByName(name);
		return product != null;
	}



}
