package com.example.modszert_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int productOwnerId;
    private String productName;
    private String productDescription;
    private int startingPrice;
    private int currentPrice;
    private LocalDateTime auctionEndTime;
}
