package org.example.sesssion15_b2.service;


import org.example.sesssion15_b2.Entity.Product;
import org.example.sesssion15_b2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> filterProducts(String category, Double maxPrice){
        return productRepository
                .findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
                        category, maxPrice, 0
                );
    }
}
