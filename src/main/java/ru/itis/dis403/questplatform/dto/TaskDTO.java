package ru.itis.dis403.questplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String question;
    private List<AnswerDTO> answers;
}