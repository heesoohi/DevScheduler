package com.example.devscheduler.controller;

import com.example.devscheduler.dto.TaskRequestDto;
import com.example.devscheduler.dto.TaskResponseDto;
import com.example.devscheduler.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDto> saveTask(@RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(taskService.saveTask(dto));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDto>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
