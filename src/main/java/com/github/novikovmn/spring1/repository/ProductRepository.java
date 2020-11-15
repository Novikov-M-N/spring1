package com.github.novikovmn.spring1.repository;

import com.github.novikovmn.spring1.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Фильтрация по минимальной и максимальной цене, постраничное отображение
    Page<Product> findByCostBetween(Integer minCost, Integer maxCost, Pageable pageable);
}