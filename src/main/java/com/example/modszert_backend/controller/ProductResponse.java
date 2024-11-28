package com.example.modszert_backend.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private String productDescription;
    private int startingPrice;

    //bid entity
    private int currentPrice;
    private LocalDateTime auctionEndTime;
}
