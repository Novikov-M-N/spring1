package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.entities.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product getById(int id) { return productRepo.getById(id); }

    public List<Product> getAll() {
        List<Product> products = productRepo.getAll();
        products.sort(Comparator.comparingInt(Product::getId));
        return products;
    }

    //Данную функцию реализовать не успел, но её нет в задании
    public List<Product> getByPrice(int min, int max) {
        return productRepo.getAll().stream()
                .filter(product -> product.getCost() >= min && product.getCost() <= max)
                .sorted(Comparator.comparingInt(Product::getCost))
                .collect(Collectors.toList());
    }

    public Product add(Product product) { return productRepo.add(product); }

    public void removeById(int id) { productRepo.remove(id); }
}
