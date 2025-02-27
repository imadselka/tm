package com.example.taskmanager.service;

import com.example.taskmanager.model.RefreshTokens;
import com.example.taskmanager.repository.RefreshTokensRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RefreshTokensService {
    private final RefreshTokensRepository refreshTokensRepository;

    public RefreshTokensService(RefreshTokensRepository refreshTokensRepository) {
        this.refreshTokensRepository = refreshTokensRepository;
    }

    public List<RefreshTokens> getAllRefreshTokens() {
        return refreshTokensRepository.findAll();
    }

    public RefreshTokens createRefreshToken(RefreshTokens refreshToken) {
        return refreshTokensRepository.save(refreshToken);
    }

    public RefreshTokens updateRefreshToken(RefreshTokens refreshToken) {
        return refreshTokensRepository.save(refreshToken);
    }

    public void deleteRefreshToken(Long id) {
        refreshTokensRepository.deleteById(id);
    }

    public RefreshTokens getRefreshTokenById(Long id) {
        return refreshTokensRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refresh token not found with id: " + id));
    }
}