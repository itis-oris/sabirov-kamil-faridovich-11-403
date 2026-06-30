package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.dis403.questplatform.model.Quest;
import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long>, QuestRepositoryCustom {
    @Query("SELECT DISTINCT q FROM Quest q " +
            "JOIN q.reviews r " +
            "GROUP BY q.id " +
            "HAVING AVG(r.rating) >= :minRating")
    List<Quest> findPopularQuests(@Param("minRating") double minRating);
}