package com.example.devscheduler.repository;

import com.example.devscheduler.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
