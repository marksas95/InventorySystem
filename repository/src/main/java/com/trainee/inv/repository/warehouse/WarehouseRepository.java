package com.trainee.inv.repository.warehouse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findByName(String name);

    List<Warehouse> findByIsActive(boolean isActive);

    Warehouse findByGoodQuantityProductsId(int id);

    boolean existsByName(String name);

    boolean existsByAddress(String address);

    boolean existsByDescription(String description);
}
