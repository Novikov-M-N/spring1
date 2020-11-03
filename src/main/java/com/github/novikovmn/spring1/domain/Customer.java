package com.github.novikovmn.spring1.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Buy> buys = new ArrayList<>();

    public Customer() {};
    public Customer(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Buy> getBuys() { return buys; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBuys(List<Buy> buys) { this.buys = buys; }
}
