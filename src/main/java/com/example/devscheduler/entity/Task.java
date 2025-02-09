package com.example.devscheduler.entity;

import com.example.devscheduler.dto.TaskRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Task extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String title;
    private String content;

    public Task(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void update(TaskRequestDto dto) {
        this.userName = dto.getUserName();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
