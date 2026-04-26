package org.example.session15_b1.controller;

import org.example.session15_b1.Service.ProductService;
import org.example.session15_b1.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/filter")
    public List<Product> filterProduct(@RequestParam String category,
                                       @RequestParam Double maxPrice){
        return productService.filterProducts(category, maxPrice);
    }
}
