package org.example.sesssion15_b2.controller;


import org.example.sesssion15_b2.Entity.Product;
import org.example.sesssion15_b2.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String showForm() {
        return "index";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String category, @RequestParam Double maxPrice, Model model){

        List<Product> products = productService.filterProducts(category, maxPrice);
        model.addAttribute("products", products);
        return "index";
    }


}
