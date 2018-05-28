package com.trainee.inv.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
	Warehouse findByName(String name);
}
