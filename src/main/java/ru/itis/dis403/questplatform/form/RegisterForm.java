package ru.itis.dis403.questplatform.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterForm {
    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 3, max = 50, message = "Длина имени от 3 до 50 символов")
    private String username;

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
    private String password;
}