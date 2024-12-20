package com.example.modszert_backend.service;

import com.example.modszert_backend.config.JwtService;
import com.example.modszert_backend.customExceptions.UsernameAlreadyExistsException;
import com.example.modszert_backend.dto.LoginDto;
import com.example.modszert_backend.dto.RegisterDto;
import com.example.modszert_backend.entity.Role;
import com.example.modszert_backend.entity.User;
import com.example.modszert_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        var user = User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(LoginDto loginDto) {
        // check if the username and password are correct
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        var user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow();

        return jwtService.generateToken(user);
    }
}
