package com.example.demo.Repositorys;


import com.example.demo.Enums.Priority;
import com.example.demo.Enums.Status;
import com.example.demo.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    List<Task> findByTitleContaining(String title);

    List<Task> findByDescriptionContaining(String description);

    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByDueDate(LocalDateTime dueDate);
}