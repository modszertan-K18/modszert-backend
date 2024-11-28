package com.example.modszert_backend.repository;

import com.example.modszert_backend.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Integer> {}