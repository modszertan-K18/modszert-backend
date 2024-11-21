package com.example.modszert_backend.service;

import com.example.modszert_backend.controller.ProductResponse;
import com.example.modszert_backend.dto.ProductDto;
import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
                .auctionStartTime(productDto.getAuctionStartTime())
                .auctionEndTime(productDto.getAuctionEndTime())
                .sellerId(productDto.getSellerId())
                .buyerId(productDto.getBuyerId())
                .status(productDto.getStatus())
                .build();

        productRepository.save(product);
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentBid(product.getCurrentBid())
                .auctionStartTime(product.getAuctionStartTime())
                .auctionEndTime(product.getAuctionEndTime())
                .sellerId(product.getSellerId())
                .buyerId(product.getBuyerId())
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
                .auctionStartTime(product.getAuctionStartTime())
                .auctionEndTime(product.getAuctionEndTime())
                .sellerId(product.getSellerId())
                .buyerId(product.getBuyerId())
                .status(product.getStatus())
                .build();
    }

    public ProductResponse bidOnProduct(int productId, int bidIncrement) {
        var product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cannot find product with id: " + productId
                ));
        if (bidIncrement <= 0){ throw new IllegalArgumentException("Bid increment must be greater than 0"); }
        product.setCurrentBid(product.getCurrentBid() + bidIncrement);

        productRepository.save(product);
        return ProductResponse
                .builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .startingPrice(product.getStartingPrice())
                .currentBid(product.getCurrentBid())
                .auctionStartTime(product.getAuctionStartTime())
                .auctionEndTime(product.getAuctionEndTime())
                .sellerId(product.getSellerId())
                .buyerId(product.getBuyerId())
                .status(product.getStatus())
                .build();
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

}
