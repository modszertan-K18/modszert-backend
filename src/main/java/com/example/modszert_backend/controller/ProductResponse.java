package com.example.modszert_backend.controller;

import com.example.modszert_backend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private String productDescription;
    private int startingPrice;
    private int currentBid;
    private OffsetDateTime auctionStartTime;
    private OffsetDateTime auctionEndTime;
    private String sellerId;
    private String buyerId;
    private Product.Status status;
}
