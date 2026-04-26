package org.example.session15_b1.repsitory;


import org.example.session15_b1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
            String category,  Double maxPrice, Integer stockQuantity
    );
}
