package com.example.modszert_backend.entity;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    private Integer productId;

    @Column(nullable = false)
    private String productName;

    @Column(length = 600)
    private String productDescription;

    @Column(nullable = false)
    private int startingPrice;

    @Column
    private int currentBid;

    @Column(nullable = false)
    private OffsetDateTime auctionStartTime;

    @Column(nullable = false)
    private OffsetDateTime auctionEndTime;

    @Column(nullable = false)
    private String sellerId;

    @Column
    private String buyerId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE, SOLD, EXPIRED
    }
}
