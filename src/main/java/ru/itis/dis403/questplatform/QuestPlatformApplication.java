package ru.itis.dis403.questplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuestPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestPlatformApplication.class, args);
    }
}