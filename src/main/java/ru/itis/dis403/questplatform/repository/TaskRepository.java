package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByQuestIdOrderByQuestionAsc(Long questId);
}