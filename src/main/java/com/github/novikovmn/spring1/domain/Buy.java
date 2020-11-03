package com.github.novikovmn.spring1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "buys")
public class Buy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "buy_cost")
    private Integer buyCost;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "date")
    private Date date;

    public Buy() {}
    public Buy(Customer customer, Product product, Integer quantity) {
        this.customer = customer;
        this.product = product;
        this.buyCost = product.getCost();
        this.quantity = quantity;
        this.date = new Date();
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Product getProduct() { return product; }
    public Integer getBuyCost() { return buyCost; }
    public Integer getQuantity() { return quantity; }
    public Date getDate() { return date; }

    public void setId(Long id) { this.id = id; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setProduct(Product product) { this.product = product; }
    public void setBuyCost(Integer buyCost) { this.buyCost = buyCost; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setDate(Date date) { this.date = date; }

}
