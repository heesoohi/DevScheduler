package com.example.devscheduler.service;

import com.example.devscheduler.dto.TaskRequestDto;
import com.example.devscheduler.dto.TaskResponseDto;
import com.example.devscheduler.entity.Task;
import com.example.devscheduler.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto) {
        Task task = new Task(taskRequestDto.getUserName(), taskRequestDto.getTitle(), taskRequestDto.getContent());
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask.getId(), savedTask.getUserName(), savedTask.getTitle(), savedTask.getContent());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskResponseDto(task.getId(), task.getUserName(), task.getTitle(), task.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task with id " + id + " not found")
        );
        return new TaskResponseDto(task.getId(), task.getUserName(), task.getTitle(), task.getContent());
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task with id " + id + " not found")
        );
        task.update(dto);
        return new TaskResponseDto(task.getId(), task.getUserName(), task.getTitle(), task.getContent());
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
