package com.example.modszert_backend.entity;

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

    @Column(nullable = false)
    private String productName;

    @Column(length = 600)
    private String productDescription;

    @Column(nullable = false)
    private int startingPrice;

    @Column(nullable = false)
    private int currentPrice;

    //Products bids
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

}
