package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductsRepository repository;

    @Autowired
    public void setRepository(ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return repository.findAll();
    }
}
