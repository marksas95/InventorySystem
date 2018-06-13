package com.trainee.inv.repository.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Supplier findByName(String name);

    boolean existsByName(String name);

}
