package com.example.modszert_backend.controller;

import com.example.modszert_backend.dto.ProductDto;
import com.example.modszert_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }
    @PostMapping("{productId}/bid")
    public ResponseEntity<ProductResponse> bidOnProduct(@PathVariable int productId, @RequestBody int bidIncrement) {
        return ResponseEntity.ok(productService.bidOnProduct(productId,bidIncrement));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> byId(@PathVariable int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }
}
