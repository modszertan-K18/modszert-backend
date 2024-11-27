package com.example.modszert_backend.service;

import com.example.modszert_backend.config.JwtService;
import com.example.modszert_backend.entity.User;
import com.example.modszert_backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Optional<User> getMyProfile(HttpServletRequest request) {
        var requestUsername = this.jwtService.extractUsernameFromToken(
                this.jwtService.extractTokenFromRequest(request)
        );

        return userRepository.findByUsername(requestUsername);
    }
}
