package ru.itis.dis403.questplatform.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class QuestForm {
    @NotBlank(message = "Название обязательно")
    private String title;

    @NotBlank(message = "Описание обязательно")
    private String description;

    @NotNull(message = "Сложность обязательна")
    private String difficulty;

    @NotBlank(message = "Категория обязательна")
    private String category;

    private List<TaskForm> tasks = new ArrayList<>();
}