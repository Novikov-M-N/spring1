package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    public void setProductRepository(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setTitle("Новый");
        product.setCost(100);
        products.add(product);
//        return products;
        return productRepository.findAll();
    }
}
