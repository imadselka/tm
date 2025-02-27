package com.example.taskmanager.service;

import com.example.taskmanager.model.Tasks;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Tasks createTask(Tasks tasks) {
        return taskRepository.save(tasks);
    }

    public Tasks updateTask(Tasks tasks) {
        return taskRepository.save(tasks);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Tasks getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
}