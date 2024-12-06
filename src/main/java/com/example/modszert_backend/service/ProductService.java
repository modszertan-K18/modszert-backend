package com.example.modszert_backend.service;

import com.example.modszert_backend.controller.ProductResponse;
import com.example.modszert_backend.dto.ProductDto;

import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.entity.Bid;

import com.example.modszert_backend.repository.BidRepository;
import com.example.modszert_backend.repository.ProductRepository;
import com.example.modszert_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(ProductDto productDto) {
        var product = Product.builder()
                .productName(productDto.getProductName())
                .productDescription(productDto.getProductDescription())
                .startingPrice(productDto.getStartingPrice())
                .currentPrice(productDto.getStartingPrice())
                .productOwnerId(productDto.getProductOwnerId())
                .auctionEndTime(productDto.getAuctionEndTime())
                .build();

        productRepository.save(product);
        return product;
    }

    public ProductResponse findById(int productId) {
        var product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot find product with id: " + productId
                ));
        return ProductResponse
                .builder()
                .productOwnerId(product.getProductOwnerId())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentPrice(product.getCurrentPrice())
                .build();
    }

    public ProductResponse bidOnProduct(int productId, int bidIncrement,int userId) {
        var product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot find product with id: " + productId
                ));

        if (bidIncrement <= 0){ throw new IllegalArgumentException("Bid increment must be greater than 0"); }


        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot find user with id: " + userId
                ));

        var newBid = Bid.builder()
                .currentBid(product.getCurrentPrice()+bidIncrement)
                .user(user)
                .bidDate(LocalDateTime.now())
                .product(product)
                .build();

        bidRepository.save(newBid);

        product.setCurrentPrice(product.getCurrentPrice() + bidIncrement);
        productRepository.save(product);


        return ProductResponse
                .builder()
                .productOwnerId(product.getProductOwnerId())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentPrice(product.getCurrentPrice())
                .build();
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

}
