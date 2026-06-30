package ru.itis.dis403.questplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserQuestProgressDTO {
    private Long id;
    private String questTitle;
    private boolean completed;
    private Integer score;
    private LocalDateTime finishedAt;
    private String finishedAtFormatted;
}