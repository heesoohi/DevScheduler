package com.example.devscheduler.entity;

import com.example.devscheduler.dto.TaskRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Task extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(TaskRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}