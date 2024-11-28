package com.example.modszert_backend.controller;

import com.example.modszert_backend.entity.User;
import com.example.modszert_backend.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/my-profile")
    public ResponseEntity<Optional<User>> myProfile(HttpServletRequest request) {
        System.out.println("endpoint reached");
        return ResponseEntity.ok(this.profileService.getMyProfile(request));
    }
}
