package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}