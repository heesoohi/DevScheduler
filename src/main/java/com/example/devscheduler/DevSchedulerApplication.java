package com.example.devscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevSchedulerApplication.class, args);
    }

}
