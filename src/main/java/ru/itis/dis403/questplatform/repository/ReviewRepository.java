package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByQuestIdOrderByCreatedAtDesc(Long questId);
}