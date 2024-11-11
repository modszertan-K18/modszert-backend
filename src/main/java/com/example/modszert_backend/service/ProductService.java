package com.example.modszert_backend.service;

import com.example.modszert_backend.controller.ProductResponse;
import com.example.modszert_backend.dto.ProductDto;
import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse create(ProductDto productDto) {
        var product = Product.builder()
                .productName(productDto.getProductName())
                .productDescription(productDto.getProductDescription())
                .startingPrice(productDto.getStartingPrice())
                .currentBid(productDto.getCurrentBid())
                .bidIncrement(productDto.getBidIncrement())
                .transactionId(productDto.getTransactionId())
                .auctionStartTime(productDto.getAuctionStartTime())
                .auctionEndTime(productDto.getAuctionEndTime())
                .sellerId(productDto.getSellerId())
                .status(productDto.getStatus())
                .images(productDto.getImages())
                .build();

        productRepository.save(product);
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentBid(product.getCurrentBid())
                .bidIncrement(product.getBidIncrement())
                .transactionId(product.getTransactionId())
                .auctionStartTime(product.getAuctionStartTime())
                .auctionEndTime(product.getAuctionEndTime())
                .sellerId(product.getSellerId())
                .images(product.getImages())
                .status(product.getStatus())
                .build();
    }

    public ProductResponse findById(int productId) {
        var product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot find product with id: " + productId
                ));
        return ProductResponse
                .builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentBid(product.getCurrentBid())
                .bidIncrement(product.getBidIncrement())
                .transactionId(product.getTransactionId())
                .auctionStartTime(product.getAuctionStartTime())
                .auctionEndTime(product.getAuctionEndTime())
                .sellerId(product.getSellerId())
                .images(product.getImages())
                .status(product.getStatus())
                .build();
    }

}
