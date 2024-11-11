package com.example.modszert_backend.service;

import com.example.modszert_backend.repository.ProductRepository;
import com.example.modszert_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

}
