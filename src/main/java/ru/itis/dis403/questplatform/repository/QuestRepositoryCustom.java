package ru.itis.dis403.questplatform.repository;

import ru.itis.dis403.questplatform.model.Difficulty;
import ru.itis.dis403.questplatform.model.Quest;
import java.util.List;

public interface QuestRepositoryCustom {
    List<Quest> findByDifficultyAndCategory(Difficulty difficulty, String category);
}
