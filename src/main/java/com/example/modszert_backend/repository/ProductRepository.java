package com.example.modszert_backend.repository;

import com.example.modszert_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    //Optional<List<Product>> findAll();

    Optional<Product> findByProductId(int productId);

    //Optional<Product> createOne(Product product);
//
//    Product updateOne(Product product);
//
//    void delete(int productId);
//
//    List<Product> findAllByStatus(Product.Status status);
//
//    List<Product> findByCurrentBidBetween(int minPrice, int maxPrice);

}
