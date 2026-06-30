package ru.itis.dis403.questplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Integer rating;
    private String authorName;
    private LocalDateTime createdAt;
}