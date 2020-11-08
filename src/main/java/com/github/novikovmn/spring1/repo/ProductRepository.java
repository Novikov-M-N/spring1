package com.github.novikovmn.spring1.repo;

import com.github.novikovmn.spring1.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Фильтрация по минимальной цене
    List<Product> findByCostGreaterThanEqual(Integer cost);

    // Фильтрация по максимальной цене
    List<Product> findByCostLessThanEqual(Integer cost);

    // Фильтрация по минимальной и максимальной цене
    List<Product> findByCostBetween(Integer minCost, Integer maxCost);

    // Фильтрация по минимальной цене, постраничное отображение
    Page<Product> findByCostGreaterThanEqual(Integer minCost, Pageable pageable);

    // Фильтрация по максимальной цене, постраничное отображение
    Page<Product> findByCostLessThanEqual(Integer maxCost, Pageable pageable);

    // Фильтрация по минимальной и максимальной цене, постраничное отображение
    Page<Product> findByCostBetween(Integer minCost, Integer maxCost, Pageable pageable);
}
