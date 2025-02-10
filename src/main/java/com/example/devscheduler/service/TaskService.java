package com.example.devscheduler.service;

import com.example.devscheduler.dto.TaskRequestDto;
import com.example.devscheduler.dto.TaskResponseDto;
import com.example.devscheduler.entity.Task;
import com.example.devscheduler.entity.User;
import com.example.devscheduler.repository.TaskRepository;
import com.example.devscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto) {
        User user = userRepository.findById(taskRequestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User with id " + taskRequestDto.getUserId() + " not found")
        );
        Task task = new Task(taskRequestDto.getTitle(), taskRequestDto.getContent(), user);
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask.getId(),savedTask.getTitle(), savedTask.getContent(), savedTask.getUser().getId());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskResponseDto(task.getId(),task.getTitle(), task.getContent(), task.getUser().getId()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public TaskResponseDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task with id " + id + " not found")
        );
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContent(), task.getUser().getId());
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Task with id " + id + " not found")
        );
        task.update(dto);
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContent(), task.getUser().getId());
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
