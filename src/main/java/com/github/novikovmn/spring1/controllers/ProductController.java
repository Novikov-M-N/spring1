package com.github.novikovmn.spring1.controllers;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.service.ProductService;
import com.github.novikovmn.spring1.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

//    @Autowired
//    public ProductController(ProductService productServiceImpl) {
//        this.productService = productService;
//    }

    // app/products/
    @RequestMapping(value = "")
    public String index(Model model) {
        return "index";
    }

    // app/products/all
    @RequestMapping(value = "/all")
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "all";
    }

    //app/products/bycost/{minCost}{maxCost}
}
