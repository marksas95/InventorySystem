package com.trainee.inv.service.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		boolean existsById = productRepository.existsById(product.getId());
		boolean existsByItemCode = productRepository.existsByItemCode(product.getItemCode());
		boolean existsBySerialNumber = productRepository.existsBySerialNumber(product.getSerialNumber());
		checkIfIdExists(existsById, existsByItemCode, existsBySerialNumber);
		return productRepository.save(product);
	}

	private void checkIfIdExists(boolean existsById, boolean existsByItemCode, boolean existsBySerialNumber) {
		if (existsById) {
			throw new IllegalArgumentException("Invalid ID");
		} else if (existsByItemCode) {
			throw new IllegalArgumentException("Invalid operation, ItemCode already exists.");
		} else if (existsBySerialNumber) {
			throw new IllegalArgumentException("Invalid operation, Serial Number already exists.");
		}
	}

	@Override
	public Product update(Product product) {
		boolean existsById = productRepository.existsById(product.getId());
		if (!existsById) {
			throw new IllegalArgumentException("Invalid operation, ID does not exists.");
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
		returnList = findAllProductsByItemCode(itemCode, list, returnList);
		return returnList;
	}

	private List<Product> findAllProductsByItemCode(String itemCode, List<Product> list, List<Product> returnList) {
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
		returnList = findAllProductsByUnitOfMeasurement(unitOfMeasurement, list, returnList);
		return returnList;
	}

	private List<Product> findAllProductsByUnitOfMeasurement(String unitOfMeasurement, List<Product> list,
			List<Product> returnList) {
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
		returnList = findAllProductsByDescription(description, list, returnList);
		return returnList;
	}

	private List<Product> findAllProductsByDescription(String description, List<Product> list,
			List<Product> returnList) {
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
			throw new IllegalArgumentException("Invalid operation, ID does not exists!");
		}
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
	public List<Product> findByCategoryId(int id) {
		return productRepository.findByCategoryId(id);
	}

	@Override
	public List<Product> findBySupplierId(int id) {
		return productRepository.findBySupplierId(id);

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
