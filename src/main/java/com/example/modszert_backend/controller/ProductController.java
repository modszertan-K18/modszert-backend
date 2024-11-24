package com.example.modszert_backend.controller;

import com.example.modszert_backend.dto.ProductDto;
import com.example.modszert_backend.entity.Product;
import com.example.modszert_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @PostMapping("{productId}/bid")
    public ResponseEntity<ProductResponse> bidOnProduct(@PathVariable int productId, @RequestBody int bidIncrement) {
        return ResponseEntity.ok(productService.bidOnProduct(productId, bidIncrement));
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
