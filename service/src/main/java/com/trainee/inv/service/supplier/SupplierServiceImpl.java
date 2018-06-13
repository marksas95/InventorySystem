package com.trainee.inv.service.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.repository.supplier.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    //to be edit
    public Supplier update(int id, String name) {
        if (supplierRepository.existsByName(name)) {
            throw new IllegalArgumentException("Supplier Name Already Exists");
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        Supplier supplier = optionalSupplier.get();
        supplier.setName(name);
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier create(String name) {
        checkIfNameExists(name);
        Supplier supplier = new Supplier();
        supplier.setName(name);
        return supplierRepository.save(supplier);
    }

    private void checkIfNameExists(String name) {
        if (supplierRepository.existsByName(name)) {
            throw new IllegalArgumentException("Supplier Name Already Exist");
        }
    }

    @Override
    public Supplier findByName(String name) {
        return supplierRepository.findByName(name);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public void delete(int id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier findById(int id) {
        return supplierRepository.findById(id).get();
    }

}
