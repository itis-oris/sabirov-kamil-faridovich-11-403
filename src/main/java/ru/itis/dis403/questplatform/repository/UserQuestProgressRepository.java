package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.UserQuestProgress;
import java.util.List;
import java.util.Optional;

public interface UserQuestProgressRepository extends JpaRepository<UserQuestProgress, Long> {
    Optional<UserQuestProgress> findByUserIdAndQuestId(Long userId, Long questId);
    List<UserQuestProgress> findByUserId(Long userId);
}