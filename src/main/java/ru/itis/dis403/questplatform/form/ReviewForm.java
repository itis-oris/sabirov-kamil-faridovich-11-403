package ru.itis.dis403.questplatform.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewForm {

    @Min(value = 1, message = "Минимальная оценка: 1")
    @Max(value = 5, message = "Максимальная оценка: 5")
    private Integer rating;

    @NotBlank(message = "Текст отзыва не может быть пустым")
    private String content;
}