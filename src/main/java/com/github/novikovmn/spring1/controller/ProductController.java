package com.github.novikovmn.spring1.controller;

import com.github.novikovmn.spring1.domain.Product;
import com.github.novikovmn.spring1.service.CostFormatter;
import com.github.novikovmn.spring1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping({"", "/"})
public class ProductController {

    @Autowired
    private ProductService productService;

//    private List<Product> productList = new ArrayList<>();

//    {
//        productList.add(new Product(1L, "Подвес прямой 300x30x0.9 мм",660));
//        productList.add(new Product(2L, "Уголок крепежный соединительный 40х40х40х1.8 мм",570));
//        productList.add(new Product(3L, "Уголок крепежный 25х14х25х1.75 мм",400));
//        productList.add(new Product(4L, "Шайба пружинная DIN 127B 4x9 мм, 15 шт.",410));
//        productList.add(new Product(5L, "Пластина бытовая 50х14х1.5 мм, сталь",650));
//        productList.add(new Product(6L, "Винт для крепления цилиндра M5x70 мм",720));
//    }

    @Autowired
    private CostFormatter costFormatter;

    // app/
    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("title", "Тест контроллера товаров");
        return "index";
    }

    //app/products/?min={minCost}&max={maxCost}&page={page}
    @RequestMapping({"/products", "/products/"})
    public String productList(Model model, @RequestParam Map<String, String> params) {
        final Integer PAGE_SIZE = 2;
        Integer page = 1;
        Page<Product> products = null;
        String title = "";
        String link = "";

        if (params.containsKey("page")) page = Integer.parseInt(params.get("page"));
        Pageable productsPageable = PageRequest.of(page - 1, PAGE_SIZE);

        Integer minRub = 0;
        Integer minCop = 0;
        Integer maxRub = 1000;
        Integer maxCop = 0;

        if (
                params.containsKey("minrub")
                && params.containsKey("mincop")
                && params.containsKey("maxrub")
                && params.containsKey("maxcop")
        ) {
            minRub = parceInt(params, "minrub");
            minCop = parceInt(params, "mincop");
            maxRub = parceInt(params, "maxrub");
            maxCop = parceInt(params, "maxcop");

            products = productService.getByMinAndMaxCostPageable(
                    costFormatter.parse(minRub, minCop)
                    ,costFormatter.parse(maxRub, maxCop)
                    ,productsPageable);
            title = "Товары с ценой от "
                    + costFormatter.print(minRub, minCop)
                    + " до "
                    + costFormatter.print(maxRub, maxCop);
            link = "?minrub=" + minRub
                    + "&mincop=" + minCop
                    + "&maxrub=" + maxRub
                    + "&maxcop=" + maxCop
                    + "&page=";
        } else {
            products = productService.getAllPageable(productsPageable);
            title = "Список товаров";
            link = "?page=";
        }
        model.addAttribute("title", title);
        model.addAttribute("products", products);
        model.addAttribute("minrub", minRub);
        model.addAttribute("mincop", minCop);
        model.addAttribute("maxrub", maxRub);
        model.addAttribute("maxcop", maxCop);
        model.addAttribute("costFormatter", costFormatter);
        model.addAttribute("page", page);
        model.addAttribute("total_pages", products.getTotalPages());
        model.addAttribute("link", link);
        return "productlist";
    }

    // app/product?id={id}
    @RequestMapping({"/product","/product/"})
    public String product(Model model, @RequestParam(name = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("costFormatter", costFormatter);
        return "product";
    }

    // app/product/save
    @RequestMapping(value = {"/product/save","/product/save/"}, method = RequestMethod.POST)
    public String productSave(Product product, Integer rub, Integer cop) {
        product.setCost(costFormatter.parse(rub, cop));
        productService.save(product);
        return "redirect:/product?id=" + product.getId();
    }

    public Integer parceInt(Map<String, String> params, String key) {
        if (params.containsKey(key)) return Integer.parseInt(params.get(key));
        return null;
    }

}
