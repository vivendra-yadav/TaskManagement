package com.example.demo.Services;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.Status;
import com.example.demo.Models.Task;
import com.example.demo.Repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);  // Save the task to the database
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            // Update fields as necessary
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setUpdatedAt(LocalDateTime.now()); // Update timestamp
            return taskRepository.save(existingTask);
        }
        return null; // Task not found
    }
    public boolean deleteTask(Long id) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            taskRepository.delete(existingTaskOptional.get());
            return true; // Task was found and deleted
        }
        return false; // Task not found
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null); // Returns the task or null if not found
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Returns all tasks
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    // Search by Title
    public List<Task> getByTitle(String title) {
        return taskRepository.findByTitleContaining(title);
    }

    // Search by Description
    public List<Task> getByDescription(String description) {
        return taskRepository.findByDescriptionContaining(description);
    }

    // Search by Status
    public List<Task> getByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    // Search by Priority
    public List<Task> getByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    // Search by Due Date
    public List<Task> getByDueDate(LocalDateTime dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }
}
