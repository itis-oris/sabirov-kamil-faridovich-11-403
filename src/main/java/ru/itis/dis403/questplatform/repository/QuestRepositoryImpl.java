package ru.itis.dis403.questplatform.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itis.dis403.questplatform.model.Difficulty;
import ru.itis.dis403.questplatform.model.Quest;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestRepositoryImpl implements QuestRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<Quest> findByDifficultyAndCategory(Difficulty difficulty, String category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Quest> cq = cb.createQuery(Quest.class);
        Root<Quest> root = cq.from(Quest.class);

        List<Predicate> predicates = new ArrayList<>();

        if (difficulty != null) {
            predicates.add(cb.equal(root.get("difficulty"), difficulty));
        }
        if (category != null && !category.isBlank()) {
            predicates.add(cb.equal(root.get("category"), category));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}