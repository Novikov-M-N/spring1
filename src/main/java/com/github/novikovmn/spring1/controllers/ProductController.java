package com.github.novikovmn.spring1.controllers;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"", "/"})
public class ProductController {

    @Autowired
    private ProductService productService;

    // app
    @RequestMapping({"", "/"})
    public String index(Model model) {
        return "index";
    }

    //app/products/?min={minCost}&max={maxCost}&page={page}
    @RequestMapping({"/products", "/products/"})
    public String getByCost(
            Model model
            , @RequestParam Map<String, String> params
//            , @RequestParam(value = "mincost", required = false) Integer minCost
//            , @RequestParam(value = "maxcost", required = false) Integer maxCost
    ) {
        final Integer PAGE_SIZE = 2;
        Integer page = 1;
        Page<Product> products = null;
        String title = "";
        String link = "";

        if (params.containsKey("page")) page = Integer.parseInt(params.get("page"));
        Integer minCost = parceInt(params, "mincost");
        Integer maxCost = parceInt(params, "maxcost");
        Pageable productsPageable = PageRequest.of(page - 1, PAGE_SIZE);
        if (minCost != null && maxCost != null) {
            products = productService.getByMinAndMaxCostPageable(minCost, maxCost, productsPageable);
            title = "Товары с ценой от " + costToString(minCost) + " до " + costToString(maxCost);
            link = "?mincost=" + minCost + "&maxcost=" + maxCost + "&page=";
        }
        if (minCost != null && maxCost == null) {
            products = productService.getByMinCostPageable(minCost, productsPageable);
            title = "Товары с ценой от " + costToString(minCost);
            link = "?mincost=" + minCost + "&page=";
        }
        if (maxCost != null && minCost == null) {
            products = productService.getByMaxCostPageable(maxCost, productsPageable);
            title = "Товары с ценой до " + costToString(maxCost);
            link = "?maxcost=" + maxCost + "&page=";
        }
        if (minCost == null && maxCost == null) {
            products = productService.getAllPageable(productsPageable);
            title = "Список всех товаров";
            link = "?page=";
        }
        model.addAttribute("title", title);
        model.addAttribute("products", products);
        model.addAttribute("page", page);
        model.addAttribute("total_pages", products.getTotalPages());
        model.addAttribute("link", link);
        return "productlist";
    }

    public String costToString(Integer cost) {
        return cost / 100 + "р. " + cost % 100 + "к.";
    }

    public Integer parceInt(Map<String, String> params, String key) {
        if (params.containsKey(key)) return Integer.parseInt(params.get(key));
        return null;
    }
}
