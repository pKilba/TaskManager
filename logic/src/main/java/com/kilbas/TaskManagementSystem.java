package com.kilbas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TaskManagementSystem implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystem.class, args);
    }

}
