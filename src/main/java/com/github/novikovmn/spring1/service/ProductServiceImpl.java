package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() { return productRepository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByMinCost(Integer minCost) { return productRepository.findByCostGreaterThanEqual(minCost); }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByMaxCost(Integer maxCost) { return productRepository.findByCostLessThanEqual(maxCost); }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByMinAndMaxCost(Integer minCost, Integer maxCost) {
        return productRepository.findByCostBetween(minCost, maxCost);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllPageable(Pageable pageable) { return productRepository.findAll(pageable); }

    @Override
    public Page<Product> getByMinCostPageable(Integer minCost, Pageable pageable) {
        return productRepository.findByCostGreaterThanEqual(minCost, pageable);
    }

    @Override
    public Page<Product> getByMaxCostPageable(Integer maxCost, Pageable pageable) {
        return productRepository.findByCostLessThanEqual(maxCost, pageable);
    }

    @Override
    public Page<Product> getByMinAndMaxCostPageable(Integer minCost, Integer maxCost, Pageable pageable) {
        return productRepository.findByCostBetween(minCost, maxCost, pageable);
    }
}
