package com.example.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanager.model.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    // JpaRepository provides built-in CRUD methods
}
