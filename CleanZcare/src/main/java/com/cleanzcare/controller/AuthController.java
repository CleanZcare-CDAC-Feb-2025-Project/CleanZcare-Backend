package com.cleanzcare.controller;

import com.cleanzcare.dto.AuthRequest;
import com.cleanzcare.dto.AuthResponse;
import com.cleanzcare.entity.User;
import com.cleanzcare.repository.UserRepository;
import com.cleanzcare.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  AuthenticationManager authManager;
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private  PasswordEncoder encoder;
    @Autowired
    private  JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
    	System.out.println(request.getEmail()+" "+ request.getPassword());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole());

    }
}
