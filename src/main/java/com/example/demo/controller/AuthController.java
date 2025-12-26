package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repository,
                          PasswordEncoder encoder,
                          JwtUtil jwtUtil) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repository.findByEmail(user.getEmail())
                .orElseThrow();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole());
    }
}
