package com.example.devscheduler.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaskResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
}
