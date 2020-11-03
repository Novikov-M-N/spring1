package com.github.novikovmn.spring1.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Buy> buys = new ArrayList<>();

    public Product() {}
    public Product(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getCost() { return cost; }
    public List<Buy> getBuys() { return buys; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCost(Integer cost) { this.cost = cost; }
    public void setBuys(List<Buy> buys) { this.buys = buys; }
}
