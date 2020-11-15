package com.github.novikovmn.spring1.controller;

import com.github.novikovmn.spring1.domain.Product;
import com.github.novikovmn.spring1.service.CostFormatter;
import com.github.novikovmn.spring1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/rest"
//        consumes = MediaType.APPLICATION_JSON_VALUE,
//        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RestProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CostFormatter costFormatter;

    // app/rest/products - GET
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    // app/rest/products/{id} - GET
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    // app/rest/products - POST
    @PostMapping("/products")
    public Product addProduct(Product product) {
        return productService.save(product);
    }

    // app/rest/products - PUT
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    // app/rest/products - DELETE
    @DeleteMapping("/products/{id}")
    public int deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }
}
