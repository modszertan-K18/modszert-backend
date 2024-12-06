package com.example.modszert_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidDto {
    private int id;
    private int userId;
    private int bidId;
    private int bidAmount;

}
