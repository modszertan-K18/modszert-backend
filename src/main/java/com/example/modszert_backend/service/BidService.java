package com.example.modszert_backend.service;

import com.example.modszert_backend.entity.Bid;
import com.example.modszert_backend.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {
    private final BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    //implement both of these
    public List<Bid> getBidsForProduct(Integer productId) {
        return bidRepository.findByProductId(productId);
    }

    public List<Bid> getBidsForUser(Integer userId) {
    }
}