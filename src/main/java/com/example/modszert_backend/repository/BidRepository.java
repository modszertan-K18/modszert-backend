package com.example.modszert_backend.repository;

import com.example.modszert_backend.entity.Bid;
import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    List<Bid> findAllByProduct_ProductId(int productId);

    List<Bid> findAllByUser_UserId(int userId);
}