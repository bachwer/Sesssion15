package org.example.session15_b1.Service;


import org.example.session15_b1.entity.Product;
import org.example.session15_b1.repsitory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> filterProducts(String category, Double maxPrice){
        return productRepository
                .findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
                        category, maxPrice, 0
                );
    }
}
