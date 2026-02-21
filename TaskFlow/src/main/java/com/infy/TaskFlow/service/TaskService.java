package com.infy.TaskFlow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infy.TaskFlow.dto.TaskRequest;
import com.infy.TaskFlow.entity.Task;
import com.infy.TaskFlow.entity.User;
import com.infy.TaskFlow.repository.TaskRepository;
import com.infy.TaskFlow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTask(TaskRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.dueDate())
                .status(request.getRequest())
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getTasks(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUser(user);
    }

    public Task updateTask(Long id, TaskRequest request, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Forbidden");
        }
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setStatus(request.getStatus());

        return taskRepository.save(task);

    }

    public void deleteTask(Long id, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Forbidden");
        }
        taskRepository.delete(task);
    }
}
