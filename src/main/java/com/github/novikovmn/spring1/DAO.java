package com.github.novikovmn.spring1;

import com.github.novikovmn.spring1.domain.Buy;
import com.github.novikovmn.spring1.domain.Customer;
import com.github.novikovmn.spring1.domain.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static DAO entity;
    private EntityManager em;

    public static DAO getInstance(EntityManager em) {
        if (entity == null) entity = new DAO(em);
        return entity;
    }

    private DAO(EntityManager em) { this.em = em; }

    public List<Customer> getCustomers() {
        em.getTransaction().begin();
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        for (Customer customer : customers) { em.refresh(customer); }
        em.getTransaction().commit();
        return customers;
    }

    public List<Product> getProducts() {
        em.getTransaction().begin();
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        for (Product product : products) { em.refresh(product); }
        em.getTransaction().commit();
        return products;
    }

    public List<Buy> getBuys() {
        em.getTransaction().begin();
        List<Buy> buys = em.createQuery("SELECT b FROM Buy b", Buy.class).getResultList();
        for (Buy buy : buys) { em.refresh(buy); }
        em.getTransaction().commit();
        return buys;
    }

    public List<Buy> getBuysByProductId(Long id) {
        List<Buy> result = new ArrayList<>();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.refresh(product);
            result = product.getBuys();
        }
        em.getTransaction().commit();
        return result;
    }

    public List<Buy> getBuysByProductTitle(String title) {
        List<Buy> result = new ArrayList<>();
        em.getTransaction().begin();
        Product product = em.createQuery(
                "SELECT p FROM Product p WHERE p.title = :title"
                , Product.class
        ).setParameter("title", title).getSingleResult();
        if (product != null) {
            em.refresh(product);
            result = product.getBuys();
        }
        em.getTransaction().commit();
        return result;
    }

    public List<Buy> getBuysByCustomerId(Long id) {
        List<Buy> result = new ArrayList<>();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.refresh(customer);
            result = customer.getBuys();
        }
        em.getTransaction().commit();
        return result;
    }

    public List<Buy> getBuysByCustomerTitle(String name) {
        List<Buy> result = new ArrayList<>();
        em.getTransaction().begin();
        Customer customer = em.createQuery(
                "SELECT c FROM Customer c WHERE c.name = :name"
                , Customer.class
        ).setParameter("name", name).getSingleResult();
        if (customer != null) {
            em.refresh(customer);
            result = customer.getBuys();
        }
        em.getTransaction().commit();
        return result;
    }

    public void deleteCustomerById(Long id) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.refresh(customer);
            for (Buy buy : customer.getBuys()) {
                em.refresh(buy);
                em.refresh(buy.getProduct());
                buy.getProduct().getBuys().remove(buy);
            }
            em.remove(customer);
        }
        em.getTransaction().commit();
    }

    public void deleteCustomerByName(String name) {
        em.getTransaction().begin();
        Customer customer = em.createQuery(
                "SELECT c FROM Customer c WHERE c.name = :name"
                , Customer.class
        ).setParameter("name", name).getSingleResult();
        if (customer != null) {
            em.refresh(customer);
            for (Buy buy : customer.getBuys()) {
                em.refresh(buy);
                em.refresh(buy.getProduct());
                buy.getProduct().getBuys().remove(buy);
            }
            em.remove(customer);
        }
        em.getTransaction().commit();
    }

}
