package com.example.modszert_backend.controller;

import com.example.modszert_backend.dto.BidDto;
import com.example.modszert_backend.dto.ProductDto;
import com.example.modszert_backend.entity.Bid;
import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.modszert_backend.service.BidService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BidService bidService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {

        System.out.println("Product DTO: " + productDto);

        var createdProduct = productService.create(productDto);

        System.out.println("Created Product: " + createdProduct);

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/product/{productId}/bid")
    public List<Bid> getBidsByProduct_ProductId(@PathVariable int productId) {
        return bidService.findAllByProductId(productId);
    }

    @PostMapping("{productId}/bid")
    public ResponseEntity<ProductResponse> bidOnProduct(
            @PathVariable int productId,
            @RequestBody BidDto bidRequest) {
        return ResponseEntity.ok(
                productService.bidOnProduct(productId, bidRequest.getBidAmount(), bidRequest.getUserId()));
    }


    @GetMapping("/user/{userId}")
    public List<Bid> getBidsByUserId(@PathVariable int userId) {
        return bidService.findAllByUserId(userId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> byId(@PathVariable int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

}
