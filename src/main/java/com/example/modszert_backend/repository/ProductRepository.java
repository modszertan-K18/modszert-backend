package com.example.modszert_backend.repository;

import com.example.modszert_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAll();

    Product findById(int productId);

    Product createOne(Product product);

    Product updateOne(Product product);

    void deleteOne(int productId);

    List<Product> findAllByStatus(Product.Status status);

    List<Product> findByCurrentPriceBetween(int minPrice, int maxPrice);

}
