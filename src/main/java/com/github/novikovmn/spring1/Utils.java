package com.github.novikovmn.spring1;

import com.github.novikovmn.spring1.domain.Buy;
import com.github.novikovmn.spring1.domain.Customer;
import com.github.novikovmn.spring1.domain.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private static Utils entity;
    private EntityManager em;

    public static Utils getInstance(EntityManager em) {
        if (entity == null) entity = new Utils(em);
        return entity;
    }

    private Utils(EntityManager em) {
        this.em = em;
    }

    // Преобразование данных в двумерный массив строк для сущностей типа Customer
    public List<List<String>> customersAsTable(List<Customer> customers) {
        List<List<String>> result = new ArrayList<>();
        result.add(Arrays.asList(new String[]{"id","Имя"}));
        for (Customer customer : customers) {
            List<String> row = new ArrayList<>();
            row.add(customer.getId().toString());
            row.add(customer.getName());
            result.add(row);
        }
        return result;
    }

    // Преобразование данных в двумерный массив строк для сущностей типа Product
    public List<List<String>> productsAsTable(List<Product> products) {
        List<List<String>> result = new ArrayList<>();
        result.add(Arrays.asList(new String[]{"id","Наименование","Цена"}));
        for (Product product : products) {
            List<String> row = new ArrayList<>();
            row.add(product.getId().toString());
            row.add(product.getTitle());
            row.add(String.format("%d р.%3d к.", product.getCost()/100, product.getCost()%100));
            result.add(row);
        }
        return result;
    }

    // Преобразование данных в двумерный массив строк для сущностей типа Buy
    public List<List<String>> buysAsTable(List<Buy> buys) {
        List<List<String>> result = new ArrayList<>();
        result.add(Arrays.asList(new String[]{"id","Дата","Покупатель","Товар","Цена продажи","Кол-во (шт.)"}));
        for (Buy buy : buys) {
            List<String> row = new ArrayList<>();
            row.add(buy.getId().toString());
            row.add(buy.getDate().toString());
            row.add(buy.getCustomer().getId().toString());
            row.add(buy.getProduct().getId().toString());
            row.add(String.format("%d р.%3d к.", buy.getBuyCost()/100, buy.getBuyCost()%100));
            row.add(buy.getQuantity().toString());
            result.add(row);
        }
        return result;
    }

    // Преобразование данных в двумерный массив строк для сущностей типа Buy - подробное отображение
    public List<List<String>> buysAdvancedAsTable(List<Buy> buys) {
        List<List<String>> result = new ArrayList<>();
        result.add(Arrays.asList(new String[]{"id","Дата","Покупатель","Товар","Цена продажи","Кол-во (шт.)"}));
        for (Buy buy : buys) {
            List<String> row = new ArrayList<>();
            row.add(buy.getId().toString());
            row.add(buy.getDate().toString());
            row.add(buy.getCustomer().getName());
            row.add(buy.getProduct().getTitle());
            row.add(String.format("%d р.%3d к.", buy.getBuyCost()/100, buy.getBuyCost()%100));
            row.add(buy.getQuantity().toString());
            result.add(row);
        }
        return result;
    }

}
