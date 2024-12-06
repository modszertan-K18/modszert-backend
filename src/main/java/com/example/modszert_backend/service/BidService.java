package com.example.modszert_backend.service;

import com.example.modszert_backend.entity.Bid;
import com.example.modszert_backend.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BidService {
    private final BidRepository bidRepository;

    public List<Bid> findAllByProductId(int productId) {
        return bidRepository.findAllByProduct_ProductId(productId);
    }

    public List<Bid> findAllByUserId(int userId) {
        return bidRepository.findAllByUser_UserId(userId);
    }
}
