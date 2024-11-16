package com.example.modszert_backend.controller;

import com.example.modszert_backend.config.JwtService;
import com.example.modszert_backend.dto.RegisterDto;
import com.example.modszert_backend.dto.LoginDto;
import com.example.modszert_backend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@RequestBody RegisterDto registerDto, HttpServletRequest request) {

        var jwtToken  = authService.register(registerDto);

        ResponseCookie cookie = ResponseCookie.from("access_token", jwtToken)
                .httpOnly(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(Duration.ofHours(24))
                .build();

        HashMap<String, String> successMessage = new HashMap<>();
        successMessage.put("message", "login success, access token as cookie sent!");

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(successMessage);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDto loginDto) {
        var jwtToken = authService.login(loginDto);

        ResponseCookie cookie = ResponseCookie.from("access_token", jwtToken)
                .httpOnly(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(Duration.ofHours(24))
                .build();

        HashMap<String, String> successMessage = new HashMap<>();
        successMessage.put("message", "register success, access token as cookie sent!");

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(successMessage);
    }
}
