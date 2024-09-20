package com.example.demo.Controllers;

import com.example.demo.Models.Task;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

