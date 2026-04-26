package org.example.sesssion15_b2.repository;


import org.example.sesssion15_b2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
            String category, Double maxPrice, Integer stockQuantity
    );
}
