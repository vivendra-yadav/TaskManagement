package com.example.demo.Services;

import com.example.demo.Models.Task;
import com.example.demo.Repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
