package com.github.novikovmn.spring1.controller;

import com.github.novikovmn.spring1.domain.Authority;
import com.github.novikovmn.spring1.domain.Product;
import com.github.novikovmn.spring1.domain.User;
import com.github.novikovmn.spring1.service.CostFormatter;
import com.github.novikovmn.spring1.service.ProductService;
import com.github.novikovmn.spring1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CostFormatter costFormatter;

    // app/admin/products/?page={page}
    @GetMapping("/products")
    public String productList(Model model, @RequestParam Map<String, String> params) {
        final Integer PAGE_SIZE = 2;
        Integer page = 1;
        Page<Product> products = null;
        String title = "";
        String link = "";

        if (params.containsKey("page")) page = Integer.parseInt(params.get("page"));
        Pageable productsPageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id"));

        products = productService.getAllPageable(productsPageable);
        title = "Список товаров - админка";
        link = "?page=";

        model.addAttribute("title", title);
        model.addAttribute("products", products);
        model.addAttribute("costFormatter", costFormatter);
        model.addAttribute("page", page);
        model.addAttribute("total_pages", products.getTotalPages());
        model.addAttribute("link", link);
        return "adminproductlist";
    }

    // app/admin/products/save
    @PostMapping("/products/save")
    public String saveProduct(Product product, Integer rub, Integer cop, Integer page) {
        product.setCost(costFormatter.parse(rub, cop));
        productService.save(product);
        return "redirect:/admin/products?page=" + page;
    }

    // app/admin/products/add
    @PostMapping("/products/add")
    public String addProduct(Model model, String title, Integer rub, Integer cop) {
        final Integer PAGE_SIZE = 2;
        Integer cost = costFormatter.parse(rub, cop);
        productService.save(new Product(title, cost));
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("id"));
        Integer lastPage = productService.getAllPageable(pageable).getTotalPages();
        return "redirect:/admin/products?page=" + lastPage;
    }

    // app/admin/products/delete/?id={id}
    @GetMapping("/products/delete")
    public String deleteProduct(Model model, Long id, Integer page) {
        productService.deleteById(id);
        return "redirect:/admin/products?page=" + page;
    }

    // app/admin/users
    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public String userList(Model model) {
        String title = "Список пользователей";
        List<User> users = userService.getAll();
        List<Authority>[] authorities = new ArrayList[users.size()];
        int i = 0;
        for (User user:users) {
            authorities[i] = new ArrayList<>(user.getAuthorities());
            i++;
        }

        model.addAttribute("title", title);
        model.addAttribute("users", users);
        model.addAttribute("authorities", authorities);
        return "userlist";
    }
}
