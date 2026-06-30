package ru.itis.dis403.questplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private String text;
    private boolean correct;
}