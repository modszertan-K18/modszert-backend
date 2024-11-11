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

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> byId(@PathVariable int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }
}
