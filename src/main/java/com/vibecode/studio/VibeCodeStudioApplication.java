package com.vibecode.studio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VibeCodeStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(VibeCodeStudioApplication.class, args);
    }
}