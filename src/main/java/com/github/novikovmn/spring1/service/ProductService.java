package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.domain.Product;
import com.github.novikovmn.spring1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent() ? product.get() : new Product(id, "Товар с id = " + id + " не существует", 0);
    }

    @Transactional(readOnly = true)
    public List<Product> getAll() { return productRepository.findAll(); }

    @Transactional(readOnly = true)
    public Page<Product> getAllPageable(Pageable pageable) { return productRepository.findAll(pageable); }

    @Transactional(readOnly = true)
    public Page<Product> getByMinAndMaxCostPageable(Integer minCost, Integer maxCost, Pageable pageable) {
        return productRepository.findByCostBetween(minCost, maxCost, pageable);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) { productRepository.deleteById(id); }
}