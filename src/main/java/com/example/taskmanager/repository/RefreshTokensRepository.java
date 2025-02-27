package com.example.taskmanager.repository;

import com.example.taskmanager.model.RefreshTokens;
import com.example.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, Long> {
    Optional<RefreshTokens> findByToken(String token);
    void deleteByUser(User user);
}
