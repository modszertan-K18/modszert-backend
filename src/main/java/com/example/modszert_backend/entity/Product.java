package com.example.modszert_backend.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
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

    @Column(name = "product_owner_id",nullable = false)
    private Integer productOwnerId;


    @Column(nullable = false)
    private String productName;

    @Column(length = 600)
    private String productDescription;

    @Column(nullable = false)
    private LocalDateTime auctionEndTime;

    @Column(nullable = false)
    private int startingPrice;

    @Column(nullable = false)
    private int currentPrice;

    //Products bids
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

}
