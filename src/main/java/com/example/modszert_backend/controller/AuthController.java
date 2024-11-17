package com.example.modszert_backend.controller;

import com.example.modszert_backend.customExceptions.UsernameAlreadyExistsException;
import com.example.modszert_backend.dto.RegisterDto;
import com.example.modszert_backend.dto.LoginDto;
import com.example.modszert_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org. springframework. security. core. AuthenticationException;

import java.time.Duration;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@RequestBody RegisterDto registerDto) {
        if (registerDto.getUsername() == null || registerDto.getPassword() == null) {
            HashMap<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "please provide a username and password both");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }

        try {
            var jwtToken = authService.register(registerDto);

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

        } catch (UsernameAlreadyExistsException ex) {
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getMessage());

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDto loginDto) {
        if (loginDto.getUsername() == null || loginDto.getPassword() == null) {
            HashMap<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "please provide a username and password both");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }

        try {
            var jwtToken = authService.login(loginDto);

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
        } catch (AuthenticationException e) {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", "Invalid username or password");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }

    }
}
