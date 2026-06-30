package ru.itis.dis403.questplatform.form;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TaskForm {
    private String question;
    private List<AnswerForm> answers = new ArrayList<>();
    private Integer correctAnswerIndex;
}