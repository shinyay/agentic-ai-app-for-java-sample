package com.example.techassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TechKnowledgeAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechKnowledgeAssistantApplication.class, args);
    }
}