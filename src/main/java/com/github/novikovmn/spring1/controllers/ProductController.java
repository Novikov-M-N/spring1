package com.github.novikovmn.spring1.controllers;

import com.github.novikovmn.spring1.service.ProductService;
import com.github.novikovmn.spring1.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productService = productServiceImpl;
    }

    // app/products/
    @RequestMapping(value = "")
    public String index(Model model) {
        return "index";
    }
}
