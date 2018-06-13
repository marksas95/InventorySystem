package com.trainee.inv.repository.damagequantityproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DamageQuantityProductRepository extends JpaRepository<DamageQuantityProduct, Integer> {

    List<DamageQuantityProduct> findByProductId(int id);

}