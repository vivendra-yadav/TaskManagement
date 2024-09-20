package com.example.demo.Controllers;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.Status;
import com.example.demo.Models.Task;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/createtask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task Created Successfully");
    }

    @PutMapping("/updatetask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return ResponseEntity.ok("Task Updated Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        }
    }

    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (isDeleted) {
            return ResponseEntity.ok("Task Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        }
    }


    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tasks);
    }

    // Search by Title
    @GetMapping("/tasks/searchByTitle")
    public ResponseEntity<List<Task>> getByTitle(@RequestParam(required = false) String title) {
        List<Task> tasks = taskService.getByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    // Search by Description
    @GetMapping("/tasks/searchByDescription")
    public ResponseEntity<List<Task>> getByDescription(@RequestParam(required = false) String description) {
        List<Task> tasks = taskService.getByDescription(description);
        return ResponseEntity.ok(tasks);
    }

    // Search by Status
    @GetMapping("/tasks/searchByStatus")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam(required = false) Status status) {
        List<Task> tasks = taskService.getByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    // Search by Priority
    @GetMapping("/tasks/searchByPriority")
    public ResponseEntity<List<Task>> getByPriority(@RequestParam(required = false) Priority priority) {
        List<Task> tasks = taskService.getByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    // Search by Due Date
    @GetMapping("/tasks/searchByDueDate")
    public ResponseEntity<List<Task>> getByDueDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        List<Task> tasks = taskService.getByDueDate(dueDate);
        return ResponseEntity.ok(tasks);
    }
}

