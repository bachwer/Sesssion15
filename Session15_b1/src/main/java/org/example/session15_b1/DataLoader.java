package org.example.session15_b1;

import org.example.session15_b1.entity.Product;
import org.example.session15_b1.repsitory.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {

            // tránh insert trùng
            if (repository.count() == 0) {

                repository.save(new Product(null, "iPhone 13", "Phone", 10000000.0, 10));
                repository.save(new Product(null, "Samsung S21", "Phone", 9000000.0, 5));
                repository.save(new Product(null, "Xiaomi Mi 11", "Phone", 8000000.0, 0)); // hết hàng
                repository.save(new Product(null, "MacBook Pro", "Laptop", 25000000.0, 3));

                System.out.println("✅ Sample data inserted!");
            }
        };
    }
}