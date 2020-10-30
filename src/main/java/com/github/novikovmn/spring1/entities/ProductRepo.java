package com.github.novikovmn.spring1.entities;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepo {
    private Map<Integer, Product> repo = new HashMap<>();
    private int id = 0;

    {
        this.add(new Product("Подвес прямой 300x30x0.9 мм",660));
        this.add(new Product("Уголок крепежный соединительный 40х40х40х1.8 мм",570));
        this.add(new Product("Уголок крепежный 25х14х25х1.75 мм",400));
        this.add(new Product("Шайба пружинная DIN 127B 4x9 мм, 15 шт.",410));
        this.add(new Product("Пластина бытовая 50х14х1.5 мм, сталь",650));
        this.add(new Product("Винт для крепления цилиндра M5x70 мм",720));
    }

    public Product getById(int id) {
        return repo.get(id);
    }

    public List<Product> getAll() {
        return new ArrayList<>(repo.values());
    }

    public Product add(Product product) {
        product.setId(id);
        repo.put(id++, product);
        return product;
    }

    public void remove(int id) { repo.remove(id); }
}
