package com.trainee.inv.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

    boolean existsByName(String name);
}
