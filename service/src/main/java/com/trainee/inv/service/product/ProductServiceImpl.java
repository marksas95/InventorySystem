package com.trainee.inv.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;
import com.trainee.inv.repository.supplier.Supplier;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired

	ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		boolean existsById = productRepository.existsById(product.getId());
		boolean exsistsByItemCode = productRepository.exsistsByItemCode(product.getItemCode());
		boolean exsistsBySerialNumber = productRepository.exsistsBySerialNumber(product.getSerialNumber());
		if (existsById) {
			throw new IllegalArgumentException("Invalid field id");
		} else if (exsistsByItemCode) {
			throw new IllegalArgumentException("Invalid field, ItemCode already exists.");
		} else if (exsistsBySerialNumber) {
			throw new IllegalArgumentException("Invalid field, SerialNumer already exists.");
		}
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		boolean existsById = productRepository.existsById(product.getId());
		if (!existsById) {
			throw new IllegalArgumentException("Invalid update field id not exists.");
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
		Product product = productRepository.findByName(name);
		return product != null ? product : null;
	}

	@Override
	public Product findById(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		return optionalProduct.get();
	}

	@Override
	public void delete(int id) {
		boolean existsById = productRepository.existsById(id);
		if (!existsById) {
			throw new IllegalArgumentException("Invalid delete field id not exists.");
		}
		productRepository.deleteById(id);

	}

}
