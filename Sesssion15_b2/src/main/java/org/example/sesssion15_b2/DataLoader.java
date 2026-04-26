package org.example.sesssion15_b2;

import org.example.sesssion15_b2.Entity.Product;
import org.example.sesssion15_b2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Product(null, "iPhone 13", "Phone", 10000000.0, 10));
                repo.save(new Product(null, "Samsung S21", "Phone", 9000000.0, 5));
                repo.save(new Product(null, "Xiaomi", "Phone", 8000000.0, 0));
                repo.save(new Product(null, "MacBook", "Laptop", 25000000.0, 3));
            }
        };
    }
}