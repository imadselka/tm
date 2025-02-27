package com.example.taskmanager.service;

import com.example.taskmanager.model.RefreshTokens;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.RefreshTokensRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokensRepository refreshTokensRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, RefreshTokensRepository refreshTokensRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.refreshTokensRepository = refreshTokensRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(username);
    }

    public RefreshTokens createRefreshToken(User user) {
        RefreshTokens refreshToken = new RefreshTokens();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusSeconds(604800)); // 7 days
        return refreshTokensRepository.save(refreshToken);
    }
}
