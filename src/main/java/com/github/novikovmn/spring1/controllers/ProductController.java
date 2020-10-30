package com.github.novikovmn.spring1.controllers;

import com.github.novikovmn.spring1.entities.Product;
import com.github.novikovmn.spring1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // app/products/
    @RequestMapping(value = "")
    public String index(Model model) {
        return "products";
    }

    //GET app/products/all
    @GetMapping(value = "/all")
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "all";
    }

    //GET app/prodicts/{id}
    @GetMapping(value = "/{id}")
    public String getById(Model model, @PathVariable("id") int id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product == null ? new Product(id, "Такого товара нет", 0) : product);
        return "product";
    }

    //GET app/products/{id}/price
    @GetMapping(value = "/{id}/price")
    @ResponseBody
    public String getCost(@PathVariable("id") int id) {
        Product product = productService.getById(id);
        return String.valueOf(product == null ? 0 : product.getCost());
    }


    //GET app/products/add
    @GetMapping(value = "/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    //POST app/products/add
    @PostMapping(value = "/add")
    public String add(Product product) {
        Product newProduct = productService.add(product);
        System.out.println(newProduct);
        return "redirect:/products/" + newProduct.getId();
    }

    //GET app/products/{id}/delete
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        productService.removeById(id);
        return "redirect:/products/all";
    }
}
