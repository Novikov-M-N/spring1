package com.github.novikovmn.spring1.domain;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    public Product() {}

    public Product(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(Long id, String title, Integer cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getCost() { return cost; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCost(Integer cost) { this.cost = cost; }
}