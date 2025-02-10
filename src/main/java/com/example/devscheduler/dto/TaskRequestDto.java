package com.example.devscheduler.dto;

import lombok.Getter;

@Getter
public class TaskRequestDto {
    private String title;
    private String content;
    private Long userId;
}
