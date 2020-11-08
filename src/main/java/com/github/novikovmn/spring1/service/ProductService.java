package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    List<Product> getByMinCost(Integer minCost);
    List<Product> getByMaxCost(Integer maxCost);
    List<Product> getByMinAndMaxCost(Integer minCost, Integer maxCost);

    Page<Product> getAllPageable(Pageable pageable);
    Page<Product> getByMinCostPageable(Integer minCost, Pageable pageable);
    Page<Product> getByMaxCostPageable(Integer maxCost, Pageable pageable);
    Page<Product> getByMinAndMaxCostPageable(Integer minCost, Integer maxCost, Pageable pageable);
}
